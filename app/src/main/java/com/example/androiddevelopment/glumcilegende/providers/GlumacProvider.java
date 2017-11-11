package com.example.androiddevelopment.glumcilegende.providers;

import com.example.androiddevelopment.glumcilegende.model.Film;
import com.example.androiddevelopment.glumcilegende.model.Glumac;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by androiddevelopment on 27.10.17..
 */

public class GlumacProvider {


    public static List<Glumac> getGlumac() {
        Film filmovi = new Film (0, "Valter brani Sarajevo");

        List<Glumac> glumci = new ArrayList<>();
        glumci.add(new Glumac(0, "velimir.jpg", "Velimir Bata Zivojinovic", "Bio je jugoslovenski i srpski pozorišni, televizijski i filmski glumac. Najpoznatiji jugoslovenski glumac po ulogama u partizanskim filmovima vezanim za Drugi svetski rat...", " rodjen 05.06.1933", " preminuo 16.05.2016", filmovi));
        glumci.add(new Glumac(1, "dragan.jpg", "Dragan Gaga Nikolic", "Bio je jugoslovenski i srpski pozorišni, televizijski i filmski glumac. Važio je za predstavnika beogradskog šarma, šmekera sa karakterom gospodina, nacionalnu klasu, jednog od najvećih frajera YU filma...", " rodjen 20.08.1943", " preminuo 11.03.2016", filmovi));
        glumci.add(new Glumac(2, "zoran.jpg", "Zoran Radmilovic", "Bio je jugoslovenski i srpski pozorišni, televizijski i filmski glumac. Jedan od najboljih i najvoljenijih glumaca, kao i jedan od najvećih komičara sa ovih prostora...", " rodjen 11.05.1933"," preminuo 21.07.1985", filmovi));

        return glumci;

       /* Film filmovi = new Film (0, "Glumac");
        ArrayList<Film> filmovi = new ArrayList<>();
        filmovi.add(new Film(0, "Valter brani Sarajevo"));
        filmovi.add(new Film(1, "Poslednji krug u Monci"));
        filmovi.add(new Film(2, "Sesta brzina"));*/

    }

   /* public static List<String> getImenaGlumaca() {

        List<String> imenaPrezimena = new ArrayList<>();
        imenaPrezimena.add("Velimir Bata Zivojinovic");
        imenaPrezimena.add("Dragan Gaga Nikolic");
        imenaPrezimena.add("Zoran Radmilovic");

        return imenaPrezimena;
    }*/

   public static Glumac getGlumacById(int id) {
       Film filmovi = new Film (0, "Valter brani Sarajevo");

       /* ArrayList<Film> filmovi = new ArrayList<>();
        filmovi.add(new Film(0, "Valter brani Sarajevo"));
        filmovi.add(new Film(1, "Poslednji krug u Monci"));
        filmovi.add(new Film(2, "Sesta brzina"));*/

        switch (id) {
            case 0:
                return new Glumac(0, "velimir.jpg", "Velimir Bata Zivojinovic", "Bio je jugoslovenski i srpski pozorišni, televizijski i filmski glumac. Bio je najpoznatiji jugoslovenski glumac po ulogama u partizanskim filmovima vezanim za Drugi svetski rat.", "05.06.1933", "16.05.2016", filmovi);
            case 1:
                return new Glumac(1, "dragan.jpg", "Dragan Gaga Nikolic", "Bio je jugoslovenski i srpski pozorišni, televizijski i filmski glumac. Važio je za predstavnika beogradskog šarma, šmekera sa karakterom gospodina, nacionalnu klasu, jednog od najvećih frajera YU filma...", "20.08.1943", "11.03.2016", filmovi);
            case 2:
                return new Glumac(2, "zoran.jpg", "Zoran Radmilovic", "Bio je jugoslovenski i srpski pozorišni, televizijski i filmski glumac. Jedan od najboljih i najvoljenijih glumaca, kao i jedan od najvećih komičara sa ovih prostora...", "11.05.1933","21.07.1985", filmovi);
            default:
                return null;
        }
    }
}
