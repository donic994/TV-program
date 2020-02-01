/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.MVC;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.foi.uzdiz.filgatari.zadaca_3.ChainOfResponsibility.Chain;
import org.foi.uzdiz.filgatari.zadaca_3.ChainOfResponsibility.DugaEmisija;
import org.foi.uzdiz.filgatari.zadaca_3.ChainOfResponsibility.KratkaEmisija;
import org.foi.uzdiz.filgatari.zadaca_3.ChainOfResponsibility.SrednjeDugaEmisija;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.DaniUTjednu;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.EmisijaUProgramu;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.RasporedComposite;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.TjedniRaspored;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.TvKuca;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.BasicDecorator;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.Decorator;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.DecoratorEmisijaUProgramu;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.DecoratorOsobaUloga;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.DecoratorReklame;
import org.foi.uzdiz.filgatari.zadaca_3.Emisija;
import org.foi.uzdiz.filgatari.zadaca_3.Iterator.SveEmisijeIterator;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.caretaker;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.emisije;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.originator;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.osobe;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.raspored;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.uloge;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.vrste;
import org.foi.uzdiz.filgatari.zadaca_3.Memento.Memento;
import org.foi.uzdiz.filgatari.zadaca_3.Osoba;
import org.foi.uzdiz.filgatari.zadaca_3.OsobaUloga;
import org.foi.uzdiz.filgatari.zadaca_3.TjedniRasporedSingleton;
import org.foi.uzdiz.filgatari.zadaca_3.Uloga;
import org.foi.uzdiz.filgatari.zadaca_3.Visitor.ElementVisitor;
import org.foi.uzdiz.filgatari.zadaca_3.Visitor.ElementVisitorImpl;
import org.foi.uzdiz.filgatari.zadaca_3.Vrsta;

/**
 *
 * @author filip
 */
public class Controler {

    private DefaultPogled defaultPogled;
    private FormatPogled formatPogled;
    public static Integer linija;
    public static Integer pogled;

    public Controler() {
        linija = 1;
        pogled = 1;
        PrikaziPogled();
    }

    public static Integer getPogled() {
        return pogled;
    }

    public static void setPogled(Integer pogled) {
        Controler.pogled = pogled;
    }

    private void PrikaziPogled() {
        defaultPogled = new DefaultPogled();
        formatPogled = new FormatPogled();
        Integer odabir;

        korisnikckiIzbornik();
    }

