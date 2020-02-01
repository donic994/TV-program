/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.filgatari.zadaca_3.Visitor;

import org.foi.uzdiz.filgatari.zadaca_3.Composite.EmisijaUProgramu;
/**
 *
 * @author filip
 */
public interface ElementVisitor {
    Integer visit(Element element);
}