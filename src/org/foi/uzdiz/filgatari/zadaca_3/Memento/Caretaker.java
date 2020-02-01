/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Memento;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filip
 */
public class Caretaker {

    public List<Memento> mementoList = new ArrayList<Memento>();

    public void addMemento(Memento state) {
        mementoList.add(state);
    }

    public Memento getMemento(int index) {
        return mementoList.get(index);
    }
}
