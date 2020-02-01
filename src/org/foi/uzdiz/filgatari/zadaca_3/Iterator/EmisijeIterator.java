/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Iterator;

import java.util.NoSuchElementException;

/**
 *
 * @author filip
 */
public interface EmisijeIterator {

    public boolean hasNext();

    public Object getNext() throws NoSuchElementException;

    public Object getLastFetched();

    public void restart();
}
