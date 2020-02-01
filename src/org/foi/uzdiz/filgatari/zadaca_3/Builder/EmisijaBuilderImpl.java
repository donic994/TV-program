/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Builder;

import java.util.List;
import org.foi.uzdiz.filgatari.zadaca_3.Emisija;
import org.foi.uzdiz.filgatari.zadaca_3.OsobaUloga;

/**
 *
 * @author filip
 */
public class EmisijaBuilderImpl implements EmisijaBuilder {

    private Emisija emisija;

    public EmisijaBuilderImpl() {
        emisija = new Emisija();
    }

    @Override
    public Emisija build() {
        return emisija;
    }

    @Override
    public EmisijaBuilder setId(Integer id) {
        emisija.setId(id);
        return this;
    }

    @Override
    public EmisijaBuilder setNaziv(String naziv) {
        emisija.setNaziv(naziv);
        return this;
    }

    @Override
    public EmisijaBuilder setTrajanje(Integer trajanje) {
        emisija.setTrajanje(trajanje);
        return this;
    }

    @Override
    public EmisijaBuilder setOsobaUloga(List<OsobaUloga> osobaUloga) {
        emisija.setOsobaUloga(osobaUloga);
        return this;
    }

    @Override
    public EmisijaBuilder setVrsta(Integer vrsta) {
        emisija.setVrsta(vrsta);
        return this;
    }

}
