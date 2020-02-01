/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.FactoryMethod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.DaniUTjednu;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.EmisijaUProgramu;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.TjedniRaspored;
import org.foi.uzdiz.filgatari.zadaca_3.Emisija;
import org.foi.uzdiz.filgatari.zadaca_3.IspisOsobaUlogaSingleton;
import org.foi.uzdiz.filgatari.zadaca_3.OsobaUloga;
import org.foi.uzdiz.filgatari.zadaca_3.Program;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.dodajOsobaUloga;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.emisije;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.ispisiDaneEmitiranjaNazivi;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.ispisiEmisiju;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.pretvoriStringULocalTime;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.preuzmiDaneUTjednu;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.programi;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.vrste;
import org.foi.uzdiz.filgatari.zadaca_3.Pomocna;
import org.foi.uzdiz.filgatari.zadaca_3.TjedniRasporedSingleton;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.TvKuca;
import org.foi.uzdiz.filgatari.zadaca_3.Vrsta;

/**
 *
 * @author filip
 */
public class PreuzmiPrograme implements Preuzmi {

    IspisOsobaUlogaSingleton ispisOsobaUloga = IspisOsobaUlogaSingleton.getInstance();

    @Override
    public void preuzmi(String putanja) {
        System.out.println("----------------");
        System.out.println("PREUZIMAM PROGRAM ");
        System.out.println("----------------");
        TjedniRasporedSingleton raspored = TjedniRasporedSingleton.getInstance();
        List<Program> listaPrograma = new ArrayList<>();
        List<String> popisPrograma = Pomocna.preuzmiPodatkeDatoteke(putanja);
        Integer red = 0;
        TvKuca kuca = (TjedniRasporedSingleton.getInstance().listaProgramaTvKuce
                .get(TjedniRasporedSingleton.getInstance().listaProgramaTvKuce.size() - 1));
        if (popisPrograma.isEmpty()) {
            System.out.println("Datoteka s popisom programa je prazna!");
            System.exit(1);
        }
        for (int dan = 1; dan <= 7; dan++) {
            //nije baš najbolje riješenje ali radi ¯\_(ツ)_/¯
            DaniUTjednu d = new DaniUTjednu(dan);
            kuca.daniEmitiranja.add(d);
            for (String redak : popisPrograma) {
                if (!redak.contains(";")) {
                    red++;
                    System.out.println("ERROR! Pogrešan delimiter!"
                            + " Potrebno je koristiti znak ; za delimiter.");
                } else {
                    dodajProgram(red, redak, listaPrograma, dan, kuca);
                }
            }
            programi = listaPrograma;
        }
    }

    private void dodajProgram(Integer red, String redak,
            List<Program> listaPrograma, Integer dan, TvKuca kuca) {
        red++;
        String[] program = redak.replaceAll("\\s+", "").split(";");
        Integer delimiter = program.length;
        if (delimiter >= 1) {
            Program p = new Program();
            try {
                String id = program[0].replaceAll("\\s+", "");
                p.id = Integer.parseInt(id);
            } catch (Exception e) {
                System.out.println("ERROR! ID emisije nije cijeli broj!" + program[0]);
            }
            if (delimiter >= 2) {
                p.daniUTjednu = preuzmiDaneUTjednu(program[1].replaceAll("\\s+", ""));
                if (delimiter >= 3) {
                    p.pocetak = pretvoriStringULocalTime(program[2]);
                    p.osobaUloga = null;
                    if (delimiter >= 4) {
                        String[] osobaUlogaZajedno = program[3].split(",");
                        List<OsobaUloga> listaOsobaIUloga = dodajOsobaUloga(osobaUlogaZajedno);
                        p.osobaUloga = listaOsobaIUloga;
                    }
                }
                provjeriDanPrograma(p, dan, listaPrograma, kuca);
            } 
            //TjedniRaspored raspored = TjedniRasporedSingleton.getInstance();
            //raspored.dodaj(p);
        }
        else {
                System.out.println("ERROR! Pogreška u formatu pisanja atributa programa u "
                        + red + ". retku. " + delimiter + "/4 atributa");
            }
    }

    private void provjeriDanPrograma(Program p, Integer dan, List<Program> listaPrograma,
            TvKuca kuca) {
        if (p.daniUTjednu != null) {
            for (int i = 0; i < p.daniUTjednu.length; i++) {
                if (p.daniUTjednu[i] == dan) {
                    listaPrograma.add(p);
                    programi = listaPrograma;
                    preuzmiEmisije(p, dan, kuca);
                    System.out.println("Preuzet program " + ispisiEmisiju(p.id)
                            + ", dani emitiranja = " + ispisiDaneEmitiranjaNazivi(p.daniUTjednu)
                            + ", pocetak = " + p.pocetak
                            + ", " + ispisOsobaUloga.ispisiOsobaUloga(p.osobaUloga));
                }
            }
        }
    }

