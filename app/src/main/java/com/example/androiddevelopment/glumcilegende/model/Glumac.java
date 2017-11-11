package com.example.androiddevelopment.glumcilegende.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by androiddevelopment on 27.10.17..
 */

public class Glumac {

    private int id;
    private String image;
    private String imePrezime;
    private String biografija;
    private String datumRodjenja;
    private String datumSmrti;
    private Film film;

    public Glumac() {

    }

    public Glumac(int id, String image, String imePrezime, String biografija, String datumRodjenja, String datumSmrti, Film film) {
        this.id = id;
        this.image = image;
        this.imePrezime = imePrezime;
        this.biografija = biografija;
        this.datumRodjenja = datumRodjenja;
        this.datumSmrti = datumSmrti;
        this.film = film;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }


  /*  //metode iz prethodnih primera sa predavanja

    public void addFilm(Film film) {

        filmovi.add(film);
    }

    public void removeFilm(Film film) {

        filmovi.remove(film);
    }

    public Film getFilm(int position) {

        return filmovi.get(position);
    }*/

    @Override
    public String toString() {
        return imePrezime;
    }
}
