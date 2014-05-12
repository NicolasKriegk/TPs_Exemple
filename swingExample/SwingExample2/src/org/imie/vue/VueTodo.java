/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.imie.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.imie.model.Model;
import org.imie.model.Todo;
import org.imie.model.TodoState;
import org.imie.swingexample2.ObserverMessage;

/**
 *
 * @author imie
 */
public class VueTodo extends Observable implements Observer {

    public class ButtonAddAction implements ActionListener {

        public ButtonAddAction(JTextField inputField, JPanel addTodoPanel, JScrollPane jScrollPane) {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            setChanged();
            ObserverMessage observerMessage = new ObserverMessage();
            observerMessage.setCommande("addTodo");
            observerMessage.setParameter(inputToDo.getText());
            notifyObservers(observerMessage);

        }
    }

    public class ButtonRemoveAction implements ActionListener {

        Todo todo;

        public ButtonRemoveAction(Todo todo) {
            this.todo = todo;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            setChanged();
            ObserverMessage observerMessage = new ObserverMessage();
            observerMessage.setCommande("removeTodo");
            observerMessage.setParameter(todo);
            notifyObservers(observerMessage);
        }
    }

    public class MouseEditAdapter extends MouseAdapter {

        JPanel ownerPanel;
        Todo todo;

        public MouseEditAdapter(JPanel ownerPanel, Todo todo) {
            this.ownerPanel = ownerPanel;
            this.todo = todo;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            //source.setText(source.getText().concat("+"));
            JTextField editTextField = new JTextField();
            editTextField.setText(source.getText());
            editTextField.setColumns(30);
            editTextField.addKeyListener(new KeyUpdateAdapter(todo));
            ownerPanel.add(editTextField, 0);
            ownerPanel.remove(source);
            ownerPanel.revalidate();
        }
    }

    public class KeyUpdateAdapter extends KeyAdapter {

        Todo todo;

        public KeyUpdateAdapter(Todo todo) {
            this.todo = todo;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            JTextField inputUpdate = (JTextField) e.getSource();
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                todo.setText(inputUpdate.getText());
                setChanged();
                ObserverMessage observerMessage = new ObserverMessage();
                observerMessage.setCommande("updateTodo");
                observerMessage.setParameter(todo);
                notifyObservers(observerMessage);
            }
        }
    }
    JPanel panelSCrollable = null;
    JScrollPane scrollPane = null;
    JTextField inputToDo = null;
    Model model = null;

    public void init() {
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setMinimumSize(new Dimension(100, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container cont = frame.getContentPane();
        cont.setLayout(new BorderLayout());

        JLabel nord = new JLabel("G4 TODO List");
        cont.add(nord, BorderLayout.NORTH);
        JLabel sud = new JLabel("IMIE copyright");
        cont.add(sud, BorderLayout.SOUTH);

        //Panel centrale de la frame
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

        //Panel dédié au textfield pour saisir un Todo et le boutton pour ajouter
        JPanel inputPanel = new JPanel();
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        inputPanel.setLayout(new FlowLayout());
        inputToDo = new JTextField();
        inputToDo.setColumns(30);
        JButton buttonAdd = new JButton("add");
        inputPanel.add(inputToDo);
        inputPanel.add(buttonAdd);

        //Panel scrollable
        panelSCrollable = new JPanel();
        panelSCrollable.setLayout(new BoxLayout(panelSCrollable, BoxLayout.PAGE_AXIS));
        scrollPane = new JScrollPane(panelSCrollable);


        //ACTIONS
        buttonAdd.addActionListener(new ButtonAddAction(inputToDo, panelSCrollable, scrollPane));


        //ajout du inputPanel et des Todo dans le Panel Centrale
        centerPanel.add(inputPanel);
        centerPanel.add(scrollPane);


        cont.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.pack();

    }

    public void refresh(Model model) {


        panelSCrollable.removeAll();

        for (Todo todo : model.getTodoList()) {
            addTodoPanel(todo, panelSCrollable, scrollPane);
        }
        //rafraichissement
        panelSCrollable.validate();
        panelSCrollable.repaint();
        if (scrollPane != null) {
            scrollPane.revalidate();
        }
    }

    private void addTodoPanel(Todo todo, JPanel panelSCrollable, JScrollPane scrollPane) {
        //creation de la ligne
        JPanel row = new JPanel();
        //ajout du label de read de Todo
        JPanel cellTodo = new JPanel();
        cellTodo.setMinimumSize(new Dimension(200, Integer.MIN_VALUE));


        JLabel text = new JLabel(todo.getText());
        cellTodo.add(text);
        //branchement evenement d'édition
        text.addMouseListener(new MouseEditAdapter(cellTodo,todo));

        row.add(cellTodo);
        row.setLayout(new BoxLayout(row, BoxLayout.LINE_AXIS));

        //ajout du boutton de suppression
        JButton removeButton = new JButton("remove");
        //branchement de l'evenement de suppression
        removeButton.addActionListener(new ButtonRemoveAction(todo));
        row.add(removeButton);
        //presentation
        row.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panelSCrollable.add(row);
        
    }

    @Override
    public void update(Observable o, Object o1) {
        refresh(model);
    }

    public String getAddTodoText() {
        return inputToDo.getText();
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
