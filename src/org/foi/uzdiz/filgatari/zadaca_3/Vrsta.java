/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3;

import java.io.Serializable;

/**
 *
 * @author filip
 */
public class Vrsta implements Serializable{

    public Integer id;
    public String naziv;
    public Integer reklama;
    public Integer trajanjeReklama;

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

    public Integer getReklama() {
        return reklama;
    }

    public void setReklama(Integer reklama) {
        this.reklama = reklama;
    }

    public Integer getTrajanjeReklama() {
        return trajanjeReklama;
    }

    public void setTrajanjeReklama(Integer trajanjeReklama) {
        this.trajanjeReklama = trajanjeReklama;
    }
}
