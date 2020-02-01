/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.FactoryMethod;

import org.foi.uzdiz.filgatari.zadaca_3.Builder.EmisijaBuilderDirector;
import org.foi.uzdiz.filgatari.zadaca_3.Builder.EmisijaBuilderImpl;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.filgatari.zadaca_3.Emisija;
import org.foi.uzdiz.filgatari.zadaca_3.Builder.EmisijaBuilder;
import org.foi.uzdiz.filgatari.zadaca_3.IspisOsobaUlogaSingleton;
import org.foi.uzdiz.filgatari.zadaca_3.OsobaUloga;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.emisije;
import org.foi.uzdiz.filgatari.zadaca_3.Pomocna;

/**
 *
 * @author filip
 */
public class PreuzmiEmisije implements Preuzmi {

    IspisOsobaUlogaSingleton ispisOsobaUloga = IspisOsobaUlogaSingleton.getInstance();

    @Override
    public void preuzmi(String putanja) {
        System.out.println("----------------");
        System.out.println("PREUZIMAM EMISIJE");
        System.out.println("----------------");
        Integer red = 0;
        List<String> popisPodataka = Pomocna.preuzmiPodatkeDatoteke(putanja);
        List<Emisija> listaEmisija = new ArrayList<>();
        if (popisPodataka.isEmpty()) {
            System.out.println("Datoteka s popisom emisija je prazna!");
            System.exit(1);
        }
        for (String redak : popisPodataka) {
            if (!redak.contains(";")) {
                red++;
                System.out.println("ERROR! Pogrešan delimiter u "
                        + red + ". retku! Potrebno je koristiti znak ; za delimiter.");
            } else {
                red++;
                String[] emisija = redak.split(";");
                Integer delimiter = emisija.length;
                Integer emisijaId = null, emisijaTrajanje = null, emisijaVrsta = null;
                String emisijaNaziv;
                List<OsobaUloga> emisijaOsobaUloga = null;
                if (delimiter >= 4) {
                    Emisija e = dodajEmisiju(emisija, emisijaId, emisijaVrsta,
                            emisijaTrajanje, delimiter, emisijaOsobaUloga, listaEmisija);
                    System.out.println("Preuzeta emisija " + e.naziv
                            + "(id=" + e.id + "), vrsta = " + e.vrsta + ", trajanje=" + e.trajanje
                            + ", " + ispisOsobaUloga.ispisiOsobaUloga(e.osobaUloga));
                } else {
                    System.out.println("ERROR! Pogreška u formatu pisanja atributa emisije u "
                            + red + ". retku. " + delimiter + "/4 atributa");
                }
            }
        }
        emisije = listaEmisija;
    }

    private Emisija dodajEmisiju(String[] emisija, Integer emisijaId, Integer emisijaVrsta,
            Integer emisijaTrajanje, Integer delimiter, List<OsobaUloga> emisijaOsobaUloga,
            List<Emisija> listaEmisija) {
        String emisijaNaziv;
        EmisijaBuilder builder = new EmisijaBuilderImpl();
        EmisijaBuilderDirector emisijaBuilderDirector = new EmisijaBuilderDirector(builder);
        try {
            String id = emisija[0].replaceAll("\\s+", "");
            emisijaId = Integer.parseInt(id);
        } catch (Exception ex) {
            System.out.println("ERROR! ID emisije nije cijeli broj!" + emisija[0]);
        }
        try {
            String vrsta = emisija[2].replaceAll("\\s+", "");
            emisijaVrsta = Integer.parseInt(vrsta);
        } catch (Exception ex) {
            System.out.println("ERROR! ID emisije nije cijeli broj!" + emisija[2]);
        }
        emisijaNaziv = emisija[1];
        try {
            String trajanje = emisija[3].replaceAll("\\s+", "");
            emisijaTrajanje = Integer.parseInt(trajanje);
        } catch (Exception ex) {
            System.out.println("ERROR! ID emisije nije cijeli broj!" + emisija[2]);
        }
        if (delimiter >= 5) {
            String[] osobaUlogaZajedno = emisija[4].split(",");
            List<OsobaUloga> listaOsobaIUloga = dodajOsobaUloga(osobaUlogaZajedno);
            emisijaOsobaUloga = listaOsobaIUloga;

        }
        emisijaBuilderDirector.construct(emisijaId, emisijaNaziv, emisijaVrsta,
                emisijaTrajanje, emisijaOsobaUloga);
        Emisija e = emisijaBuilderDirector.getEmisija();
        listaEmisija.add(e);
        return e;
    }

    private static List<OsobaUloga> dodajOsobaUloga(String[] osobaUlogaZajedno) {
        List<OsobaUloga> listaOsobaIUloga = new ArrayList<>();
        for (String str : osobaUlogaZajedno) {
            String[] osobaUlogaOdvojeno = str.split("-");
            OsobaUloga ou = new OsobaUloga();
            try {
                String osoba = osobaUlogaOdvojeno[0].replaceAll("\\s+", "");
                ou.osoba = Integer.parseInt(osoba);
                String uloga = osobaUlogaOdvojeno[1].replaceAll("\\s+", "");
                ou.uloga = Integer.parseInt(uloga);
                if (uloga == null || osoba == null) {
                    throw new Exception();
                }
                listaOsobaIUloga.add(ou);
            } catch (Exception e) {
                System.out.println("ERROR! ID osobe ili uloge nije cijeli broj!");
            }
        }
        return listaOsobaIUloga;
    }
}
