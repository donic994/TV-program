/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.ChainOfResponsibility;

import org.foi.uzdiz.filgatari.zadaca_3.Decorator.StringBasicDecorator;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.StringDecorator;
import org.foi.uzdiz.filgatari.zadaca_3.Decorator.StringDecoratorEmisija;
import org.foi.uzdiz.filgatari.zadaca_3.Emisija;

/**
 *
 * @author filip
 */
public class DugaEmisija implements Chain {

    private Chain nextInChain;

    @Override
    public void setNext(Chain c) {
        nextInChain = c;
    }

    @Override
    public void process(Emisija request) {
        String odgovor = "";
        if (request.trajanje > 60) {
            odgovor = "Emisija s trajanjem preko 60 minuta;" + request.naziv;
            StringDecorator emisijaDecorator = new StringDecoratorEmisija(new StringBasicDecorator(1));
            emisijaDecorator.add(odgovor);
            odgovor = "";

        } else {
            nextInChain.process(request);
        }
    }

}
