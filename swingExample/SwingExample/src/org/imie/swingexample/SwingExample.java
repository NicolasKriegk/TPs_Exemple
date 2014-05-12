/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.imie.swingexample;

import javax.swing.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author imie
 */
public class SwingExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
                JFrameExample jFrameExample = new JFrameExample();
            }
        });           
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
