/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Visitor;

import org.foi.uzdiz.filgatari.zadaca_3.Composite.EmisijaUProgramu;

/**
 *
 * @author filip
 */
public class ElementVisitorImpl implements ElementVisitor {

    int ukupnoMin = 0;

    @Override
    public Integer visit(Element element) {
        EmisijaUProgramu emisija = (EmisijaUProgramu) element;
        if (emisija.vrsta.trajanjeReklama > 0) {
            ukupnoMin += emisija.vrsta.trajanjeReklama;
            //System.out.println("Ukupni prihod od reklama= " + ukupnoMin);
        }
        return ukupnoMin;
    }
}
