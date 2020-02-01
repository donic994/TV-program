/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.FactoryMethod;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.filgatari.zadaca_3.Uloga;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.uloge;
import org.foi.uzdiz.filgatari.zadaca_3.Pomocna;

/**
 *
 * @author filip
 */
public class PreuzmiUloge implements Preuzmi {

    @Override
    public void preuzmi(String putanja) {
        System.out.println("----------------");
        System.out.println("PREUZIMAM ULOGE");
        System.out.println("----------------");
        List<String> popisPodataka = Pomocna.preuzmiPodatkeDatoteke(putanja);
        Integer red = 0;
        List<Uloga> listaUloga = new ArrayList<>();
        if (popisPodataka.isEmpty()) {
            System.out.println("Datoteka s popisom uloga je prazna!");
            System.exit(1);
        }
        for (String redak : popisPodataka) {
            if (!redak.contains(";")) {
                red++;
                System.out.println("ERROR! Pogrešan delimiter u "
                        + red + ". retku! Potrebno je koristiti znak ; za delimiter");
            } else {
                red++;
                String[] uloga = redak.split("; ");
                Uloga u = new Uloga();
                try {
                    String id = uloga[0].replaceAll("\\s+", "");
                    u.id = Integer.parseInt(id);
                } catch (Exception e) {
                    System.out.println("ERROR! ID uloge nije cijei broj!");
                }
                u.opis = uloga[1];
                listaUloga.add(u);
                System.out.println("Preuzeta uloga " + u.opis + "(id=" + u.id + ")");
            }
        }
        uloge = listaUloga;
    }
}
