package org.foi.uzdiz.filgatari.zadaca_3;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author filip
 */
public class Emisija {

    public Integer id;
    public String naziv;
    public Integer trajanje;
    public List<OsobaUloga> osobaUloga;
    public Integer vrsta;

    public Integer getVrsta() {
        return vrsta;
    }

    public void setVrsta(Integer vrsta) {
        this.vrsta = vrsta;
    }

    public Emisija() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Integer trajanje) {
        this.trajanje = trajanje;
    }

    public List<OsobaUloga> getOsobaUloga() {
        return osobaUloga;
    }

    public void setOsobaUloga(List<OsobaUloga> osobaUloga) {
        this.osobaUloga = osobaUloga;
    }
}
