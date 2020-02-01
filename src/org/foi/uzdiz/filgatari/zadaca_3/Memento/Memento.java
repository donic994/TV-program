/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Memento;

import java.sql.Timestamp;
import org.foi.uzdiz.filgatari.zadaca_3.Composite.TjedniRaspored;
import static org.foi.uzdiz.filgatari.zadaca_3.Main.caretaker;

/**
 *
 * @author filip
 */
public class Memento {

    private TjedniRaspored state;

    public Integer redniBrojEmisije;
    public Integer brojPohranjivanja = 0;
    public Timestamp vrijeme;
    
    public Memento(TjedniRaspored state, Integer rBr) {
        this.state = state;
        this.redniBrojEmisije = rBr;
        this.vrijeme = new Timestamp(System.currentTimeMillis());
        this.brojPohranjivanja = caretaker.mementoList.size()+1;
    }

    public TjedniRaspored getState() {
        return state;
    }
}
