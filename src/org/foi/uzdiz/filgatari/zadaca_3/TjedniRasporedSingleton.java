/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3;

import org.foi.uzdiz.filgatari.zadaca_3.Composite.TvKuca;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filip
 */
public class TjedniRasporedSingleton {

    private static TjedniRasporedSingleton instance = new TjedniRasporedSingleton();
    public List<TvKuca> listaProgramaTvKuce = new ArrayList<TvKuca>();

    public List<TvKuca> getListaPrograma() {
        return listaProgramaTvKuce;
    }

    public void setListaPrograma(List<TvKuca> listaPrograma) {
        this.listaProgramaTvKuce = listaPrograma;
    }

    private TjedniRasporedSingleton() {

    }

    public static TjedniRasporedSingleton getInstance() {
        return instance;
    }
}
