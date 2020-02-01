/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Decorator;

import org.foi.uzdiz.filgatari.zadaca_3.Composite.EmisijaUProgramu;

/**
 *
 * @author filip
 */
public class BasicDecorator implements Decorator {

    private Integer tip;

    public BasicDecorator(Integer tip) {
        this.tip = tip;
    }

    @Override
    public void add(EmisijaUProgramu ispis) {
        if (tip.equals(1)) {
            //emisija
            System.out.printf("\t+--+----------------------------------------+-----------+---------------+--+\n");
        }
        if (tip.equals(2)) {
            //osoba i uloga
            System.out.println("\t+----------------------------------------------------+");
        }
                if (tip.equals(3)) {
            //osoba i uloga
            System.out.println("\t+--+----------------------------------------+----+");
        }
    }
}