    public void korisnikckiIzbornik() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer odabir = 10;
        while (odabir != 0) {
            if (pogled.equals(1)) {
                defaultPogled.korisnikckiIzbornik();
            }

            if (pogled.equals(2)) {
                formatPogled.korisnikckiIzbornik();
            }

            try {
                odabir = Integer.parseInt(br.readLine());
            } catch (Exception ex) {
                if (pogled.equals(1)) {
                    defaultPogled.greskaKorisnickiIzbiornik();
                }
                if (pogled.equals(2)) {
                    formatPogled.greskaKorisnickiIzbiornik();
                }
            }
            odabirIspisa(odabir);
        }
    }

    public void odabirIspisa(Integer odabir) {
        switch (odabir) {
            case 1: {
                ispisiVremeskiPlan();
                break;
            }
            case 2: {
                ispisiPotencijalnePrihodeOdReklama();
                break;
            }
            case 3: {
                ispisiTjedniPlanPoVrsti();
                break;
            }
            case 4: {
                zamjeniUloguOsobiUEmisiji();
                break;
            }
            case 5: {
                obrisiEmisijuPremaRbr();
                break;
            }
            case 6: {
                ispisiPodatkePohranjivanja();
                break;
            }
            case 7: {
                vratiPodatkePohranjivanja();
                break;
            }
            case 8: {
                ispisiKategorijeTrajanja();
                break;
            }
            case 9: {
                promjeniPogled();
                break;
            }
            case 0: {
                if (pogled.equals(1)) {
                    defaultPogled.izlaz();
                }
                if (pogled.equals(2)) {
                    formatPogled.izlaz();
                }
                break;
            }
            default:
                if (pogled.equals(1)) {
                    defaultPogled.greskaKorisnickiIzbiornik();
                }
                if (pogled.equals(2)) {
                    formatPogled.greskaKorisnickiIzbiornik();
                }
                break;
        }
    }

    public void ispisiVremeskiPlan() {

        if (pogled.equals(1)) {
            defaultPogled.ispisiVremeskiPlan();
        }
        if (pogled.equals(2)) {
            formatPogled.ispisiVremeskiPlan();
        }

        boolean pogresanUnos = true, programPostoji = false;
        Integer program = null, dan = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (pogresanUnos) {
            try {
                if (pogled.equals(1)) {
                    defaultPogled.unosPrograma();
                }
                if (pogled.equals(2)) {
                    formatPogled.unosPrograma();
                }
                program = Integer.parseInt(br.readLine());
                if (pogled.equals(1)) {
                    defaultPogled.unosDana();
                }
                if (pogled.equals(2)) {
                    formatPogled.unosDana();
                }
                dan = Integer.parseInt(br.readLine());
                pogresanUnos = false;
            } catch (Exception e) {
                if (pogled.equals(1)) {
                    defaultPogled.greskaCijeliBroj();
                }
                if (pogled.equals(2)) {
                    formatPogled.greskaCijeliBroj();
                }
            }
            pogresanUnos = provjeriProgramIDan(dan, pogresanUnos, program, programPostoji);
        }
        ispisiProgramDana(program, dan);
    }

    public void ispisiProgrameIDaneZaOdabir() {
        for (RasporedComposite r : raspored.getChildren()) {
            TvKuca t = (TvKuca) r;
            System.out.println("ID = " + t.id + ", Naziv =" + t.nazivPrograma);
        }

        System.out.println("Pon=1, Uto=2, Sri=3, Cet=4, Pet=5, Sub=6, Ned=7");
        System.out.println("------------------------------------------------");
    }

    public void ispisiProgramDana(Integer program, Integer dan) {
        Integer indexPrograma = 0, indexDana = 0;
        TvKuca t = null;
        DaniUTjednu dut = null;

        for (RasporedComposite r : raspored.getChildren()) {
            t = (TvKuca) r;
            if (t.id.equals(program)) {
                indexPrograma = raspored.getChildren().indexOf(t);
                if (pogled.equals(1)) {
                    defaultPogled.programPrikazujeUDanu(t, dan);
                }
                if (pogled.equals(2)) {
                    formatPogled.programPrikazujeUDanu(t, dan);
                }
                for (RasporedComposite rc : r.getChildren()) {
                    dut = (DaniUTjednu) rc;
                    if (dut.id.equals(dan)) {
                        indexDana = r.getChildren().indexOf(dut);
                        SveEmisijeIterator iterator = new SveEmisijeIterator(raspored.getChild(indexPrograma));
                        while (iterator.hasNext()) {
                            EmisijaUProgramu emisija = (EmisijaUProgramu) iterator.getNext();
                            if (raspored.getChild(indexPrograma).getChild(indexDana)
                                    .getChildren().contains(emisija)) {
                                Decorator emisijaDecorator = new DecoratorEmisijaUProgramu(new BasicDecorator(1));
                                emisijaDecorator.add(emisija);
                            }
                        }
                    }
                }
            }
        }
    }

    public void ispisiPotencijalnePrihodeOdReklama() {
        if (pogled.equals(1)) {
            defaultPogled.ispisiPotencijalnePrihodeOdReklama();
        }
        if (pogled.equals(2)) {
            formatPogled.ispisiPotencijalnePrihodeOdReklama();
        }
        boolean pogresanUnos = true, programPostoji = false;
        Integer program = null, dan = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (pogresanUnos) {
            try {
                if (pogled.equals(1)) {
                    defaultPogled.unosPrograma();
                }
                if (pogled.equals(2)) {
                    formatPogled.unosPrograma();
                }
                program = Integer.parseInt(br.readLine());
                if (pogled.equals(1)) {
                    defaultPogled.unosDana();
                }
                if (pogled.equals(2)) {
                    formatPogled.unosDana();
                }
                dan = Integer.parseInt(br.readLine());
                pogresanUnos = false;
            } catch (Exception e) {
                if (pogled.equals(1)) {
                    defaultPogled.greskaCijeliBroj();
                }
                if (pogled.equals(2)) {
                    formatPogled.greskaCijeliBroj();
                }
            }
            pogresanUnos = provjeriProgramIDan(dan, pogresanUnos, program, programPostoji);
        }
        ispisiPrihodeReklama(program, dan);
    }

    public boolean provjeriProgramIDan(Integer dan, boolean pogresanUnos,
            Integer program, boolean programPostoji) {
        if (dan < 1 || dan > 7) {
            pogresanUnos = true;
            if (pogled.equals(1)) {
                defaultPogled.greskaTjedan();
            }
            if (pogled.equals(2)) {
                formatPogled.greskaTjedan();
            }
        }
        for (RasporedComposite r : raspored.getChildren()) {
            TvKuca t = (TvKuca) r;
            if (t.id.equals(program)) {
                programPostoji = true;
                break;
            }
        }
        if (!pogresanUnos && programPostoji) {
            pogresanUnos = false;
        } else {
            pogresanUnos = true;
            if (pogled.equals(1)) {
                defaultPogled.greskaIdPrograma();
            }
            if (pogled.equals(2)) {
                formatPogled.greskaIdPrograma();
            }
        }
        return pogresanUnos;
    }

    public void ispisiPrihodeReklama(Integer program, Integer dan) {
        Integer indexPrograma = 0, indexDana = 0, prihodiReklama = 0;
        ElementVisitor visitor = new ElementVisitorImpl();

        TvKuca t = null;
        DaniUTjednu dut = null;
        for (RasporedComposite r : raspored.getChildren()) {
            t = (TvKuca) r;
            if (t.id.equals(program)) {
                indexPrograma = raspored.getChildren().indexOf(t);
                for (RasporedComposite rc : r.getChildren()) {
                    dut = (DaniUTjednu) rc;
                    if (dut.id.equals(dan)) {
                        indexDana = r.getChildren().indexOf(dut);
                        SveEmisijeIterator iterator = new SveEmisijeIterator(raspored.getChild(indexPrograma));
                        while (iterator.hasNext()) {
                            EmisijaUProgramu emisija = (EmisijaUProgramu) iterator.getNext();
                            if (raspored.getChild(indexPrograma).getChild(indexDana)
                                    .getChildren().contains(emisija)) {
                                if (emisija.vrsta.reklama.equals(1) && emisija.vrsta.trajanjeReklama != null) {
                                    Decorator emisijaDecorator = new DecoratorReklame(new BasicDecorator(3));
                                    emisijaDecorator.add(emisija);
                                    prihodiReklama = emisija.accept(visitor);
                                }
                            }
                        }
                    }
                }
            }
            if (pogled.equals(1)) {
                defaultPogled.ispisiUkupnePrihodeReklama(t, dan, prihodiReklama);
            }
            if (pogled.equals(2)) {
                formatPogled.ispisiUkupnePrihodeReklama(t, dan, prihodiReklama);
            }
            break;
        }
    }

    public void ispisiTjedniPlanPoVrsti() {
        if (pogled.equals(1)) {
            defaultPogled.ispisiTjedniPlanPoVrsti();
        }
        if (pogled.equals(2)) {
            formatPogled.ispisiTjedniPlanPoVrsti();
        }
        boolean pogresanUnos = true, vrstaPostoji = false;
        Integer vrsta = null;
        Vrsta vrstaO = new Vrsta();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (pogresanUnos) {
            try {
                if (pogled.equals(1)) {
                    defaultPogled.unosVrste();
                }
                if (pogled.equals(2)) {
                    formatPogled.unosVrste();
                }
                vrsta = Integer.parseInt(br.readLine());
                pogresanUnos = false;
            } catch (Exception e) {
                if (pogled.equals(1)) {
                    defaultPogled.greskaCijeliBroj();
                }
                if (pogled.equals(2)) {
                    formatPogled.greskaCijeliBroj();
                }
            }
            for (Vrsta v : vrste) {
                if (v.id.equals(vrsta)) {
                    vrstaO = v;
                }
            }
            vrstaPostoji = vrste.contains(vrstaO);
            if (!pogresanUnos && vrstaPostoji) {
                if (pogled.equals(1)) {
                    defaultPogled.ispisiOdabranuVrstu(vrstaO);
                }
                if (pogled.equals(2)) {
                    formatPogled.ispisiOdabranuVrstu(vrstaO);
                }
                ispisiEmisijePoVrsti(vrsta);
                pogresanUnos = false;
            } else {
                pogresanUnos = true;
                if (pogled.equals(1)) {
                    defaultPogled.greskaIdVrste();
                }
                if (pogled.equals(2)) {
                    formatPogled.greskaIdVrste();
                }
            }
        }
    }

    public void ispisiEmisijePoVrsti(Integer vrsta) throws NoSuchElementException {
        Integer indexPrograma = 0, indexDana = 0;

        TvKuca t = null;
        DaniUTjednu dut = null;
        for (RasporedComposite r : raspored.getChildren()) {
            t = (TvKuca) r;
            indexPrograma = raspored.getChildren().indexOf(t);
            for (RasporedComposite rc : r.getChildren()) {
                dut = (DaniUTjednu) rc;
                indexDana = r.getChildren().indexOf(dut);
                SveEmisijeIterator iterator = new SveEmisijeIterator(raspored.getChild(indexPrograma));
                while (iterator.hasNext()) {
                    EmisijaUProgramu emisija = (EmisijaUProgramu) iterator.getNext();
                    if (raspored.getChild(indexPrograma).getChild(indexDana)
                            .getChildren().contains(emisija) && emisija.vrsta.id.equals(vrsta)) {
                        Decorator emisijaDecorator = new DecoratorEmisijaUProgramu(new BasicDecorator(1));
                        emisijaDecorator.add(emisija);
                    }
                }
            }
        }
    }

    public void zamjeniUloguOsobiUEmisiji() {
        if (pogled.equals(1)) {
            defaultPogled.zamjeniUloguOsobiUEmisiji();
        }
        if (pogled.equals(2)) {
            formatPogled.zamjeniUloguOsobiUEmisiji();
        }
        ispisiOsobeUloge();
        boolean pogresanUnos = true;
        Integer osoba = 0, staraUloga = 0, novaUloga = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (pogresanUnos) {
            try {
                if (pogled.equals(1)) {
                    defaultPogled.unosOsoba();
                }
                if (pogled.equals(2)) {
                    formatPogled.unosOsoba();
                }
                osoba = Integer.parseInt(br.readLine());
                if (pogled.equals(1)) {
                    defaultPogled.unosStaraUloga();
                }
                if (pogled.equals(2)) {
                    formatPogled.unosStaraUloga();
                }
                staraUloga = Integer.parseInt(br.readLine());
                if (pogled.equals(1)) {
                    defaultPogled.unosNovaUloga();
                }
                if (pogled.equals(2)) {
                    formatPogled.unosNovaUloga();
                }
                novaUloga = Integer.parseInt(br.readLine());
                pogresanUnos = false;
            } catch (Exception e) {
                if (pogled.equals(1)) {
                    defaultPogled.greskaCijeliBroj();
                }
                if (pogled.equals(2)) {
                    formatPogled.greskaCijeliBroj();
                }
            }
            for (Osoba o : osobe) {
                if (o.id.equals(osoba)) {
                    for (Uloga u : uloge) {
                        if (u.id.equals(staraUloga)) {
                            for (Uloga u2 : uloge) {
                                if (u2.id.equals(novaUloga)) {
                                    if (pogled.equals(1)) {
                                        defaultPogled.ispisiPromjenuUloge(o, u2);
                                    }
                                    if (pogled.equals(2)) {
                                        formatPogled.ispisiPromjenuUloge(o, u2);
                                    }
                                    o.setState(u2.getOpis());
                                    pogresanUnos = false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void registriraj(EmisijaUProgramu emisija) {
        if (emisija.osobaUloga != null) {
            List<OsobaUloga> nova = new ArrayList<OsobaUloga>();
            if (emisija.osobaUloga != null) {
                for (OsobaUloga osobaUloga : emisija.osobaUloga) {
                    for (Osoba o : osobe) {
                        if (o.id.equals(osobaUloga.osoba)) {
                            o.register(emisija);
                            for (Uloga u : uloge) {
                                if (u.id.equals(osobaUloga.uloga)) {
                                    o.uloga = u.opis;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void ispisiOsobeUloge() {
        List<OsobaUloga> nova = new ArrayList<OsobaUloga>();

        for (Osoba o : osobe) {
            for (Uloga u : uloge) {
                if (u.opis.equals(o.uloga)) {
                    OsobaUloga ou = new OsobaUloga();
                    ou.osoba = o.id;
                    ou.uloga = u.id;
                    if (nova.size() < 1 || nova == null) {
                        nova.add(ou);
                    } else {
                        if (!nova.contains(ou)) {
                            nova.add(ou);
                        }
                    }
                }
            }
        }
        for (TvKuca program : TjedniRasporedSingleton.getInstance().listaProgramaTvKuce) {
            for (DaniUTjednu daniEmitiranja : program.daniEmitiranja) {
                for (EmisijaUProgramu emisija : daniEmitiranja.listaEmisija) {
                    for (OsobaUloga n : nova) {
                        if (emisija.osobaUloga != null) {
                            for (OsobaUloga ou : emisija.osobaUloga) {
                                if (ou.osoba.equals(n.osoba) && ou.uloga.equals(n.uloga)) {
                                    Decorator emisijaDecorator = new DecoratorOsobaUloga(new BasicDecorator(2));
                                    emisijaDecorator.add(emisija);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void obrisiEmisijuPremaRbr() {
        if (pogled.equals(1)) {
            defaultPogled.obrisiEmisijuPremaRbr();
        }
        if (pogled.equals(2)) {
            formatPogled.obrisiEmisijuPremaRbr();
        }
        Integer indexPrograma = 0, indexDana = 0, uneseniBroj = 0;
        TvKuca t = null;
        DaniUTjednu dut = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            if (pogled.equals(1)) {
                defaultPogled.unosRbrEmisije();
            }
            if (pogled.equals(2)) {
                formatPogled.unosRbrEmisije();
            }
            uneseniBroj = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            if (pogled.equals(1)) {
                defaultPogled.greskaCijeliBroj();
            }
            if (pogled.equals(2)) {
                formatPogled.greskaCijeliBroj();
            }
        }
        for (RasporedComposite program : raspored.getChildren()) {
            t = (TvKuca) program;
            indexPrograma = raspored.getChildren().indexOf(t);
            for (RasporedComposite dan : program.getChildren()) {
                dut = (DaniUTjednu) dan;
                indexDana = program.getChildren().indexOf(dut);
                SveEmisijeIterator iterator = new SveEmisijeIterator(raspored.getChild(indexPrograma));
                while (iterator.hasNext()) {
                    EmisijaUProgramu emisija = (EmisijaUProgramu) iterator.getNext();
                    if (raspored.getChild(indexPrograma).getChild(indexDana)
                            .getChildren().contains(emisija) && emisija.redniBroj.equals(uneseniBroj)) {
                        TjedniRaspored novi = (TjedniRaspored) klonirajObjekt(raspored);
                        originator.setState(novi, uneseniBroj);
                        caretaker.addMemento(originator.save());
                        dut.obrisi(emisija);
                    }
                }
            }
        }
    }

    public void ispisiPodatkePohranjivanja() {
        if (pogled.equals(1)) {
            defaultPogled.ispisiPodatkePohranjivanja();
        }
        if (pogled.equals(2)) {
            formatPogled.ispisiPodatkePohranjivanja();
        }
        Integer ukupnoPohranjivanja = caretaker.mementoList.size();

        for (Memento m : caretaker.mementoList) {
            if (pogled.equals(1)) {
                defaultPogled.ispisiPohranjivanjeInfo(m);
            }
            if (pogled.equals(2)) {
                formatPogled.ispisiPohranjivanjeInfo(m);
            }
        }
        if (pogled.equals(1)) {
            defaultPogled.ispisiPohranjivanjeUkupno(ukupnoPohranjivanja);
        }
        if (pogled.equals(2)) {
            formatPogled.ispisiPohranjivanjeUkupno(ukupnoPohranjivanja);
        }
    }

    public Object klonirajObjekt(Object object) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(object);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return objInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void vratiPodatkePohranjivanja() {
        if (pogled.equals(1)) {
            defaultPogled.vratiPodatkePohranjivanja();
        }
        if (pogled.equals(2)) {
            formatPogled.vratiPodatkePohranjivanja();
        }
        Integer uneseniBroj = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            if (pogled.equals(1)) {
                defaultPogled.unosJednoznacniBroj();
            }
            if (pogled.equals(2)) {
                formatPogled.unosJednoznacniBroj();
            }
            uneseniBroj = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            if (pogled.equals(1)) {
                defaultPogled.greskaCijeliBroj();
            }
            if (pogled.equals(2)) {
                formatPogled.greskaCijeliBroj();
            }
        }
        for (Memento m : caretaker.mementoList) {
            if (m.brojPohranjivanja.equals(uneseniBroj)) {
                originator.restore(caretaker.getMemento(m.brojPohranjivanja - 1));
                raspored = originator.getState();
                if (pogled.equals(1)) {
                    defaultPogled.ispisiVracanjeStanja(m);
                }
                if (pogled.equals(2)) {
                    formatPogled.ispisiVracanjeStanja(m);
                }
            }
        }
    }

    public void ispisiKategorijeTrajanja() {
        if (pogled.equals(1)) {
            defaultPogled.ispisiKategorijeTrajanja();
        }
        if (pogled.equals(2)) {
            formatPogled.ispisiKategorijeTrajanja();
        }
        Chain c1 = new KratkaEmisija();
        Chain c2 = new SrednjeDugaEmisija();
        Chain c3 = new DugaEmisija();
        c1.setNext(c2);
        c2.setNext(c3);

        for (Emisija e : emisije) {
            if (pogled.equals(1)) {
                defaultPogled.ispisiKategorijuTrajanjaEmisije(c1, e);
            }
            if (pogled.equals(2)) {
                formatPogled.ispisiKategorijuTrajanjaEmisije(c1, e);
            }
        }
    }

    public void promjeniPogled() {
        if (pogled.equals(2)) {
            defaultPogled.promjeniPogled();
        }
        if (pogled.equals(1)) {
            formatPogled.promjeniPogled();
        }
        if (Controler.pogled.equals(1)) {
            Controler.setPogled(2);
        } else if (Controler.pogled.equals(2)) {
            Controler.setPogled(1);
        }
    }

    public void ispisiString(String tekst) {
        if (pogled.equals(1)) {
            defaultPogled.ispisiString(tekst);
        }
        if (pogled.equals(2)) {
            formatPogled.ispisiString(tekst);
        }
    }
}
