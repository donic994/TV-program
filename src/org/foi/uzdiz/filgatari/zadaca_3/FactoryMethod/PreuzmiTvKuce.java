/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.FactoryMethod;

import java.io.File;
import java.util.List;
import java.time.LocalTime;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.TvKuca;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.pretvoriStringULocalTime;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.tvKuce;
import org.foi.uzdiz.filgatari.zadaca_3.Pomocna;
import org.foi.uzdiz.filgatari.zadaca_3.TjedniRasporedSingleton;

/**
 *
 * @author filip
 */
public class PreuzmiTvKuce implements Preuzmi {

    @Override
    public void preuzmi(String putanja) {
        System.out.println("----------------");
        System.out.println("PREUZIMAM TV KUCE");
        System.out.println("----------------");
        List<String> popisPodataka = Pomocna.preuzmiPodatkeDatoteke(putanja);
        Integer red = 0;
        List<TvKuca> listaTvKuca = TjedniRasporedSingleton.getInstance().listaProgramaTvKuce;
        if (popisPodataka.isEmpty()) {
            System.out.println("Datoteka s popisom TV kuca je prazna!");
            System.exit(1);
        } else {
            for (String redak : popisPodataka) {
                if (!redak.contains(";")) {
                    red++;
                    System.out.println("ERROR! Pogrešan delimiter u "
                            + red + ". retku! Potrebno je koristiti znak ; za delimiter.");
                } else {
                    red++;
                    String[] tvKuca = redak.split(";");
                    Integer delimiter = tvKuca.length;
                    dodajTvKucu(delimiter, tvKuca, listaTvKuca, red);
                }
            }
            tvKuce = listaTvKuca;
        }
    }

    private void dodajTvKucu(Integer delimiter, String[] tvKuca,
            List<TvKuca> listaTvKuca, Integer red) {
        if (delimiter == 5) {
            Integer id = 0;
            try {
                String idS = tvKuca[0].replaceAll("\\s+", "");
                id = Integer.parseInt(idS);
            } catch (Exception e) {
                System.out.println("ERROR! ID TV kuce nije cijeli broj!" + tvKuca[0]);
            }
            String nazivPrograma = tvKuca[1];
            LocalTime pocetak = pretvoriStringULocalTime(tvKuca[2]);
            LocalTime kraj = pretvoriStringULocalTime(tvKuca[3]);
            String nazivDatoteke = tvKuca[4];
            TvKuca tvk = new TvKuca(id, nazivPrograma, pocetak, kraj, nazivDatoteke);
            listaTvKuca.add(tvk);

            System.out.println("Preuzeta TV kuca " + tvk.nazivPrograma
                    + "(id=" + tvk.id + "), pocetak=" + tvk.pocetak
                    + ", kraj= " + tvk.kraj + ", datoteka= " + tvk.nazivDatoteke);

            preuzmiProgramTvKuce(tvk);
        } else {
            System.out.println("ERROR! Pogreška u formatu pisanja atributa TV kuce u "
                    + red + ". retku. " + delimiter + "/5 atributa");
        }
    }

    private void preuzmiProgramTvKuce(TvKuca tvk) {
        PreuzimanjeFactory preuzimanje = new PreuzimanjeFactory();
        Preuzmi preuzmiPrograme = preuzimanje.getTipPreuzimanja("program");
        String putanjaDatotekePrograma = preuzmiPutanjuDatotekePrograma(tvk);

        preuzmiPrograme.preuzmi(putanjaDatotekePrograma);
    }

    public static String preuzmiPutanjuDatotekePrograma(TvKuca tvKuca) {
        String putanjaDatoteke = "";
        putanjaDatoteke = new File("").getAbsolutePath() + "\\" + tvKuca.nazivDatoteke;
        return putanjaDatoteke;
    }
}