    private void preuzmiEmisije(Program p, Integer dan, TvKuca kuca) {
        TjedniRaspored raspored = new TjedniRaspored();
        if (p.daniUTjednu != null) {
            for (DaniUTjednu daniEmitiranja : kuca.daniEmitiranja) {
                for (Emisija e : emisije) {
                    if (e.id.equals(p.id)) {
                        if (dan == daniEmitiranja.id) {
                            postaviPrioritete(p, e, daniEmitiranja, kuca, raspored);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void postaviPrioritete(Program p, Emisija e, DaniUTjednu daniEmitiranja,
            TvKuca kuca, TjedniRaspored raspored) {
        List<EmisijaUProgramu> prioritet1 = new ArrayList<>();
        List<EmisijaUProgramu> prioritet2 = new ArrayList<>();
        Vrsta vrsta = new Vrsta();
        for (Vrsta v : vrste) {
            if (v.id.equals(e.vrsta)) {
                vrsta.setId(v.id);
                vrsta.setNaziv(v.naziv);
                vrsta.setReklama(v.reklama);
                vrsta.setTrajanjeReklama(v.trajanjeReklama);
            }
        }
        if (p.daniUTjednu != null && p.daniUTjednu.length >= 1 && p.pocetak != null) {
            //Emisija se emitira jedan ili vise dana u tjednu 
            EmisijaUProgramu eup = new EmisijaUProgramu(e.id, e.naziv, e.trajanje, e.osobaUloga,
                    vrsta, p.pocetak, kuca.pocetak, kuca.id, 0);
            if (eup.kraj.isAfter(kuca.pocetak) && eup.kraj.isBefore(kuca.kraj)
                    && eup.kraj.isAfter(kuca.pocetak) && eup.kraj.isBefore(kuca.kraj)) {
                raspored.dodaj(eup);
                daniEmitiranja.listaEmisija.add(eup);
            }
        } else if (p.daniUTjednu != null && p.daniUTjednu.length >= 1 && p.pocetak == null) {
            //Programu nije zadan pocetak emitiranja
            EmisijaUProgramu eup = new EmisijaUProgramu(e.id, e.naziv, e.trajanje, e.osobaUloga,
                    vrsta, p.pocetak, kuca.pocetak, kuca.id, 1);
            raspored.dodaj(eup);
            prioritet1.add(eup);
            kreirajRaspored1(prioritet1, daniEmitiranja);
        } else {
            //Emisijama nisu zadani dani a programu nije zadan pocetak emitiranja
            EmisijaUProgramu eup = new EmisijaUProgramu(e.id, e.naziv, e.trajanje, e.osobaUloga,
                    vrsta, p.pocetak, kuca.pocetak, kuca.id, 2);
            raspored.dodaj(eup);
            prioritet2.add(eup);
            kreirajRaspored2(prioritet2, daniEmitiranja);
        }
        daniEmitiranja.listaEmisija.sort(Comparator.comparing((EmisijaUProgramu emp) -> emp.pocetak));
    }

    private void kreirajRaspored1(List<EmisijaUProgramu> prioritet1, DaniUTjednu daniEmitiranja) {
        daniEmitiranja.listaEmisija.sort(Comparator.comparing((EmisijaUProgramu e) -> e.pocetak));
        boolean zauzetPocetak = false;
        for (EmisijaUProgramu emisijaBezPocetka : prioritet1) {
            for (int i = 0; i < daniEmitiranja.listaEmisija.size() - 1; i++) {
                EmisijaUProgramu trenutnaEmisija = daniEmitiranja.listaEmisija.get(i);
                EmisijaUProgramu sljedecaEmisija = daniEmitiranja.listaEmisija.get(i + 1);
                if (trenutnaEmisija.pocetak.equals(emisijaBezPocetka.pocetakPrograma)) {
                    zauzetPocetak = true;
                }
                if (!zauzetPocetak) {
                    if (emisijaBezPocetka.pocetakPrograma.plusMinutes(emisijaBezPocetka.trajanje)
                            .isBefore(trenutnaEmisija.pocetak.minusNanos(2))) {
                        emisijaBezPocetka.setPocetak(emisijaBezPocetka.pocetakPrograma);
                        emisijaBezPocetka.setKraj(emisijaBezPocetka.pocetak
                                .plusMinutes(emisijaBezPocetka.trajanje));
                        daniEmitiranja.listaEmisija.add(emisijaBezPocetka);
                        zauzetPocetak = true;
                        break;
                    }
                }
                if (trenutnaEmisija.kraj.plusMinutes(emisijaBezPocetka.trajanje)
                        .isBefore(sljedecaEmisija.pocetak.minusNanos(2))) {
                    emisijaBezPocetka.setPocetak(trenutnaEmisija.kraj);
                    emisijaBezPocetka.setKraj(emisijaBezPocetka.pocetak
                            .plusMinutes(emisijaBezPocetka.trajanje));
                    if (emisijaBezPocetka.kraj.isBefore(sljedecaEmisija.pocetak.plusNanos(2))) {
                        daniEmitiranja.listaEmisija.add(emisijaBezPocetka);
                        break;
                    }
                }
                if (trenutnaEmisija.kraj.isAfter(sljedecaEmisija.pocetak.plusNanos(2))) {
                    daniEmitiranja.listaEmisija.remove(sljedecaEmisija);
                }
            }
        }
    }

    private void kreirajRaspored2(List<EmisijaUProgramu> prioritet2, DaniUTjednu daniEmitiranja) {
        daniEmitiranja.listaEmisija.sort(Comparator.comparing((EmisijaUProgramu e) -> e.pocetak));
        for (EmisijaUProgramu emisijaBezDana : prioritet2) {
            for (int i = 0; i < daniEmitiranja.listaEmisija.size() - 1; i++) {
                EmisijaUProgramu trenutnaEmisija = daniEmitiranja.listaEmisija.get(i);
                EmisijaUProgramu sljedecaEmisija = daniEmitiranja.listaEmisija.get(i + 1);
                if (emisijaBezDana.pocetakPrograma.plusMinutes(emisijaBezDana.trajanje)
                        .isBefore(trenutnaEmisija.pocetak.minusNanos(2))) {
                    emisijaBezDana.setPocetak(emisijaBezDana.pocetakPrograma);
                    emisijaBezDana.setKraj(emisijaBezDana.pocetak
                            .plusMinutes(emisijaBezDana.trajanje));
                    daniEmitiranja.listaEmisija.add(emisijaBezDana);
                    break;
                }
                if (trenutnaEmisija.kraj.plusMinutes(emisijaBezDana.trajanje)
                        .isBefore(sljedecaEmisija.pocetak.minusNanos(2))) {
                    emisijaBezDana.setPocetak(trenutnaEmisija.kraj);
                    emisijaBezDana.setKraj(emisijaBezDana.pocetak
                            .plusMinutes(emisijaBezDana.trajanje));
                    if (emisijaBezDana.kraj.isBefore(sljedecaEmisija.pocetak.plusNanos(2))) {
                        daniEmitiranja.listaEmisija.add(emisijaBezDana);
                        break;
                    }
                }
                if (trenutnaEmisija.kraj.isAfter(sljedecaEmisija.pocetak.plusNanos(2))) {
                    daniEmitiranja.listaEmisija.remove(sljedecaEmisija);
                }
            }
        }
    }

    private void kreirajRaspored(List<EmisijaUProgramu> prioritet1, DaniUTjednu daniEmitiranja) {
        daniEmitiranja.listaEmisija.sort(Comparator.comparing((EmisijaUProgramu e) -> e.pocetak));
        for (EmisijaUProgramu emisijaBezPocetka : prioritet1) {
            for (int i = 0; i < daniEmitiranja.listaEmisija.size() - 1; i++) {
                EmisijaUProgramu trenutnaEmisija = daniEmitiranja.listaEmisija.get(i);
                EmisijaUProgramu sljedecaEmisija = daniEmitiranja.listaEmisija.get(i + 1);
                if (trenutnaEmisija.kraj.plusMinutes(emisijaBezPocetka.trajanje)
                        .isBefore(sljedecaEmisija.pocetak.minusNanos(2))) {
                    emisijaBezPocetka.setPocetak(trenutnaEmisija.kraj);
                    emisijaBezPocetka.setKraj(emisijaBezPocetka.pocetak
                            .plusMinutes(emisijaBezPocetka.trajanje));
                    if (emisijaBezPocetka.kraj.isBefore(sljedecaEmisija.pocetak.plusNanos(2))) {
                        daniEmitiranja.listaEmisija.add(emisijaBezPocetka);
                        break;
                    }
                }
                if (trenutnaEmisija.kraj.isAfter(sljedecaEmisija.pocetak.plusNanos(2))) {
                    daniEmitiranja.listaEmisija.remove(sljedecaEmisija);
                }
            }
        }
    }
}
