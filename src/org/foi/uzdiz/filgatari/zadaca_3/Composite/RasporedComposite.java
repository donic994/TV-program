/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Composite;

import java.util.List;

/**
 *
 * @author filip
 */
/*
public interface RasporedComposite {
    public void prikazi();
    public List<RasporedComposite> getChildren();
    public RasporedComposite getChild(int id);
}
 */
public abstract class RasporedComposite {

    public void prikazi() {
        throw new UnsupportedOperationException();
    }

    public List<RasporedComposite> getChildren() {
        throw new UnsupportedOperationException();
    }

    public RasporedComposite getChild(int id) {
        throw new UnsupportedOperationException();
    }
}
