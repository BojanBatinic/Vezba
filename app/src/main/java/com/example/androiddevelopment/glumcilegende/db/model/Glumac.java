package com.example.androiddevelopment.glumcilegende.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by BBLOJB on 10.11.2017..
 */
@DatabaseTable(tableName = Glumac.TABLE_NAME_USERS)
public class Glumac {

    public static final String TABLE_NAME_USERS = "glumci";

    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_NAME   = "imePrezime";
    public static final String FIELD_NAME_BIOGRAFIJA   = "biografija";
    public static final String FIELD_NAME_DATUMR   = "datumRodjenja";
    public static final String FIELD_NAME_DATUMS   = "datumSmrti";
    public static final String FIELD_NAME_IMAGE  = "image";
    public static final String FIELD_NAME_FILM = "filmovi";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String imePrezime;

    @DatabaseField(columnName = FIELD_NAME_BIOGRAFIJA)
    private String biografija;

    @DatabaseField(columnName = FIELD_NAME_DATUMR)
    private String datumRodjenja;

    @DatabaseField(columnName = FIELD_NAME_DATUMS)
    private String datumSmrti;

    @DatabaseField(columnName = FIELD_NAME_IMAGE)
    private String image;

    @DatabaseField(columnName = FIELD_NAME_FILM, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Film film;

    public Glumac() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getDatumSmrti() {
        return datumSmrti;
    }

    public void setDatumSmrti(String datumSmrti) {
        this.datumSmrti = datumSmrti;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return imePrezime;
    }
}
