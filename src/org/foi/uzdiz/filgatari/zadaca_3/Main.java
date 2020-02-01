package org.foi.uzdiz.filgatari.zadaca_3;

import org.foi.uzdiz.filgatari.zadaca_3.Composite.TvKuca;
import org.foi.uzdiz.filgatari.zadaca_3.FactoryMethod.PreuzimanjeFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.filgatari.zadaca_3.ChainOfResponsibility.Chain;
import org.foi.uzdiz.filgatari.zadaca_3.ChainOfResponsibility.DugaEmisija;
import org.foi.uzdiz.filgatari.zadaca_3.ChainOfResponsibility.KratkaEmisija;
import org.foi.uzdiz.filgatari.zadaca_3.ChainOfResponsibility.SrednjeDugaEmisija;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.DaniUTjednu;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.EmisijaUProgramu;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.RasporedComposite;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.TjedniRaspored;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.BasicDecorator;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.Decorator;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.DecoratorEmisijaUProgramu;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.DecoratorOsobaUloga;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.DecoratorReklame;
import org.foi.uzdiz.filgatari.zadaca_3.FactoryMethod.Preuzmi;
import org.foi.uzdiz.filgatari.zadaca_3.Iterator.SveEmisijeIterator;
import org.foi.uzdiz.filgatari.zadaca_3.MVC.Controler;
import org.foi.uzdiz.filgatari.zadaca_3.Memento.Caretaker;
import org.foi.uzdiz.filgatari.zadaca_3.Memento.Memento;
import org.foi.uzdiz.filgatari.zadaca_3.Memento.Originator;
import static org.foi.uzdiz.filgatari.zadaca_3.Pomocna.pretvoriBrojUDanTjedna;
import org.foi.uzdiz.filgatari.zadaca_3.Visitor.ElementVisitor;
import org.foi.uzdiz.filgatari.zadaca_3.Visitor.ElementVisitorImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author filip
 */
public class Main {

