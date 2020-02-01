/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.FactoryMethod;

/**
 *
 * @author filip
 */
public class PreuzimanjeFactory {

    public Preuzmi getTipPreuzimanja(String tipPreuzimanja) {
        if (tipPreuzimanja == null) {
            return null;
        } else if (tipPreuzimanja.equalsIgnoreCase("osoba")) {
            return new PreuzmiOsobe();
        } else if (tipPreuzimanja.equalsIgnoreCase("uloga")) {
            return new PreuzmiUloge();
        } else if (tipPreuzimanja.equalsIgnoreCase("emisija")) {
            return new PreuzmiEmisije();
        } else if (tipPreuzimanja.equalsIgnoreCase("tvKuca")) {
            return new PreuzmiTvKuce();
        } else if (tipPreuzimanja.equalsIgnoreCase("program")) {
            return new PreuzmiPrograme();
        } else if (tipPreuzimanja.equalsIgnoreCase("vrsta")) {
            return new PreuzmiVrste();
        } else {
            return null;
        }
    }
}
