/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.FactoryMethod;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.filgatari.zadaca_3.Osoba;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.osobe;
import org.foi.uzdiz.filgatari.zadaca_3.Pomocna;

/**
 *
 * @author filip
 */
public class PreuzmiOsobe implements Preuzmi {

    @Override
    public void preuzmi(String putanja) {
        System.out.println("----------------");
        System.out.println("PREUZIMAM OSOBE");
        System.out.println("----------------");
        Integer red = 0;
        List<Osoba> listaOsoba = new ArrayList<>();
        List<String> popisPodataka = Pomocna.preuzmiPodatkeDatoteke(putanja);
        if (popisPodataka.isEmpty()) {
            System.out.println("Datoteka s popisom osoba je prazna!");
            System.exit(1);
        }
        for (String redak : popisPodataka) {
            if (!redak.contains(";")) {
                red++;
                System.out.println("ERROR! Pogrešan delimiter u "
                        + red + ". retku! Potrebno je koristiti znak ; za delimiter");
            }else {
                red++;
                String[] osoba = redak.split(";");
                if(osoba.length!=2){
                    System.out.println("ERROR!Pogreška zapisa u "
                        + red + ". retku!");
                }
                else{
                    dodajOsobu(osoba, listaOsoba);
            }}
        }
        osobe = listaOsoba;
    }

    private void dodajOsobu(String[] osoba, List<Osoba> listaOsoba) {
        Osoba o = new Osoba();
        try {
            String id = osoba[0].replaceAll("\\s+", "");
            o.id = Integer.parseInt(id);
        } catch (Exception e) {
            System.out.println("ERROR! ID osobe nije cijeli broj!");
        }
        o.imeIPrezime = osoba[1];
        listaOsoba.add(o);
        System.out.println("Preuzeta osoba " + o.imeIPrezime + "(id=" + o.id + ")");
    }
}
