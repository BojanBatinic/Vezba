package com.example.androiddevelopment.glumcilegende.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.androiddevelopment.glumcilegende.R;
import com.example.androiddevelopment.glumcilegende.db.DatabaseHelper;
import com.example.androiddevelopment.glumcilegende.db.model.Glumac;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by BBLOJB on 30.10.2017..
 */

public class ListFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    // Container Activity must implement this interface
    public interface OnGlumacSelectedListener {
        void onGlumacSelected(int id);
    }

    OnGlumacSelectedListener listener;
    ListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {

            List<Glumac> list = getDatabaseHelper().getGlumacDao().queryForAll();

            // Creates an ArrayAdaptar from the array of String
            adapter = new ArrayAdapter<Glumac>(getActivity(), R.layout.list_item, list);

            final ListView listView = (ListView) getActivity().findViewById(R.id.lista_filmova_glumac);

            // Assigns ArrayAdaptar to ListView
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Posto radimo sa bazom podataka, svaki element ima jedinstven id
                    // pa je potrebno da vidimo na koji tacno element smo kliknuli.
                    // To mozemo uraditi tako sto izvucemo proizvod iz liste i dobijemo njegov id
                    Glumac g = (Glumac) listView.getItemAtPosition(position);

                    listener.onGlumacSelected(g.getId());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (OnGlumacSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnItemSelectedListener");
        }
    }

    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }

}
