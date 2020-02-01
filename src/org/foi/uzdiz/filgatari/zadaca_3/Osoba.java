package org.foi.uzdiz.filgatari.zadaca_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.foi.uzdiz.filgatari.zadaca_3.Observer.Observer;
import org.foi.uzdiz.filgatari.zadaca_3.Observer.Subject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author filip
 */
public class Osoba implements Subject, Serializable {

    public Integer id;
    public String imeIPrezime;
    public String uloga = "";
    private List observers = new ArrayList();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImeIPrezime() {
        return imeIPrezime;
    }

    public void setImeIPrezime(String imeIPrezime) {
        this.imeIPrezime = imeIPrezime;
    }

    @Override
    public void register(Observer newObserver) {
        if (newObserver == null) {
            throw new NullPointerException("Null Observer");
        }
        if (!observers.contains(newObserver)) {
            observers.add(newObserver);
        }
    }

    @Override
    public void unregister(Observer deleteObserver) {
        observers.remove(deleteObserver);
        System.out.println("Observer " + (observers.indexOf(deleteObserver)) + 1
                + " obrisan");
    }

    @Override
    public Osoba getState() {
        return this;
    }

    @Override
    public void setState(String state) {
        this.uloga = state;
        notifyObservers();
    }

    private void notifyObservers() {
        Iterator i = observers.iterator();
        while (i.hasNext()) {
            Observer o = (Observer) i.next();
            o.update(this);
        }
    }
}
