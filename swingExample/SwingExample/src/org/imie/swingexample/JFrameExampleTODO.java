
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.imie.swingexample;

import java.awt.CardLayout;
import java.awt.HeadlessException;
import javax.swing.*;

/**
 *
 * @author imie
 */
public class JFrameExampleTODO extends JFrame{
    public JFrameExampleTODO() throws HeadlessException {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HelloWorldSwing");
        JLabel label = new JLabel("Hello World");
        getContentPane().add(label);
        /*JPanel cards = new JPanel(new CardLayout());
        JPanel card1 = new JPanel();
        JPanel card2 = new JPanel();
        cards.add(card1,"card1");
        cards.add(card2,"card2");
        setContentPane(cards);*/
        
        pack();
        setVisible(true);
        
        
    }
}
