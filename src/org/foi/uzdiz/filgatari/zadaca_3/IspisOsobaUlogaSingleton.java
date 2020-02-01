/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3;

import java.util.List;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.osobe;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.uloge;

/**
 *
 * @author filip
 */
public class IspisOsobaUlogaSingleton {

    private static IspisOsobaUlogaSingleton instance = new IspisOsobaUlogaSingleton();

    private IspisOsobaUlogaSingleton() {
    
    }

    public static IspisOsobaUlogaSingleton getInstance() {
        return instance;
    }
    
     public String ispisiOsobaUloga(List<OsobaUloga> osobeUloge) {
        String osoba = "", uloga = "", odgovor = "";
        if (osobeUloge == null) {
            return odgovor;
        } else {
            for (OsobaUloga ou : osobeUloge) {
                for (Osoba o : osobe) {
                    if (ou.osoba.equals(o.id)) {
                        osoba = o.imeIPrezime;
                    }
                }
                for (Uloga u : uloge) {
                    if (ou.uloga.equals(u.id)) {
                        uloga = u.opis;
                    }
                }
                odgovor = uloga + " = " + osoba;
            }
        }
        return odgovor;
    }

}
