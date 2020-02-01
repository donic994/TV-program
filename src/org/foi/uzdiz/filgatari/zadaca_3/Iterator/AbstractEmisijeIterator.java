/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Iterator;

import java.util.NoSuchElementException;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.EmisijaUProgramu;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.RasporedComposite;

/**
 *
 * @author filip
 */
public abstract class AbstractEmisijeIterator implements EmisijeIterator {

    protected RasporedComposite composite;

    protected int dan = 0;
    protected int elementRasporeda = 0;

    protected int newDan = 0;
    protected int newElementRasporeda = 0;

    public AbstractEmisijeIterator(RasporedComposite rc) {
        composite = rc;
    }

    @Override
    public Object getNext() throws NoSuchElementException {
        if (hasNext()) {
            dan = newDan;
            elementRasporeda = newElementRasporeda;
            EmisijaUProgramu emisija = (EmisijaUProgramu) (composite.getChild(dan)).getChild(elementRasporeda);
            elementRasporeda++;
            return emisija;
        }
        throw new NoSuchElementException("Nema sljedećeg!");
    }

    @Override
    public Object getLastFetched() {
        return ((RasporedComposite) composite.getChild(dan)).getChild(elementRasporeda);
    }

    @Override
    public void restart() {
        dan = elementRasporeda = 0;
    }

}
