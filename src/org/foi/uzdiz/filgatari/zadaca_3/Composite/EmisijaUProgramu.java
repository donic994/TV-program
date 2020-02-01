/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Composite;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.filgatari.zadaca_3.MVC.Controler;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.osobe;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.uloge;
import org.foi.uzdiz.filgatari.zadaca_3.Observer.Observer;
import org.foi.uzdiz.filgatari.zadaca_3.Observer.Subject;
import org.foi.uzdiz.filgatari.zadaca_3.Osoba;
import org.foi.uzdiz.filgatari.zadaca_3.OsobaUloga;
import org.foi.uzdiz.filgatari.zadaca_3.Pomocna;
import org.foi.uzdiz.filgatari.zadaca_3.Uloga;
import org.foi.uzdiz.filgatari.zadaca_3.Visitor.Element;
import org.foi.uzdiz.filgatari.zadaca_3.Visitor.ElementVisitor;
import org.foi.uzdiz.filgatari.zadaca_3.Vrsta;

/**
 *
 * @author filip
 */
public class EmisijaUProgramu extends RasporedComposite implements Observer, Element, Serializable {

    private List<RasporedComposite> listaEmisija = new ArrayList<RasporedComposite>();

    @Override
    public void prikazi() {
        String osobeUloge = "";
        if (osobaUloga != null) {
            for (var ou : osobaUloga) {
                osobeUloge += Pomocna.ispisiOsobu(ou.osoba) + " = " + Pomocna.ispisiUlogu(ou.uloga) + " ";
            }
            System.out.println("\t" + naziv + "(" + id + ") " + pocetak + "-" + kraj
                    + " " + vrsta.naziv + ", " + osobeUloge);
        }

    }

    @Override
    public List<RasporedComposite> getChildren() {
        return listaEmisija;
    }

    public void dodaj(RasporedComposite prog) {
        // listaEmisija.add(prog);
    }

    public void obrisi(RasporedComposite prog) {
        listaEmisija.remove(prog);
    }

    @Override
    public RasporedComposite getChild(int id) {
        return (RasporedComposite) listaEmisija.get(id);

    }

    public List<RasporedComposite> getListaPrograma() {
        return listaEmisija;
    }

    public Integer redniBroj;
    public Integer id;
    public String naziv;
    public Integer trajanje;
    public List<OsobaUloga> osobaUloga;
    public Vrsta vrsta;
    public LocalTime pocetak;
    public LocalTime pocetakPrograma;
    public LocalTime kraj;
    public Integer prioritet;
    public Integer idProgram;

    //0-ima definiran i dan i vrijeme početka programa
    //1-ima definiran samo dan
    //2-nema definiran ni dan ni vrijeme početka programa
    public EmisijaUProgramu(Integer id,
            String naziv,
            Integer trajanje,
            List<OsobaUloga> osobaUloga,
            Vrsta vrsta,
            LocalTime pocetak,
            LocalTime pocetakPrograma,
            Integer idProgram,
            Integer prioritet) {
        this.id = id;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.osobaUloga = osobaUloga;
        this.vrsta = vrsta;
        this.pocetak = pocetak;
        if (pocetak != null) {
            kraj = pocetak.plusMinutes(trajanje);
        } else {
            kraj = null;
        }
        this.prioritet = prioritet;
        this.pocetakPrograma = pocetakPrograma;
        this.idProgram = idProgram;
    }

    public Integer getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(Integer redniBroj) {
        this.redniBroj = redniBroj;
    }

    public void setListaPrograma(List<RasporedComposite> listaPrograma) {
        this.listaEmisija = listaPrograma;
    }

    public LocalTime getPocetakPrograma() {
        return pocetakPrograma;
    }

    public void setPocetakPrograma(LocalTime pocetakPrograma) {
        this.pocetakPrograma = pocetakPrograma;
    }

    public Integer getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(Integer prioritet) {
        this.prioritet = prioritet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Integer trajanje) {
        this.trajanje = trajanje;
    }

    public Vrsta getVrsta() {
        return vrsta;
    }

    public void setVrsta(Vrsta vrsta) {
        this.vrsta = vrsta;
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalTime pocetak) {
        this.pocetak = pocetak;
    }

    public LocalTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalTime kraj) {
        this.kraj = kraj;
    }

    @Override
    public void update(Subject sub) {
        Osoba osoba = sub.getState();
        OsobaUloga nova = new OsobaUloga();
        for (Osoba o : osobe) {
            if (o.id.equals(osoba.id)) {
                for (Uloga u : uloge) {
                    if (u.opis.equals(osoba.uloga)) {
                        nova.osoba = osoba.id;
                        nova.uloga = u.id;
                    }
                }
            }
        }
        int i = 0;
        boolean postoji = true;
        while (postoji) {
            for (OsobaUloga ou : osobaUloga) {
                if (osoba.id.equals(ou.osoba)) {
                    this.osobaUloga.set(i, nova);
                    postoji = false;
                    /*
                    System.out.println("Promjena kod " + osoba.imeIPrezime
                            + ". Nova uloga je " + sub.getState().uloga);
                     */
                }
            }
            i++;
        }
    }

    @Override
    public int accept(ElementVisitor visitor) {
        return visitor.visit(this);
    }
}
