package com.example.androiddevelopment.glumcilegende.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.androiddevelopment.glumcilegende.R;
import com.example.androiddevelopment.glumcilegende.activities.FirstActivity;
import com.example.androiddevelopment.glumcilegende.db.model.Film;
import com.example.androiddevelopment.glumcilegende.db.model.Glumac;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by BBLOJB on 30.10.2017..
 */

public class DetailFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static int NOTIFICATION_ID = 1;

    private Glumac glumac = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        try {
//            if (product == null) { product = ((MainActivity)getActivity()).getDatabaseHelper().getProductDao().queryForId(0); }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            glumac = new Glumac();
            glumac.setId(savedInstanceState.getInt("id"));
            glumac.setImePrezime(savedInstanceState.getString("imePrezime"));
            glumac.setBiografija(savedInstanceState.getString("biografija"));
            glumac.setDatumRodjenja(savedInstanceState.getString("datumRodjenja"));
            glumac.setDatumSmrti(savedInstanceState.getString("datumSmrti"));
            glumac.setImage(savedInstanceState.getString("image"));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            savedInstanceState.putInt("id", glumac.getId());
            savedInstanceState.putString("imePrezime", glumac.getImePrezime());
            savedInstanceState.putString("biografija", glumac.getBiografija());
            savedInstanceState.putString("datumRodjenja", glumac.getDatumRodjenja());
            savedInstanceState.putString("datumSmrti", glumac.getDatumSmrti());
            savedInstanceState.putString("image", glumac.getImage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("DetailFragment", "onCreateView");

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        //fotografija glumca
        ImageView ivImage = (ImageView) getView().findViewById(R.id.image);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open(glumac.getImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            ivImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ime i prezime glumca
        TextView imePrezime = (TextView) view.findViewById(R.id.imePrezime);
        imePrezime.setText(glumac.getImePrezime());

        // kratka biografija o glumcu
        TextView biografija = (TextView) view.findViewById(R.id.biografija);
        biografija.setText(glumac.getBiografija());

        // datum roidjenja
        TextView datumRodjenja = (TextView) view.findViewById(R.id.datumRodjenja);
        datumRodjenja.setText(glumac.getDatumRodjenja());

        // datum smrti
        TextView datumSmrti = (TextView) view.findViewById(R.id.datumSmrti);
        datumSmrti.setText(glumac.getDatumSmrti());

        Spinner spinner = (Spinner) view.findViewById(R.id.filmovi_glumac);

        // lista filmova
        try {
            List<Film> film = ((FirstActivity) getActivity()).getDatabaseHelper().getFilmDao().queryForAll();
            ArrayAdapter<Film> dataAdapter = new ArrayAdapter<>(
                    getActivity(), android.R.layout.simple_spinner_item, film);
            spinner.setAdapter(dataAdapter);

            for (int i = 0; i < film.size(); i++) {
                if (film.get(i).getId() == glumac.getFilm().getId()) {
                    spinner.setSelection(i);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return view;

    }

    public void setGlumac(Glumac glumac) {
        this.glumac = glumac;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        // You can retrieve the selected item using
        //product.setCategory(CategoryProvider.getCategoryById((int)id));
    }

    @Override
    public void  onNothingSelected(AdapterView<?> parent){
        //product.setCategory(null);
    }
    /**
     * Kada dodajemo novi element u toolbar potrebno je da obrisemo prethodne elmente
     * zato pozivamo menu.clear() i dodajemo nove toolbar elemente
     * */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.detail_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void updateGlumac(Glumac glumac) {
        this.glumac = glumac;

        EditText imePrezime = (EditText) getActivity().findViewById(R.id.imePrezime);
        imePrezime.setText(glumac.getImePrezime());

        EditText biografija = (EditText) getActivity().findViewById(R.id.biografija);
        biografija.setText(glumac.getBiografija());

        EditText datumRodjenja = (EditText) getActivity().findViewById(R.id.datumRodjenja);
        datumRodjenja.setText(glumac.getDatumRodjenja());

        EditText datumSmrti = (EditText) getActivity().findViewById(R.id.datumSmrti);
        datumSmrti.setText(glumac.getDatumSmrti());

        ImageView imageView = (ImageView) getActivity().findViewById(R.id.image);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open(glumac.getImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doUpdateElement(){
        if (glumac != null){
            EditText imePrezime = (EditText) getActivity().findViewById(R.id.imePrezime);
            glumac.setImePrezime(imePrezime.getText().toString());

            EditText biografija = (EditText) getActivity().findViewById(R.id.biografija);
            glumac.setBiografija(biografija.getText().toString());

            EditText datumRodjenja = (EditText) getActivity().findViewById(R.id.datumRodjenja);
            glumac.setDatumRodjenja(datumRodjenja.getText().toString());

            EditText datumSmrti = (EditText) getActivity().findViewById(R.id.datumSmrti);
            glumac.setDatumSmrti(datumSmrti.getText().toString());

            Spinner film = (Spinner) getActivity().findViewById(R.id.filmovi_glumac);
            com.example.androiddevelopment.glumcilegende.db.model.Film f = (com.example.androiddevelopment.glumcilegende.db.model.Film)film.getSelectedItem();
            glumac.setFilm(f);

            try {
                ((FirstActivity) getActivity()).getDatabaseHelper().getGlumacDao().update(glumac);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getActivity().onBackPressed();

        }
    }
private void doRemoveElement(){
        if (glumac !=null){
            try {
                ((FirstActivity) getActivity()).getDatabaseHelper().getGlumacDao().delete(glumac);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getActivity().onBackPressed();
        }
}
    /**
     * Na fragment dodajemo element za brisanje elementa i za izmenu podataka
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove:
                doRemoveElement();
                break;
            case
            R.id.update:
                doUpdateElement();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}