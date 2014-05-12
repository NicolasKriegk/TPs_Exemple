/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author imie
 */
public class JavaApplication3 {

    private static class DrawCanvas extends JPanel {
        // Override paintComponent to perform your own painting

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);     // paint parent's background
            g.drawLine(10, 20, 50, 50);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setMinimumSize(new Dimension(100, 200));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                Container cont = frame.getContentPane();
                cont.setLayout(new BorderLayout());

                JLabel nord = new JLabel("G4 morpion");
                cont.add(nord, BorderLayout.NORTH);
                JLabel sud = new JLabel("IMIE copyright");
                cont.add(sud, BorderLayout.SOUTH);
                DrawCanvas canvas = new DrawCanvas();
                cont.add(canvas, BorderLayout.CENTER);
                frame.setVisible(true);
                frame.pack();


            }
        });
    }
}
