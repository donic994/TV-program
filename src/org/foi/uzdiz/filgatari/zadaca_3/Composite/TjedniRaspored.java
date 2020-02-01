/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Composite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filip
 */
public class TjedniRaspored extends RasporedComposite implements Serializable {

    public List<RasporedComposite> listaPrograma = new ArrayList<RasporedComposite>();

    @Override
    public void prikazi() {
        listaPrograma.forEach(RasporedComposite::prikazi);
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
        return (RasporedComposite)listaPrograma.get(id);

    }
    
}
