/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Observer;

import org.foi.uzdiz.filgatari.zadaca_3.Osoba;

/**
 *
 * @author filip
 */
public interface Subject {

    public void register(Observer obj);

    public void unregister(Observer obj);

    public Osoba getState();
    
    public void setState (String state);
}
