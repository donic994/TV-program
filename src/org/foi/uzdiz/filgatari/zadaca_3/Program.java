package org.foi.uzdiz.filgatari.zadaca_3;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.RasporedComposite;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author filip
 */
public class Program extends RasporedComposite {

    public Integer id;
    public Integer[] daniUTjednu;
    public LocalTime pocetak;
    public List<OsobaUloga> osobaUloga;

    private List<RasporedComposite> listaPrograma = new ArrayList<RasporedComposite>();

    @Override
    public void prikazi() {
        System.out.println("\t" + id + " " + pocetak);
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
}
