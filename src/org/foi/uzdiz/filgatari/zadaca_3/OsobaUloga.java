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
public class OsobaUloga implements Serializable {

    public Integer osoba;
    public Integer uloga;

    public Integer getOsoba() {
        return osoba;
    }

    public void setOsoba(Integer osoba) {
        this.osoba = osoba;
    }

    public Integer getUloga() {
        return uloga;
    }

    public void setUloga(Integer uloga) {
        this.uloga = uloga;
    }

}
