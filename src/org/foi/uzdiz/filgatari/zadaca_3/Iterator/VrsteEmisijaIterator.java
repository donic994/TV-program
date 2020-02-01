/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Iterator;

import org.foi.uzdiz.filgatari.zadaca_3.Composite.EmisijaUProgramu;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.RasporedComposite;

/**
 *
 * @author filip
 */
public class VrsteEmisijaIterator extends AbstractEmisijeIterator {

    private int vrstaEmisije;

    public VrsteEmisijaIterator(RasporedComposite rc, int vrsta) {
        super(rc);
        vrstaEmisije = vrsta;
    }

    @Override
    public boolean hasNext() {
        int tempDan = dan;
        int tempElementRasporeda = elementRasporeda;

        while (tempDan < composite.getChildren().size()) {
            while (tempElementRasporeda < ((RasporedComposite) composite.getChild(tempDan)).getChildren().size()) {
                int vrsta = ((EmisijaUProgramu) ((RasporedComposite) composite.getChild(tempDan)).
                        getChild(tempElementRasporeda)).getVrsta().getId();
                if (vrsta == vrstaEmisije) {
                    newDan = tempDan;
                    newElementRasporeda = tempElementRasporeda;
                    return true;
                }
                tempElementRasporeda++;
            }
            tempDan++;
            tempElementRasporeda = 0;
        }
        return false;
    }

}
