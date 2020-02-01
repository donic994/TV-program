/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Decorator;

import org.foi.uzdiz.filgatari.zadaca_3.Composite.EmisijaUProgramu;
import org.foi.uzdiz.filgatari.zadaca_3.Pomocna;

/**
 *
 * @author filip
 */
public class DecoratorOsobaUloga implements Decorator {

    protected Decorator decorator;

    public DecoratorOsobaUloga(Decorator d) {
        this.setDecorator(d);
    }

    public Decorator getDecorator() {
        return decorator;
    }

    public void setDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public void add(EmisijaUProgramu ispis) {
        if (ispis.osobaUloga != null) {
            for (var v : ispis.osobaUloga) {
                System.out.println("\t+----------------------------------------------------+");
                System.out.format("\t%-1s%2s", "|", v.osoba);
                System.out.format("%-1s%-25s", "|", Pomocna.ispisiOsobu(v.osoba));
                System.out.format("%-1s%2s", "|", v.uloga);
                System.out.format("%-1s%-20s", "|", Pomocna.ispisiUlogu(v.uloga));
                System.out.format("%-1s\n", "|");
            }
        }
    }

}
