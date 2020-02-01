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
public class StringDecoratorEmisija implements StringDecorator{

    
    protected StringDecorator decorator;

    public StringDecoratorEmisija(StringDecorator d) {
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
        System.out.format("\t%-1s%-40s", "|", ispis[0]);
        System.out.format("%-1s%-40s", "|", ispis[1]);
        System.out.format("%-1s\n", "|");
    }
    
}
