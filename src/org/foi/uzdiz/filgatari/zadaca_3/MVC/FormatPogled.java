/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.MVC;

import java.text.SimpleDateFormat;
import org.foi.uzdiz.filgatari.zadaca_3.ChainOfResponsibility.Chain;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.RasporedComposite;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.TvKuca;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.StringBasicDecorator;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.StringDecorator;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.StringDecoratorPohranjivanje;
import org.foi.uzdiz.filgatari.zadaca_3.Emisija;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.raspored;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.vrste;
import org.foi.uzdiz.filgatari.zadaca_3.Memento.Memento;
import org.foi.uzdiz.filgatari.zadaca_3.Osoba;
import static org.foi.uzdiz.filgatari.zadaca_3.Pomocna.pretvoriBrojUDanTjedna;
import org.foi.uzdiz.filgatari.zadaca_3.Uloga;
import org.foi.uzdiz.filgatari.zadaca_3.Vrsta;

/**
 *
 * @author filip
 */
public class FormatPogled {

    public void ispisiFormatiraniPocetak() {
        String formatted = "[" + String.format("%05d", Controler.linija) + "] ";
        Controler.linija++;
        System.out.print(formatted);
    }

    public void ispisiMVC(String tekst) {
        ispisiFormatiraniPocetak();
        System.out.println(tekst);

    }

    public void korisnikckiIzbornik() {
        ispisiFormatiraniPocetak();
        System.out.println("---------");
        ispisiFormatiraniPocetak();
        System.out.println("IZBORNIK");
        ispisiFormatiraniPocetak();
        System.out.println("---------");
        ispisiFormatiraniPocetak();
        System.out.println("1. Ispis vremenskog plana za program i dan u tjednu");
        ispisiFormatiraniPocetak();
        System.out.println("2. Ispis potencijalnih prihoda od reklama za program i dan u tjednu");
        ispisiFormatiraniPocetak();
        System.out.println("3. Ispis tjednog plana po svim programima, danima i emisijama"
                + " za odabranu vrstu");
        ispisiFormatiraniPocetak();
        System.out.println("4. Zamjena uloge osobe u emisiji");
        ispisiFormatiraniPocetak();
        System.out.println("5. Brisanje emisije temeljem njezinog jednoznačnog broja");
        ispisiFormatiraniPocetak();
        System.out.println("6. Ispisivanje podataka pohranjivanja");
        ispisiFormatiraniPocetak();
        System.out.println("7. Vraćanje podataka temeljem jednoznačnog broja pohranjivanja");
        ispisiFormatiraniPocetak();
        System.out.println("8. Ispis emisija po kategoriji trajanja");
        ispisiFormatiraniPocetak();
        System.out.println("9. Promjena pogleda");
        ispisiFormatiraniPocetak();
        System.out.println("0. Izlaz iz programa");
        ispisiFormatiraniPocetak();
        System.out.print("Vas odabir:");
    }

    public void greskaKorisnickiIzbiornik() {
        ispisiFormatiraniPocetak();
        System.out.println("Molimo Vas da unesete cjelobrojni broj (1-9) ili 0 za izlaz");
    }

    public void izlaz() {
        ispisiFormatiraniPocetak();
        System.out.println("Izlaz iz programa");
    }

    public void ispisiVremeskiPlan() {
        ispisiFormatiraniPocetak();
        System.out.println("------------------------------------------------");
        ispisiFormatiraniPocetak();
        System.out.println("Ispis vremenskog plana za program i dan u tjednu");
        ispisiFormatiraniPocetak();
        System.out.println("------------------------------------------------");
        ispisiProgrameIDaneZaOdabir();
    }

    public void unosPrograma() {
        ispisiFormatiraniPocetak();
        System.out.print("Program (id): ");
    }

    public void unosDana() {
        ispisiFormatiraniPocetak();
        System.out.print("Dan (1-7): ");
    }

    public void greskaCijeliBroj() {
        ispisiFormatiraniPocetak();
        System.err.println("Unos nije cijeli broj!");
    }

    public void ispisiProgrameIDaneZaOdabir() {
        for (RasporedComposite r : raspored.getChildren()) {
            TvKuca t = (TvKuca) r;
            ispisiFormatiraniPocetak();
            System.out.println("ID = " + t.id + ", Naziv =" + t.nazivPrograma);
        }
        ispisiFormatiraniPocetak();
        System.out.println("Pon=1, Uto=2, Sri=3, Cet=4, Pet=5, Sub=6, Ned=7");
        ispisiFormatiraniPocetak();
        System.out.println("------------------------------------------------");
    }

    public void programPrikazujeUDanu(TvKuca t, Integer dan) {
        ispisiFormatiraniPocetak();
        System.out.println(t.nazivPrograma + " u "
                + pretvoriBrojUDanTjedna(dan) + " prikazuje:");
    }

    public void ispisiPotencijalnePrihodeOdReklama() {
        ispisiFormatiraniPocetak();
        System.out.println("-------------------------------------------------------------------");
        ispisiFormatiraniPocetak();
        System.out.println("Ispis potencijalnih prihoda od reklama za program i dan u tjednu");
        ispisiFormatiraniPocetak();
        System.out.println("-----------------------------------------------------------------");
        ispisiProgrameIDaneZaOdabir();
    }

    public void ispisiUkupnePrihodeReklama(TvKuca t, Integer dan, Integer prihodiReklama) {
        ispisiFormatiraniPocetak();
        System.out.println("Potencijalni prihodi od reklama za " + t.nazivPrograma
                + " u " + pretvoriBrojUDanTjedna(dan)
                + " iznose " + prihodiReklama + "(min)");
    }

