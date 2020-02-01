/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.ChainOfResponsibility;

import org.foi.uzdiz.filgatari.zadaca_3.Emisija;

/**
 *
 * @author filip
 */
public interface Chain {
    public abstract void setNext(Chain nextInChain); 
    public abstract void process(Emisija request); 
}
