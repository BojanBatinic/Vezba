package com.example.androiddevelopment.glumcilegende.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by BBLOJB on 10.11.2017..
 */
@DatabaseTable(tableName = Film.TABLE_NAME_USERS)
public class Film {

    public static final String TABLE_NAME_USERS = "filmovi";

    public static final String FILED_NAME_ID = "id";
    public static final String FILED_NAME_NAME = "nazivFilma";

    @DatabaseField(columnName = FILED_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FILED_NAME_NAME)
    private String name;

    //ORMLite zahteva prazan konstuktur u klasama koje opisuju tabele u bazi!
    public Film() {
    }

    public Film(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
