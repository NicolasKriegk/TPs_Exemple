/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.imie.swingexample2;

/**
 *
 * @author imie
 */
public class ObserverMessage {
    String commande;
    Object parameter;

    public String getCommande() {
        return commande;
    }

    public void setCommande(String commande) {
        this.commande = commande;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
    
}
