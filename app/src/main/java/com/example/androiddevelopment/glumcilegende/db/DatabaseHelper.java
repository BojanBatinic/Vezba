package com.example.androiddevelopment.glumcilegende.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.example.androiddevelopment.glumcilegende.db.model.Film;
import com.example.androiddevelopment.glumcilegende.db.model.Glumac;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by BBLOJB on 10.11.2017..
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    //Dajemo ime bazi
    private static final String DATABASE_NAME = "ormlite.db";

    //takođe početna verzija baze, kreće se od 1...
    private static final int DATABASE_VERSION = 1;

    private Dao<Glumac, Integer> glumacDao = null;
    private Dao<Film, Integer> filmDao = null;

    //Potrebno je dodati konstruktor zbog pravilne inicijalizacije biblioteke
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Prilikom kreiranja baze potrebno je da pozovemo odgovarajuce metode biblioteke
    //prilikom kreiranja moramo pozvati TableUtils.createTable za svaku tabelu koju imamo

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Film.class);
            TableUtils.createTable(connectionSource, Glumac.class);

        }catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    try {
        TableUtils.dropTable(connectionSource, Film.class, true);
        TableUtils.dropTable(connectionSource, Glumac.class, true);
        onCreate(db, connectionSource);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    }

    //jedan Dao objekat sa kojim komuniciramo. Ukoliko imamo vise tabela
    //potrebno je napraviti Dao objekat za svaku tabelu
    public Dao<Glumac, Integer> getGlumacDao() throws SQLException {
        if (glumacDao == null) {
            glumacDao = getDao(Glumac.class);
        }
        return glumacDao;
    }

    public Dao<Film, Integer> getFilmDao() throws SQLException {
        if (filmDao == null) {
            filmDao = getDao(Film.class);
        }
        return filmDao;
    }

    @Override
    public void close(){
        glumacDao = null;
        filmDao = null;

        super.close();
    }
}