    public void greskaIdVrste() {
        ispisiFormatiraniPocetak();
        System.out.println("ID vrste ne postoji!");
    }

    public void ispisiOdabranuVrstu(Vrsta vrsta) {
        ispisiFormatiraniPocetak();
        System.out.println("Odabrana je " + vrsta.naziv + " vrsta.");
    }

    public void ispisiVrsteZaOdabir() {
        for (Vrsta vrsta : vrste) {
            ispisiFormatiraniPocetak();
            System.out.println("ID= " + vrsta.id
                    + ", " + vrsta.naziv);
        }
    }

    public void unosVrste() {
        ispisiFormatiraniPocetak();
        System.out.print("Vrsta (id): ");
    }

    public void greskaIdPrograma() {
        ispisiFormatiraniPocetak();
        System.out.println("ID programa ne postoji!");
    }

    public void greskaTjedan() {
        ispisiFormatiraniPocetak();
        System.out.println("Dan mora biti izmedu 1 i 7!");
    }

    public void ispisiTjedniPlanPoVrsti() {
        ispisiFormatiraniPocetak();
        System.out.println("------------------------------------------------");
        ispisiFormatiraniPocetak();
        System.out.println("Ispis tjednog plana po svim programima, danima "
                + "i emisijama za odabranu vrstu");
        ispisiFormatiraniPocetak();
        System.out.println("------------------------------------------------");
        ispisiVrsteZaOdabir();
    }

    public void zamjeniUloguOsobiUEmisiji() {
        ispisiFormatiraniPocetak();
        System.out.println("-----------------------------");
        ispisiFormatiraniPocetak();
        System.out.println("Zamjena uloge osobe u emisiji");
        ispisiFormatiraniPocetak();
        System.out.println("-----------------------------");
    }

    public void unosNovaUloga() {
        ispisiFormatiraniPocetak();
        System.out.print("Nova uloga (id): ");
    }

    public void unosStaraUloga() {
        ispisiFormatiraniPocetak();
        System.out.print("Stara uloga (id): ");
    }

    public void unosOsoba() {
        ispisiFormatiraniPocetak();
        System.out.print("Osoba (id): ");
    }

    public void ispisiPromjenuUloge(Osoba o, Uloga u2) {
        ispisiFormatiraniPocetak();
        System.out.println("Promjena kod " + o.imeIPrezime
                + ". Nova uloga je " + u2.opis);
    }

    public void obrisiEmisijuPremaRbr() {
        ispisiFormatiraniPocetak();
        System.out.println("-------------------------------------------");
        ispisiFormatiraniPocetak();
        System.out.println("Brisanje emisije prema jednoznačnom broju");
        ispisiFormatiraniPocetak();
        System.out.println("-------------------------------------------");
    }

    public void unosRbrEmisije() {
        ispisiFormatiraniPocetak();
        System.out.println("Redni broj emisije:");
    }

    public void ispisiPodatkePohranjivanja() {
        ispisiFormatiraniPocetak();
        System.out.println("-----------------------------");
        ispisiFormatiraniPocetak();
        System.out.println("Ispis podataka pohranjivanja");
        ispisiFormatiraniPocetak();
        System.out.println("-----------------------------");
    }

    public void ispisiPohranjivanjeUkupno(Integer ukupnoPohranjivanja) {
        ispisiFormatiraniPocetak();
        System.out.println("Ukupni broj pohranjivanja = " + ukupnoPohranjivanja);
    }

    public void ispisiPohranjivanjeInfo(Memento m) {
        ispisiFormatiraniPocetak();
        String odgovor = (new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(m.vrijeme)
                + "; obrisana emisija pod jednoznačnim brojem " + m.redniBrojEmisije
                + "; Jednoznačni broj pohranjivanja = " + m.brojPohranjivanja + " ");
        StringDecorator emisijaDecorator = new StringDecoratorPohranjivanje(new StringBasicDecorator(2));
        emisijaDecorator.add(odgovor);

    }

    public void vratiPodatkePohranjivanja() {
        ispisiFormatiraniPocetak();
        System.out.println("---------------------------------");
        ispisiFormatiraniPocetak();
        System.out.println("Vraćanje podataka pohranjivanja");
        ispisiFormatiraniPocetak();
        System.out.println("---------------------------------");
    }

    public void ispisiVracanjeStanja(Memento m) {
        ispisiFormatiraniPocetak();
        System.out.println("Podaci vraćeni na stanje " + m.brojPohranjivanja + ".");
    }

    public void unosJednoznacniBroj() {
        ispisiFormatiraniPocetak();
        System.out.print("Jednoznačni broj pohranjivanja:");
    }

    public void ispisiKategorijeTrajanja() {
        ispisiFormatiraniPocetak();
        System.out.println("-------------------------------------");
        ispisiFormatiraniPocetak();
        System.out.println("Ispis emisija po kategoriji trajanja");
        ispisiFormatiraniPocetak();
        System.out.println("-------------------------------------");
    }

    public void ispisiKategorijuTrajanjaEmisije(Chain c1, Emisija e) {
        //ispisiFormatiraniPocetak();
        c1.process(e);
    }

    public void promjeniPogled() {
        ispisiFormatiraniPocetak();
        System.out.println("---------------------------------------");
        ispisiFormatiraniPocetak();
        System.out.println("Pogled promjenjen u formatirani pogled");
        ispisiFormatiraniPocetak();
        System.out.println("---------------------------------------");
    }

    public void ispisiString(String tekst) {
        ispisiFormatiraniPocetak();
        System.out.println(tekst);
    }
}
