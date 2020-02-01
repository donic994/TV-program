/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Iterator;

import org.foi.uzdiz.filgatari.zadaca_3.Composite.RasporedComposite;

/**
 *
 * @author filip
 */
public class SveEmisijeIterator extends AbstractEmisijeIterator {

    public SveEmisijeIterator(RasporedComposite rc) {
        super(rc);
    }

    @Override
    public boolean hasNext() {
        int tempDan = dan;
        int tempElementRasporeda = elementRasporeda;

        while (tempDan < composite.getChildren().size()) {
            if (tempElementRasporeda < ((RasporedComposite) composite.getChild(tempDan)).getChildren().size()) {
                newDan = tempDan;
                newElementRasporeda = tempElementRasporeda;
                return true;
            }
            tempDan++;
            tempElementRasporeda = 0;
        }
        return false;
    }
}
