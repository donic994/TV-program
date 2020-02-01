/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Decorator;

import org.foi.uzdiz.filgatari.zadaca_3.Composite.EmisijaUProgramu;
import org.foi.uzdiz.filgatari.zadaca_3.Pomocna;
import org.foi.uzdiz.filgatari.zadaca_3.Visitor.ElementVisitor;
import org.foi.uzdiz.filgatari.zadaca_3.Visitor.ElementVisitorImpl;

/**
 *
 * @author filip
 */
public class DecoratorReklame implements Decorator {

    protected Decorator decorator;
    private Integer prihodiReklama = 0;
    ElementVisitor visitor = new ElementVisitorImpl();

    public DecoratorReklame(Decorator d) {
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
        prihodiReklama = ispis.accept(visitor);
        this.decorator.add(ispis);
        System.out.format("\t%-1s%2s", "|", ispis.id);
        System.out.format("%-1s%-40s", "|", ispis.naziv);
        System.out.format("%-1s%4s", "|", ispis.vrsta.trajanjeReklama);

        System.out.format("%-1s\n", "|");
    }
}
