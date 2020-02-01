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
public class DecoratorEmisijaUProgramu implements Decorator {

    protected Decorator decorator;

    public DecoratorEmisijaUProgramu(Decorator d) {
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
        this.decorator.add(ispis);
        System.out.format("\t%-1s%2s", "|", ispis.id);
        System.out.format("%-1s%-40s", "|", ispis.naziv);
        System.out.format("%-1s%10s", "|", ispis.pocetak + "-" + ispis.kraj);
        System.out.format("%-1s%-15s", "|", ispis.vrsta.naziv);
        System.out.format("%-1s%2s", "|", ispis.redniBroj);
        System.out.format("%-1s", "|");
        if (ispis.osobaUloga != null) {
            for (var v : ispis.osobaUloga) {
                if (ispis.osobaUloga.size() == 1) {
                    System.out.format("%-10s", Pomocna.ispisiOsobu(v.osoba) + " je " + Pomocna.ispisiUlogu(v.uloga));
                } else {
                    System.out.format("%-10s", Pomocna.ispisiOsobu(v.osoba) + " je " + Pomocna.ispisiUlogu(v.uloga) + ", ");
                }
            }
        }
        System.out.format("%-1s\n", " ");
    }
}
