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
public interface EmisijaBuilder {

    Emisija build();

    EmisijaBuilder setId(final Integer id);

    EmisijaBuilder setNaziv(final String naziv);

    EmisijaBuilder setTrajanje(final Integer trajanje);

    EmisijaBuilder setVrsta(final Integer vrsta);

    EmisijaBuilder setOsobaUloga(final List<OsobaUloga> osobaUloga);
}
