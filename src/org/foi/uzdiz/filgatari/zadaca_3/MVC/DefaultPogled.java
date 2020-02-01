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
public class DefaultPogled {

    public void korisnikckiIzbornik() {
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
        System.out.println("9. Promjena pogleda");
        System.out.println("0. Izlaz iz programa");
        System.out.print("Vas odabir:");
    }

    public void greskaKorisnickiIzbiornik() {
        System.out.println("Molimo Vas da unesete cjelobrojni broj (1-9) ili 0 za izlaz");
    }

    public void ispisiVremeskiPlan() {
        System.out.println("------------------------------------------------");
        System.out.println("Ispis vremenskog plana za program i dan u tjednu");
        System.out.println("------------------------------------------------");
        ispisiProgrameIDaneZaOdabir();
    }

    public void izlaz() {
        System.out.println("Izlaz iz programa");
    }

    public void unosPrograma() {
        System.out.print("Program (id): ");
    }

    public void unosDana() {
        System.out.print("Dan (1-7): ");
    }

    public void unosVrste() {
        System.out.print("Vrsta (id): ");
    }

    public void greskaCijeliBroj() {
        System.err.println("Unos nije cijeli broj!");
    }

    public void ispisiProgrameIDaneZaOdabir() {
        for (RasporedComposite r : raspored.getChildren()) {
            TvKuca t = (TvKuca) r;
            System.out.println("ID = " + t.id + ", Naziv =" + t.nazivPrograma);
        }

        System.out.println("Pon=1, Uto=2, Sri=3, Cet=4, Pet=5, Sub=6, Ned=7");
        System.out.println("------------------------------------------------");
    }

    public void programPrikazujeUDanu(TvKuca t, Integer dan) {
        System.out.println(t.nazivPrograma + " u "
                + pretvoriBrojUDanTjedna(dan) + " prikazuje:");
    }

    public void ispisiPotencijalnePrihodeOdReklama() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Ispis potencijalnih prihoda od reklama za program i dan u tjednu");
        System.out.println("-----------------------------------------------------------------");
        ispisiProgrameIDaneZaOdabir();
    }

    public void greskaIdPrograma() {
        System.out.println("ID programa ne postoji!");
    }

    public void greskaTjedan() {
        System.out.println("Dan mora biti izmedu 1 i 7!");
    }

    public void ispisiUkupnePrihodeReklama(TvKuca t, Integer dan, Integer prihodiReklama) {
        System.out.println("Potencijalni prihodi od reklama za " + t.nazivPrograma
                + " u " + pretvoriBrojUDanTjedna(dan)
                + " iznose " + prihodiReklama + "(min)");
    }

    public void ispisiTjedniPlanPoVrsti() {
        System.out.println("------------------------------------------------");
        System.out.println("Ispis tjednog plana po svim programima, danima "
                + "i emisijama za odabranu vrstu");
        System.out.println("------------------------------------------------");
        ispisiVrsteZaOdabir();
    }

    public void greskaIdVrste() {
        System.out.println("ID vrste ne postoji!");
    }

    public void ispisiOdabranuVrstu(Vrsta vrsta) {
        System.out.println("Odabrana je " + vrsta.naziv + " vrsta.");
    }

    public void ispisiVrsteZaOdabir() {
        for (Vrsta vrsta : vrste) {
            System.out.println("ID= " + vrsta.id
                    + ", " + vrsta.naziv);
        }
    }

    public void zamjeniUloguOsobiUEmisiji() {
        System.out.println("-----------------------------");
        System.out.println("Zamjena uloge osobe u emisiji");
        System.out.println("-----------------------------");
    }

    public void unosNovaUloga() {
        System.out.print("Nova uloga (id): ");
    }

    public void unosStaraUloga() {
        System.out.print("Stara uloga (id): ");
    }

    public void unosOsoba() {
        System.out.print("Osoba (id): ");
    }

    public void ispisiPromjenuUloge(Osoba o, Uloga u2) {
        System.out.println("Promjena kod " + o.imeIPrezime
                + ". Nova uloga je " + u2.opis);
    }

    public void obrisiEmisijuPremaRbr() {
        System.out.println("-------------------------------------------");
        System.out.println("Brisanje emisije prema jednoznačnom broju");
        System.out.println("-------------------------------------------");
    }

    public void unosRbrEmisije() {
        System.out.println("Redni broj emisije:");
    }

    public void ispisiPodatkePohranjivanja() {
        System.out.println("-----------------------------");
        System.out.println("Ispis podataka pohranjivanja");
        System.out.println("-----------------------------");
    }

    public void ispisiPohranjivanjeUkupno(Integer ukupnoPohranjivanja) {
        System.out.println("Ukupni broj pohranjivanja = " + ukupnoPohranjivanja);
    }

    public void ispisiPohranjivanjeInfo(Memento m) {
        String odgovor = (new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(m.vrijeme)
                + "; obrisana emisija pod jednoznačnim brojem " + m.redniBrojEmisije
                + "; Jednoznačni broj pohranjivanja = " + m.brojPohranjivanja + " ");
        StringDecorator emisijaDecorator = new StringDecoratorPohranjivanje(new StringBasicDecorator(2));
        emisijaDecorator.add(odgovor);

    }

    public void vratiPodatkePohranjivanja() {
        System.out.println("---------------------------------");
        System.out.println("Vraćanje podataka pohranjivanja");
        System.out.println("---------------------------------");
    }

    public void ispisiVracanjeStanja(Memento m) {
        System.out.println("Podaci vraćeni na stanje " + m.brojPohranjivanja + ".");
    }

    public void unosJednoznacniBroj() {
        System.out.print("Jednoznačni broj pohranjivanja:");
    }

    public void ispisiKategorijeTrajanja() {
        System.out.println("-------------------------------------");
        System.out.println("Ispis emisija po kategoriji trajanja");
        System.out.println("-------------------------------------");
    }

    public void ispisiKategorijuTrajanjaEmisije(Chain c1, Emisija e) {
        c1.process(e);
    }

    public void promjeniPogled() {
        System.out.println("-----------------------------------");
        System.out.println("Pogled promjenjen u default pogled");
        System.out.println("-----------------------------------");
    }

    public void ispisiString(String tekst) {
        System.out.println(tekst);
    }

}
