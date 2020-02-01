/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Memento;

import org.foi.uzdiz.filgatari.zadaca_3.Composite.TjedniRaspored;

/**
 *
 * @author filip
 */
public class Originator {

    private TjedniRaspored state;
    private Integer redniBrojEmisije;

    public TjedniRaspored getState() {
        return state;
    }

    public void setState(TjedniRaspored state, Integer rBr) {
        this.state = state;
        this.redniBrojEmisije = rBr;
    }

    public Memento save() {
        return new Memento(state, redniBrojEmisije);
    }

    public void restore(Memento m) {
        state = m.getState();
    }
}
