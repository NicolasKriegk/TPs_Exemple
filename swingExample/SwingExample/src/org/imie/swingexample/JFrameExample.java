
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.imie.swingexample;

import java.awt.HeadlessException;
import javax.swing.*;

/**
 *
 * @author imie
 */
public class JFrameExample extends JFrame{
    public JFrameExample() throws HeadlessException {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HelloWorldSwing");
        JLabel label = new JLabel("Hello World");
        getContentPane().add(label);
        pack();
        setVisible(true);
    }
}
