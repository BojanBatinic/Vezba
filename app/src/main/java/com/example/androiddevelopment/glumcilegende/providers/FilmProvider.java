package com.example.androiddevelopment.glumcilegende.providers;

import com.example.androiddevelopment.glumcilegende.model.Film;
import com.example.androiddevelopment.glumcilegende.model.Glumac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by androiddevelopment on 27.10.17..
 */

public class FilmProvider {

        public static List<Film> getFilmovi() {
            List<Film> filmovi = new ArrayList<>();
            filmovi.add(new Film(0, "Valter brani Sarajevo"));
            filmovi.add(new Film(1, "Poslednji krug u Monci"));
            filmovi.add(new Film(2, "Sesta brzina"));
            return filmovi;
        }

       /* public static List<String> getNazivFilma() {
            List<String> nazivi = new ArrayList<>();
            nazivi.add("Valter brani Sarajevo");
            nazivi.add("Poslednji krug u Monci");
            nazivi.add("Sesta brzina");
            return nazivi;
        }*/

        public static Film getFilmoById(int id) {
            switch (id) {
                case 0:
                    return new Film(0, "Valter brani Sarajevo");
                case 1:
                    return new Film(1, "Poslednji krug u Monci");
                case 2:
                    return new Film(2, "Sesta brzina");
                default:
                    return null;
            }
        }
}
