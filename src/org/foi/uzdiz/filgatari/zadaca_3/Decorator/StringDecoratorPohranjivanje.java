/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Decorator;

/**
 *
 * @author filip
 */
public class StringDecoratorPohranjivanje implements StringDecorator{
    
    
    protected StringDecorator decorator;

    public StringDecoratorPohranjivanje(StringDecorator d) {
        this.setDecorator(d);
    }

    public StringDecorator getDecorator() {
        return decorator;
    }

    public void setDecorator(StringDecorator decorator) {
        this.decorator = decorator;
    }
    @Override
    public void add(String tekst) {
        this.decorator.add(tekst);
        String[] ispis = tekst.split(";");        
        System.out.format("\t%-1s%-19s", "|", ispis[0]);
        System.out.format("%-1s%-45s", "|", ispis[1]);
        System.out.format("%-1s%-35s", "|", ispis[2]);
        System.out.format("%-1s\n", "|");
    }
    
}
