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
public class EmisijaBuilderDirector {

    private EmisijaBuilder builder;

    public EmisijaBuilderDirector(final EmisijaBuilder builder) {
        this.builder = builder;
    }

    public void construct(Integer id,
            String naziv,
            Integer vrsta,
            Integer trajanje,
            List<OsobaUloga> osobaUloga) {
        builder.setId(id)
                .setNaziv(naziv)
                .setVrsta(vrsta)
                .setTrajanje(trajanje)
                .setOsobaUloga(osobaUloga);
    }

    public Emisija getEmisija() {
        return builder.build();
    }
}