    public static List<Program> programi;
    public static List<TvKuca> tvKuce;
    public static List<Uloga> uloge;
    public static List<Osoba> osobe;
    public static List<Emisija> emisije;
    public static List<Vrsta> vrste;
    public static List<OsobaUloga> osobeSUlogama = new ArrayList<OsobaUloga>();
    public static TjedniRaspored raspored = new TjedniRaspored();
    public static Originator originator = new Originator();
    public static Caretaker caretaker = new Caretaker();
    public static boolean promjenaStanja = false;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        if (provjeriUneseneParametre(args)) {
            preuzmiPodatkeIzDatoteka(args);
            popuniComposite(raspored);
            //korisnikckiIzbornik();
            Controler c = new Controler();
        }
    }

    private static void preuzmiPodatkeIzDatoteka(String[] args) {
        PreuzimanjeFactory preuzimanje = new PreuzimanjeFactory();
        Preuzmi preuzmiOsobe = preuzimanje.getTipPreuzimanja("osoba");
        Preuzmi preuzmiUloge = preuzimanje.getTipPreuzimanja("uloga");
        Preuzmi preuzmiEmisije = preuzimanje.getTipPreuzimanja("emisija");
        Preuzmi preuzmiTvKuce = preuzimanje.getTipPreuzimanja("tvKuca");
        Preuzmi preuzmiVrste = preuzimanje.getTipPreuzimanja("vrsta");

        preuzmiOsobe.preuzmi(odrediPutanjuDatoteke(args, "osoba"));
        preuzmiUloge.preuzmi(odrediPutanjuDatoteke(args, "uloga"));
        preuzmiVrste.preuzmi(odrediPutanjuDatoteke(args, "vrsta"));
        preuzmiEmisije.preuzmi(odrediPutanjuDatoteke(args, "emisija"));
        preuzmiTvKuce.preuzmi(odrediPutanjuDatoteke(args, "tvKuca"));
    }

    private static String odrediPutanjuDatoteke(String[] args, String tip) {
        String putanjaDatoteke = "";
        File f = new File("");
        String putanja = f.getAbsolutePath();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-t") && tip.equals("tvKuca")) {
                putanjaDatoteke = putanja + "\\" + args[i + 1];
                if (args[i + 1].contains(":")) {
                    putanjaDatoteke = args[i + 1];
                }
            }
            if (args[i].equals("-e") && tip.equals("emisija")) {
                putanjaDatoteke = putanja + "\\" + args[i + 1];
                if (args[i + 1].contains(":")) {
                    putanjaDatoteke = args[i + 1];
                }
            }
            if (args[i].equals("-v") && tip.equals("vrsta")) {
                putanjaDatoteke = putanja + "\\" + args[i + 1];
                if (args[i + 1].contains(":")) {
                    putanjaDatoteke = args[i + 1];
                }
            }
            if (args[i].equals("-o") && tip.equals("osoba")) {
                putanjaDatoteke = putanja + "\\" + args[i + 1];
                if (args[i + 1].contains(":")) {
                    putanjaDatoteke = args[i + 1];
                }
            }
            if (args[i].equals("-u") && tip.equals("uloga")) {
                putanjaDatoteke = putanja + "\\" + args[i + 1];
                if (args[i + 1].contains(":")) {
                    putanjaDatoteke = args[i + 1];
                }
            }
        }
        return putanjaDatoteke;
    }

    private static boolean provjeriUneseneParametre(String[] args) {
        boolean parametriOk = true;
        String parametri = "";
        for (String s : args) {
            parametri += s + " ";
        }

        if (args.length == 10
                && parametri.contains("-t")
                && parametri.contains("-e")
                && parametri.contains("-v")
                && parametri.contains("-o")
                && parametri.contains("-u")) {
            parametriOk = true;
        } else {
            System.out.println("ERROR:Pogrešno uneseni argumenti!");
            parametriOk = false;
            System.exit(1);
        }
        return parametriOk;
    }

    private static void prikaziRaspored() {
        for (RasporedComposite program : raspored.getChildren()) {
            program.prikazi();
            for (RasporedComposite daniEmitiranja : program.getChildren()) {
                daniEmitiranja.prikazi();
                for (RasporedComposite emisija : daniEmitiranja.getChildren()) {
                    emisija.prikazi();
                }
            }
        }
    }

    private static void popuniComposite(TjedniRaspored raspored) {
        Integer rBr = 1;
        for (TvKuca program : TjedniRasporedSingleton.getInstance().listaProgramaTvKuce) {
            for (DaniUTjednu daniEmitiranja : program.daniEmitiranja) {
                for (EmisijaUProgramu emisija : daniEmitiranja.listaEmisija) {
                    emisija.setRedniBroj(rBr);
                    daniEmitiranja.dodaj(emisija);
                    registriraj(emisija);
                    rBr++;
                }
                program.dodaj(daniEmitiranja);
            }
            raspored.dodaj(program);
        }
    }

    private static void korisnikckiIzbornik() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer odabir = 0;
        while (odabir != 9) {
            System.out.println("---------");
            System.out.println("IZBORNIK");
            System.out.println("---------");
            System.out.println("1. Ispis vremenskog plana za program i dan u tjednu");
            System.out.println("2. Ispis potencijalnih prihoda od reklama za program i dan u tjednu");
            System.out.println("3. Ispis tjednog plana po svim programima, danima i emisijama"
                    + " za odabranu vrstu");
            System.out.println("4. Zamjena uloge osobe u emisiji");
            System.out.println("5. Brisanje emisije temeljem njezinog jednoznačnog broja");
            System.out.println("6. Ispisivanje podataka pohranjivanja");
            System.out.println("7. Vraćanje podataka temeljem jednoznačnog broja pohranjivanja");
            System.out.println("8. Ispis emisija po kategoriji trajanja");
            System.out.println("9. Izlaz iz programa");
            System.out.print("Vas odabir:");
            try {
                odabir = Integer.parseInt(br.readLine());
            } catch (IOException ex) {
                System.out.println("Molimo Vas da unesete cjelobrojni broj (1-4) ili 9 za izlaz");
            }
            odabirIspisa(odabir);
        }
    }

    private static void odabirIspisa(Integer odabir) {
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
                System.out.println("Izlaz iz programa");
                break;
            }
            default:
                System.out.println("Molimo Vas da unesete cjelobrojni broj (1-4) ili 9 za izlaz");
                break;
        }
    }

    public static String ispisiEmisiju(Integer emisija) {
        String odgovor = "", naziv = "", osoba = "", uloga = "";
        Integer trajanje = 0;
        for (Emisija e : emisije) {
            if (e.id.equals(emisija)) {
                naziv = e.naziv;
                trajanje = e.trajanje;
                odgovor = e.naziv + "(" + e.id + "), trajanje= "
                        + e.trajanje;
                if (e.osobaUloga == null) {
                    break;
                } else {
                    for (OsobaUloga ou : e.osobaUloga) {
                        for (Osoba o : osobe) {
                            if (o.id.equals(ou.osoba)) {
                                osoba = o.imeIPrezime;
                            }
                        }
                        for (Uloga u : uloge) {
                            if (u.id.equals(ou.uloga)) {
                                uloga = u.opis;
                            }
                        }
                        odgovor += ", " + uloga + " = " + osoba;
                    }
                }
            }
        }
        return odgovor;
    }

    public static LocalTime pretvoriStringULocalTime(String strVrijeme) {
        String vrijeme = strVrijeme.replaceAll("\\s+", "");
        Pattern pattern0 = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]:[0-5][0-9]$");
        Matcher m0 = pattern0.matcher(vrijeme);
        Pattern pattern1 = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$");
        Matcher m1 = pattern1.matcher(vrijeme);
        Pattern pattern2 = Pattern.compile("^([0-9]):[0-5][0-9]$");
        Matcher m2 = pattern2.matcher(vrijeme);

        LocalTime pretvorenoVrijeme = null;
        if (m0.matches()) {
            pretvorenoVrijeme = LocalTime.parse(vrijeme, DateTimeFormatter.ofPattern("HH:mm:ss"));
        } else {
            if (m1.matches()) {
                if (m2.matches()) {
                    vrijeme = "0" + vrijeme;
                }
                vrijeme = vrijeme + ":00";
                pretvorenoVrijeme = LocalTime.parse(vrijeme, DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
        }
        return pretvorenoVrijeme;
    }

    public static List<OsobaUloga> dodajOsobaUloga(String[] osobaUlogaZajedno) {
        List<OsobaUloga> listaOsobaIUloga = new ArrayList<>();
        for (String str : osobaUlogaZajedno) {
            String[] osobaUlogaOdvojeno = str.split("-");
            OsobaUloga ou = new OsobaUloga();
            try {
                String osoba = osobaUlogaOdvojeno[0].replaceAll("\\s+", "");
                ou.osoba = Integer.parseInt(osoba);
                String uloga = osobaUlogaOdvojeno[1].replaceAll("\\s+", "");
                ou.uloga = Integer.parseInt(uloga);
                listaOsobaIUloga.add(ou);
            } catch (Exception e) {
                System.out.println("ERROR! ID osobe(" + osobaUlogaOdvojeno[0]
                        + ") ili uloge(" + osobaUlogaOdvojeno[1] + ") nije cijeli broj!");
            }
        }
        return listaOsobaIUloga;
    }

    public static String preuzmiPutanjuDatotekePrograma(TvKuca tvKuca) {
        String putanjaDatoteke = "";
        putanjaDatoteke = new File("").getAbsolutePath() + "\\" + tvKuca.nazivDatoteke;
        return putanjaDatoteke;
    }

    public static Integer[] preuzmiDaneUTjednu(String daniUTjednu) {
        Integer[] daniEmitiranja = null;
        if (daniUTjednu.contains("-")) {
            daniEmitiranja = preuzmiDaneSaCrticom(daniUTjednu, daniEmitiranja);
        } else if (daniUTjednu.contains(",")) {
            String[] dani = daniUTjednu.split(",");
            Integer dan = 0;
            daniEmitiranja = new Integer[dani.length];
            daniEmitiranja = preuzmiDaneSaZarezom(dani, dan, daniEmitiranja);
        } else {
            Integer dan = 0;
            daniEmitiranja = new Integer[1];
            try {
                dan = Integer.parseInt(daniUTjednu.replaceAll("\\s+", ""));
            } catch (Exception e) {
                System.out.println("ERROR! Uneseni dani nisu cijeli broj! ");
                daniEmitiranja = null;
            }
            if (dan > 0 && dan <= 7) {
                daniEmitiranja[0] = dan;
            } else {
                System.out.println("ERROR! Zadani raspon mora biti max 1-7");
                daniEmitiranja = null;
            }
        }
        return daniEmitiranja;
    }

    private static Integer[] preuzmiDaneSaCrticom(String daniUTjednu, Integer[] daniEmitiranja) {
        String[] dani = daniUTjednu.split("-");
        Integer pocetak = 0, kraj = 0;
        try {
            pocetak = Integer.parseInt(dani[0].replaceAll("\\s+", ""));
            kraj = Integer.parseInt(dani[1].replaceAll("\\s+", ""));
        } catch (Exception e) {
            System.out.println("ERROR! Uneseni dani nisu cijeli broj!");
        }
        if (pocetak < kraj) {
            daniEmitiranja = new Integer[kraj - pocetak + 1];
            daniEmitiranja = preuzmiDaneIzNiza(pocetak, kraj, daniEmitiranja);
        } else {
            System.out.println("ERROR! Pocetak intervala treba biti manji od kraja intervala");
        }
        return daniEmitiranja;
    }

    private static Integer[] preuzmiDaneSaZarezom(String[] dani, Integer dan,
            Integer[] daniEmitiranja) {
        for (int i = 0; i < dani.length; i++) {
            try {
                dan = Integer.parseInt(dani[i].replaceAll("\\s+", ""));
            } catch (Exception e) {
                System.out.println("ERROR! Uneseni dani nisu cijeli broj!");
                daniEmitiranja = null;
            }
            if (dan > 0 && dan <= 7) {
                daniEmitiranja[i] = dan;
            } else {
                System.out.println("ERROR! Zadani raspon mora biti max 1-7");
                daniEmitiranja = null;
            }
        }
        return daniEmitiranja;
    }

    private static Integer[] preuzmiDaneIzNiza(Integer pocetak, Integer kraj,
            Integer[] daniEmitiranja) {
        int j = 0;
        for (int i = pocetak; i <= kraj; i++) {
            if (i == 1) {
                daniEmitiranja[j] = 1;
            } else if (i == 2) {
                daniEmitiranja[j] = 2;
            } else if (i == 3) {
                daniEmitiranja[j] = 3;
            } else if (i == 4) {
                daniEmitiranja[j] = 4;
            } else if (i == 5) {
                daniEmitiranja[j] = 5;
            } else if (i == 6) {
                daniEmitiranja[j] = 6;
            } else if (i == 7) {
                daniEmitiranja[j] = 7;
            } else {
                System.out.println("ERROR! Zadani raspon mora biti max 1-7");
                daniEmitiranja = null;
            }
            j++;
        }
        return daniEmitiranja;
    }

    public static String ispisiDaneEmitiranjaBrojke(Integer[] dani) {
        String odgovor = "";
        for (int i = 0; i < dani.length; i++) {
            if (i == dani.length - 1) {
                odgovor += dani[i].toString();
            } else {
                odgovor += dani[i].toString() + ", ";
            }
        }
        return odgovor;
    }

    public static String ispisiDaneEmitiranjaNazivi(Integer[] dani) {
        String odgovor = "";
        if (dani != null) {
            for (int i = 0; i < dani.length; i++) {
                if (i == dani.length - 1) {
                    odgovor += pretvoriBrojUDanTjedna(dani[i]);
                } else {
                    odgovor += pretvoriBrojUDanTjedna(dani[i]) + ", ";
                }
            }
        }
        return odgovor;
    }

    private static void ispisiVremeskiPlan() {
        System.out.println("------------------------------------------------");
        System.out.println("Ispis vremenskog plana za program i dan u tjednu");
        System.out.println("------------------------------------------------");
        ispisiProgrameIDaneZaOdabir();

        boolean pogresanUnos = true, programPostoji = false;
        Integer program = null, dan = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (pogresanUnos) {
            try {
                System.out.print("Program (id): ");
                program = Integer.parseInt(br.readLine());
                System.out.print("Dan (1-7): ");
                dan = Integer.parseInt(br.readLine());
                pogresanUnos = false;
            } catch (Exception e) {
                System.err.println("Unos nije cijeli broj!");
            }
            pogresanUnos = provjeriProgramIDan(dan, pogresanUnos, program, programPostoji);
        }
        ispisiProgramDana(program, dan);
    }

    private static void ispisiProgramDana(Integer program, Integer dan) {
        Integer indexPrograma = 0, indexDana = 0;
        TvKuca t = null;
        DaniUTjednu dut = null;

        for (RasporedComposite r : raspored.getChildren()) {
            t = (TvKuca) r;
            if (t.id.equals(program)) {
                indexPrograma = raspored.getChildren().indexOf(t);
                System.out.println(t.nazivPrograma + " u "
                        + pretvoriBrojUDanTjedna(dan) + " prikazuje:");
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

    private static void ispisiPotencijalnePrihodeOdReklama() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Ispis potencijalnih prihoda od reklama za program i dan u tjednu");
        System.out.println("-----------------------------------------------------------------");
        ispisiProgrameIDaneZaOdabir();
        boolean pogresanUnos = true, programPostoji = false;
        Integer program = null, dan = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (pogresanUnos) {
            try {
                System.out.print("Program (id): ");
                program = Integer.parseInt(br.readLine());
                System.out.print("Dan (1-7): ");
                System.out.println("");
                dan = Integer.parseInt(br.readLine());
                pogresanUnos = false;
            } catch (Exception e) {
                System.err.println("Unos nije cijeli broj!");
            }
            pogresanUnos = provjeriProgramIDan(dan, pogresanUnos, program, programPostoji);
        }
        ispisiPrihodeReklama(program, dan);
    }

    private static boolean provjeriProgramIDan(Integer dan, boolean pogresanUnos,
            Integer program, boolean programPostoji) {
        if (dan < 1 || dan > 7) {
            pogresanUnos = true;
            System.out.println("Dan mora biti izmedu 1 i 7!");
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
            System.out.println("ID programa ne postoji!");
        }
        return pogresanUnos;
    }

    private static void ispisiProgrameIDaneZaOdabir() {
        for (RasporedComposite r : raspored.getChildren()) {
            TvKuca t = (TvKuca) r;
            System.out.println("ID = " + t.id + ", Naziv =" + t.nazivPrograma);
        }

        System.out.println("Pon=1, Uto=2, Sri=3, Cet=4, Pet=5, Sub=6, Ned=7");
        System.out.println("------------------------------------------------");
    }

    private static void ispisiPrihodeReklama(Integer program, Integer dan) {
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
            System.out.println("Potencijalni prihodi od reklama za " + t.nazivPrograma
                    + " u " + pretvoriBrojUDanTjedna(dan)
                    + " iznose " + prihodiReklama + "(min)");
            break;
        }
    }

    private static void ispisiTjedniPlanPoVrsti() {
        System.out.println("------------------------------------------------");
        System.out.println("Ispis tjednog plana po svim programima, danima "
                + "i emisijama za odabranu vrstu");
        System.out.println("------------------------------------------------");
        ispisiVrsteZaOdabir();

        boolean pogresanUnos = true, vrstaPostoji = false;
        Integer vrsta = null;
        Vrsta vrstaO = new Vrsta();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (pogresanUnos) {
            try {
                System.out.print("Vrsta (id): ");
                vrsta = Integer.parseInt(br.readLine());
                pogresanUnos = false;
            } catch (Exception e) {
                System.err.println("Unos nije cijeli broj!");
            }
            for (Vrsta v : vrste) {
                if (v.id.equals(vrsta)) {
                    vrstaO = v;
                }
            }
            vrstaPostoji = vrste.contains(vrstaO);
            if (!pogresanUnos && vrstaPostoji) {
                System.out.println("Odabrana je " + vrstaO.naziv + " vrsta.");
                ispisiEmisijePoVrsti(vrsta);
                pogresanUnos = false;
            } else {
                pogresanUnos = true;
                System.out.println("ID vrste ne postoji!");
            }
        }
    }

    private static void ispisiVrsteZaOdabir() {
        for (Vrsta vrsta : vrste) {
            System.out.println("ID= " + vrsta.id
                    + ", " + vrsta.naziv);
        }
    }

    private static void ispisiEmisijePoVrsti(Integer vrsta) throws NoSuchElementException {

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

    private static void zamjeniUloguOsobiUEmisiji() {
        System.out.println("-----------------------------");
        System.out.println("Zamjena uloge osobe u emisiji");
        System.out.println("-----------------------------");
        ispisiOsobeUloge();
        boolean pogresanUnos = true;
        Integer osoba = 0, staraUloga = 0, novaUloga = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (pogresanUnos) {
            try {
                System.out.print("Osoba (id): ");
                osoba = Integer.parseInt(br.readLine());
                System.out.print("Stara uloga (id): ");
                staraUloga = Integer.parseInt(br.readLine());
                System.out.print("Nova uloga (id): ");
                novaUloga = Integer.parseInt(br.readLine());
                pogresanUnos = false;
            } catch (Exception e) {
                System.err.println("Unos nije cijeli broj!");
            }
            for (Osoba o : osobe) {
                if (o.id.equals(osoba)) {
                    for (Uloga u : uloge) {
                        if (u.id.equals(staraUloga)) {
                            for (Uloga u2 : uloge) {
                                if (u2.id.equals(novaUloga)) {
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

    public static void registriraj(EmisijaUProgramu emisija) {
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

    private static void ispisiOsobeUloge() {
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

    private static void obrisiEmisijuPremaRbr() {
        System.out.println("-------------------------------------------");
        System.out.println("Brisanje emisije prema jednoznačnom broju");
        System.out.println("-------------------------------------------");
        Integer indexPrograma = 0, indexDana = 0, uneseniBroj = 0;
        TvKuca t = null;
        DaniUTjednu dut = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Redni broj emisije:");
            uneseniBroj = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            System.out.println("Unos nije cijeli broj!");
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

    private static void ispisiPodatkePohranjivanja() {
        System.out.println("-----------------------------");
        System.out.println("Ispis podataka pohranjivanja");
        System.out.println("-----------------------------");
        Integer ukupnoPohranjivanja = caretaker.mementoList.size();

        for (Memento m : caretaker.mementoList) {
            System.out.println(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(m.vrijeme)
                    + " obrisana je emisija pod jednoznačnim brojem " + m.redniBrojEmisije
                    + ". Broj pohrane podataka = " + m.brojPohranjivanja + ".");
        }
        System.out.println("Ukupni broj pohranjivanja = " + ukupnoPohranjivanja);
    }

    private static Object klonirajObjekt(Object object) {
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

    private static void vratiPodatkePohranjivanja() {
        System.out.println("---------------------------------");
        System.out.println("Vraćanje podataka pohranjivanja");
        System.out.println("---------------------------------");
        Integer uneseniBroj = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Jednoznačni broj pohranjivanja:");
            uneseniBroj = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            System.out.println("Unos nije cijeli broj!");
        }
        for (Memento m : caretaker.mementoList) {
            if (m.brojPohranjivanja.equals(uneseniBroj)) {
                originator.restore(caretaker.getMemento(m.brojPohranjivanja - 1));
                raspored = originator.getState();
                System.out.println("Podaci vraćeni na stanje " + m.brojPohranjivanja + ".");
            }
        }
    }

    private static void ispisiKategorijeTrajanja() {
        System.out.println("-------------------------------------");
        System.out.println("Ispis emisija po kategoriji trajanja");
        System.out.println("-------------------------------------");

        Chain c1 = new KratkaEmisija();
        Chain c2 = new SrednjeDugaEmisija();
        Chain c3 = new DugaEmisija();
        c1.setNext(c2);
        c2.setNext(c3);

        for (Emisija e : emisije) {
            c1.process(e);
        }
    }

}
