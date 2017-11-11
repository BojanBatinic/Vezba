package com.example.androiddevelopment.glumcilegende.activities;


import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androiddevelopment.glumcilegende.R;
import com.example.androiddevelopment.glumcilegende.adapters.DrawerAdapter;
import com.example.androiddevelopment.glumcilegende.db.DatabaseHelper;
import com.example.androiddevelopment.glumcilegende.db.model.Film;
import com.example.androiddevelopment.glumcilegende.db.model.Glumac;
import com.example.androiddevelopment.glumcilegende.dialogs.AboutDialog;
import com.example.androiddevelopment.glumcilegende.fragments.DetailFragment;
import com.example.androiddevelopment.glumcilegende.fragments.ListFragment;
import com.example.androiddevelopment.glumcilegende.model.NavigationItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 27.10.17..
 */

public class FirstActivity extends AppCompatActivity implements ListFragment.OnGlumacSelectedListener {

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItemFromDrawer(position);

        }
    }

    // Attributes used by NavigationDrawer
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private RelativeLayout drawerPane;
    private CharSequence drawerTitle;
    private CharSequence title;

    private ArrayList<NavigationItem> navigationItems = new ArrayList<NavigationItem>();

    // Attributes used by Dialog
    private AlertDialog dialog;

    // Attributes representing the activity's state
    private boolean landscapeMode = false; // Is the device in the landscape mode?
    private boolean listShown = false; // Is the MasterFragment fragment shown?
    private boolean detailShown = false; // Is the DetailFragment fragment shown?

    private int itemId = 0;

    private DatabaseHelper databaseHelper;

    // onCreate method is a lifecycle method called when he activity is starting
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        // setContentView method draws UI
        setContentView(R.layout.activity_first);

        // Draws navigation items
        navigationItems.add(new NavigationItem(getString(R.string.drawer_home), getString(R.string.drawer_home_long), R.drawable.ic_action_product));
        navigationItems.add(new NavigationItem(getString(R.string.drawer_settings), getString(R.string.drawer_settings_long), R.drawable.ic_action_settings));
        navigationItems.add(new NavigationItem(getString(R.string.drawer_about), getString(R.string.drawer_about_long), R.drawable.ic_action_about));

        title = drawerTitle = getTitle();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerList = (ListView) findViewById(R.id.navList);

        // Populates NavigtionDrawer with options
        drawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        DrawerAdapter adapter = new DrawerAdapter(this, navigationItems);

        // Sets a custom shadow that overlays the main content when NavigationDrawer opens
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        drawerList.setAdapter(adapter);

        // Enable ActionBar app icon to behave as action to toggle nav drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }

        drawerToggle = new ActionBarDrawerToggle(
                this,                           /* host Activity */
                drawerLayout,                   /* DrawerLayout object */
                toolbar,                        /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,           /* "open drawer" description for accessibility */
                R.string.drawer_close           /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();        // Creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();        // Creates call to onPrepareOptionsMenu()
            }
        };


        if (savedInstanceState == null) {
            // FragmentTransaction is a set of changes (e.g. adding, removing and replacing fragments) that you want to perform at the same time.
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ListFragment listFragment = new ListFragment();
            ft.add(R.id.displayList, listFragment, "List_Fragment_1");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            selectItemFromDrawer(0);
        }

        // If the device is in the landscape mode and the detail fragment is null create detail fragment
        if (findViewById(R.id.displayDetail) != null) {
            landscapeMode = true;
            getFragmentManager().popBackStack();

            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.displayDetail);
            if (detailFragment == null) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                detailFragment = new DetailFragment();
                FragmentTransaction detail_fragment = ft.replace(R.id.displayDetail, detailFragment, "Detail_Fragment_1");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                detailShown = true;
            }
        }
        listShown = true;
        detailShown = false;
        itemId = 0;

        addInitFilm();
    }

    private void addInitFilm() {
        try {
            if (getDatabaseHelper().getFilmDao().queryForAll().size() == 0) {
                Film bioskopski = new Film();
                bioskopski.setName("Dugometražni");

                Film televizijski = new Film();
                televizijski.setName("Serija");

                getDatabaseHelper().getFilmDao().create(bioskopski);
                getDatabaseHelper().getFilmDao().create(televizijski);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //da bi dodali podatak u bazu, potrebno je da napravimo objekat klase
    //koji reprezentuje tabelu i popunimo podacima
    private void addItem() throws SQLException {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);

        final Spinner imagesSpinner = (Spinner) dialog.findViewById(R.id.glumci_image);
        List<String> imagesList = new ArrayList<>();
        imagesList.add("velimir.jpg");
        imagesList.add("dragan.jpg");
        imagesList.add("zoran.jpg");
        ArrayAdapter<String> imagesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, imagesList);
        imagesSpinner.setAdapter(imagesAdapter);
        imagesSpinner.setSelection(0);

        final Spinner glumciSpinner = (Spinner) dialog.findViewById(R.id.filmovi_glumac);
        List<Film> list = getDatabaseHelper().getFilmDao().queryForAll();
        ArrayAdapter<Film> dataAdapter = new ArrayAdapter<Film>(this, android.R.layout.simple_spinner_item, list);
        glumciSpinner.setAdapter(dataAdapter);
        glumciSpinner.setSelection(0);

        final EditText glumacImePrezime = (EditText) dialog.findViewById(R.id.imePrezime);
        final EditText glumacBiografija = (EditText) dialog.findViewById(R.id.biografija);
        final EditText glumacDatumRodjenja = (EditText) dialog.findViewById(R.id.datumRodjenja);
        final EditText glumacDatumSmrti = (EditText) dialog.findViewById(R.id.datumSmrti);
        //final EditText glumacFilmovi = (EditText) dialog.findViewById(R.id.filmovi_glumac);

        Button ok = (Button) dialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imePrezime = glumacImePrezime.getText().toString();
                String biografija = glumacBiografija.getText().toString();
                String datumR = glumacDatumRodjenja.getText().toString();
                String datumS = glumacDatumSmrti.getText().toString();
                Film film = (Film) glumciSpinner.getSelectedItem();
                String image = (String) imagesSpinner.getSelectedItem();

                Glumac glumac = new Glumac();
                glumac.setImePrezime(imePrezime);
                glumac.setBiografija(biografija);
                glumac.setDatumRodjenja(datumR);
                glumac.setDatumSmrti(datumS);
                glumac.setImage(image);
                glumac.setFilm(film);

                try {
                    getDatabaseHelper().getGlumacDao().create(glumac);
                    refresh();
                    Toast.makeText(FirstActivity.this, "Glumac upisan", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        } );

        dialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_item_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                refresh();
                break;
            case R.id.action_add:
                try {
                    addItem();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    // refresh() prikazuje novi sadrzaj.Povucemo nov sadrzaj iz baze i popunimo listu
    private void refresh() {
        ListView listView = (ListView) findViewById(R.id.glumci);

        if (listView != null) {
            ArrayAdapter<Glumac> adapter = (ArrayAdapter<Glumac>) listView.getAdapter();

            if(adapter != null)
            {
                try {
                    adapter.clear();
                    List<Glumac> list = getDatabaseHelper().getGlumacDao().queryForAll();
                    adapter.addAll(list);
                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void  onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectItemFromDrawer(int position) {
        if (position == 0){

        } else if (position == 1){
            Intent settings = new Intent (FirstActivity.this, SettingsActivity.class);
            startActivity(settings);
        }else if (position == 2){
           if (dialog == null){
               dialog = new AboutDialog(FirstActivity.this).prepareDialog();
           } else {
               if (dialog.isShowing()) {
                   dialog.dismiss();
               }
           }
           dialog.show();
        }

      drawerList.setItemChecked(position, true);
      setTitle(navigationItems.get(position).getTitle());
      drawerLayout.closeDrawer(drawerPane);
    }

    @Override
    public void onGlumacSelected(int id) {

        itemId = id;

        try {
            Glumac glumac = getDatabaseHelper().getGlumacDao().queryForId(id);

            if (landscapeMode) {
                DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.displayDetail);
                detailFragment.updateGlumac(glumac);
            } else {
                    DetailFragment detailFragment = new DetailFragment();
                    detailFragment.setGlumac(glumac);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.displayDetail, detailFragment, "Detail_Fragment2");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack("Detail_Fragment2");
                    ft.commit();
                    listShown = false;
                    detailShown = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public  void onBackPressed() {

        if (landscapeMode) {
            finish();
        }else if (listShown == true) {
            finish();
        } else if (detailShown == true) {
            getFragmentManager().popBackStack();
            ListFragment listFragment = new ListFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.displayList, listFragment, "List_Fragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            listShown = true;
            detailShown = false;
        }
    }
    //Metoda koja komunicira sa bazom podataka
    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);

        }
        return databaseHelper;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        // nakon rada sa bazom podataka potrebno je obavezno
        //osloboditi resurse!
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}