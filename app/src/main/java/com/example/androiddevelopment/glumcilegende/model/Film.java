package com.example.androiddevelopment.glumcilegende.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by androiddevelopment on 27.10.17..
 */

public class Film {

    private int id;
    private String nazivFilma;

    private List<Glumac> glumci;

    public Film() {
        glumci = new ArrayList<>();
    }

    public Film(int id, String nazivFilma) {
        this.id = id;
        this.nazivFilma = nazivFilma;
    }

    /*public Film(int id, String nazivFilma, ArrayList<Glumac> glumci) {
        this.id = id;
        this.nazivFilma = nazivFilma;
        this.glumci = glumci;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivFilma() {
        return nazivFilma;
    }

    public void setNazivFilma(String nazivFilma) {
        this.nazivFilma = nazivFilma;
    }

    public List<Glumac> getGlumaci() {
        return glumci;
    }

    public void setGlumci(List<Glumac> glumci) {
        this.glumci = glumci;
    }

}
