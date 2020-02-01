/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Decorator;

/**
 *
 * @author filip
 */
public class StringBasicDecorator implements StringDecorator {

    private Integer tip;

    public StringBasicDecorator(Integer tip) {
        this.tip = tip;
    }

    @Override
    public void add(String ispis) {
        if (tip.equals(1)) {
            //emisija prema trajanju
            System.out.printf("\t+----------------------------------------+----------------------------------------+\n");
        }
        if (tip.equals(2)) {
            //podaci pohranjivanja
            System.out.printf("\t+-------------------+---------------------------------------------+------------------------------------+\n");
        }
    }
}
