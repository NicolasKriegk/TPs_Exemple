/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.imie.swingexample;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author imie
 */
public class JAppletExample extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        /* Create and display the applet */
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    JPanel jPanel = new JPanel();
                    JLabel label = new JLabel("Hello World");
                    getContentPane().add(label);
                    setVisible(true);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // TODO overwrite start(), stop() and destroy() methods
}
