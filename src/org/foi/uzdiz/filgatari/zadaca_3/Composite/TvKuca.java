package org.foi.uzdiz.filgatari.zadaca_3.Composite;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author filip
 */
public class TvKuca extends RasporedComposite implements Serializable{

    public Integer id;
    public String nazivPrograma;
    public LocalTime pocetak;
    public LocalTime kraj;
    public String nazivDatoteke;
    public List<DaniUTjednu> daniEmitiranja = new ArrayList<>();

    private List<RasporedComposite> listaPrograma = new ArrayList<RasporedComposite>();

    public TvKuca(Integer id, String nazivPrograma, LocalTime pocetak, LocalTime kraj,
            String nazivDatoteke) {
        this.id = id;
        this.nazivPrograma = nazivPrograma;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.nazivDatoteke = nazivDatoteke;
    }

    @Override
    public void prikazi() {
        System.out.println(nazivPrograma);
    }

    public void dodaj(RasporedComposite prog) {
        listaPrograma.add(prog);
    }

    public void obrisi(RasporedComposite prog) {
        listaPrograma.remove(prog);
    }

    @Override
    public List<RasporedComposite> getChildren() {
        return listaPrograma;
    }

    @Override
    public RasporedComposite getChild(int id) {
        return (RasporedComposite) listaPrograma.get(id);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazivPrograma() {
        return nazivPrograma;
    }

    public void setNazivPrograma(String nazivPrograma) {
        this.nazivPrograma = nazivPrograma;
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

    public String getNazivDatoteke() {
        return nazivDatoteke;
    }

    public void setNazivDatoteke(String nazivDatoteke) {
        this.nazivDatoteke = nazivDatoteke;
    }

    public List<DaniUTjednu> getEmisije() {
        return daniEmitiranja;
    }

    public void setEmisije(List<DaniUTjednu> emisije) {
        this.daniEmitiranja = emisije;
    }

    public List<RasporedComposite> getListaPrograma() {
        return listaPrograma;
    }

    public void setListaPrograma(List<RasporedComposite> listaPrograma) {
        this.listaPrograma = listaPrograma;
    }
}
