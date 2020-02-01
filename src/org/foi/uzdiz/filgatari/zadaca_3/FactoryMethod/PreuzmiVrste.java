/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.FactoryMethod;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.filgatari.zadaca_3.IspisOsobaUlogaSingleton;
import org.foi.uzdiz.filgatari.zadaca_3.Vrsta;
import org.foi.uzdiz.filgatari.zadaca_3.Pomocna;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.vrste;

/**
 *
 * @author filip
 */
public class PreuzmiVrste implements Preuzmi {

    IspisOsobaUlogaSingleton ispisOsobaUloga = IspisOsobaUlogaSingleton.getInstance();

    @Override
    public void preuzmi(String putanja) {
        System.out.println("----------------");
        System.out.println("PREUZIMAM VRSTE ");
        System.out.println("----------------");
        boolean dodaj = true;
        List<Vrsta> listaVrsti = new ArrayList<>();
        List<String> popisVrsti = Pomocna.preuzmiPodatkeDatoteke(putanja);
        Integer red = 0;
        if (popisVrsti.isEmpty()) {
            System.out.println("Datoteka s popisom vrsta programa je prazna!");
            System.exit(1);
        }
        for (String redak : popisVrsti) {
            if (!redak.contains(";")) {
                red++;
                System.out.println("ERROR! Pogrešan delimiter u "
                        + red + ". retku! Potrebno je koristiti znak ; za delimiter.");
                dodaj = false;
            } else {
                red++;
                String[] vrsta = redak.replaceAll("\\s+", "").split(";");
                Integer delimiter = vrsta.length;
                if (delimiter >= 1) {
                    Vrsta v = new Vrsta();
                    try {
                        String id = vrsta[0].replaceAll("\\s+", "");
                        v.id = Integer.parseInt(id);
                    } catch (Exception e) {
                        System.out.println("ERROR! ID vrste nije cijeli broj!" + vrsta[0]);
                        dodaj = false;
                    }
                    if (delimiter >= 4) {
                        v.naziv = vrsta[1];
                        if (vrsta[2].equals("0") || vrsta[2].equals("1")) {
                            String reklama = vrsta[2].replaceAll("\\s+", "");
                            v.reklama = Integer.parseInt(reklama);
                        } else {
                            System.out.println("ERROR! Stupac za reklame može imati samo vrijednosti 1 ili 0");
                            dodaj = false;
                        }
                        try {
                            String trajanje = vrsta[3].replaceAll("\\s+", "");
                            v.trajanjeReklama = Integer.parseInt(trajanje);
                        } catch (Exception e) {
                            System.out.println("ERROR! Trajanje reklama vrste nije cijeli broj!" + vrsta[0]);
                            dodaj = false;
                        }
                    }
                    if (dodaj) {
                        listaVrsti.add(v);
                        vrste = listaVrsti;
                        System.out.println("Preuzeta vrsta " + v.naziv + "(" + v.id + "),"
                                + " ima reklame = " + v.reklama + ", trajanje reklama= " + v.trajanjeReklama);
                    }
                } else {
                    System.out.println("ERROR! Pogreška u formatu pisanja atributa vrste u "
                            + red + ". retku. " + delimiter + "/4 atributa");
                }
            }
        }
    }
}
