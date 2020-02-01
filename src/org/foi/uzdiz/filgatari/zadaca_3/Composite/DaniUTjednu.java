/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Composite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.filgatari.zadaca_3.Pomocna;

/**
 *
 * @author filip
 */
public class DaniUTjednu extends RasporedComposite implements Serializable{

    public Integer id;
    public String naziv;
    public List<EmisijaUProgramu> listaEmisija = new ArrayList<EmisijaUProgramu>();
    private List<RasporedComposite> listaDanaEmitiranja = new ArrayList<RasporedComposite>();

    @Override
    public void prikazi() {
        System.out.println(naziv);
    }

    public void dodaj(RasporedComposite prog) {
        listaDanaEmitiranja.add(prog);
    }

    public void obrisi(RasporedComposite prog) {
        listaDanaEmitiranja.remove(prog);
    }

    @Override
    public List<RasporedComposite> getChildren() {
        return listaDanaEmitiranja;
    }

    @Override
    public RasporedComposite getChild(int id) {
        return (RasporedComposite) listaDanaEmitiranja.get(id);

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

    public List<EmisijaUProgramu> getListaEmisija() {
        return listaEmisija;
    }

    public void setListaEmisija(List<EmisijaUProgramu> listaEmisija) {
        this.listaEmisija = listaEmisija;
    }

    public List<RasporedComposite> getListaDana() {
        return listaDanaEmitiranja;
    }

    public void setListaDana(List<RasporedComposite> listaDana) {
        this.listaDanaEmitiranja = listaDana;
    }

    public DaniUTjednu(Integer id) {
        this.id = id;
        this.naziv = Pomocna.pretvoriBrojUDanTjedna(id);
    }

}
