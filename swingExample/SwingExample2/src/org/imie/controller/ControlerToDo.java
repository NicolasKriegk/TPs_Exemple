/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.imie.controller;

import java.util.Observable;
import java.util.Observer;
import org.imie.model.Model;
import org.imie.model.Todo;
import org.imie.model.TodoState;
import org.imie.swingexample2.ObserverMessage;
import org.imie.vue.VueTodo;

/**
 *
 * @author imie
 */
public class ControlerToDo implements Observer {

    Model model;

    public ControlerToDo(Model model) {
        this.model = model;
    }

    @Override
    public void update(Observable o, Object o1) {
        ObserverMessage observerMessage = (ObserverMessage) o1;
        if (observerMessage.getCommande().compareTo("addTodo") == 0) {
            Todo todo = new Todo();
            todo.setText(observerMessage.getParameter().toString());
            model.addTodo(todo);
        }

        if (observerMessage.getCommande().compareTo("removeTodo") == 0) {
            Todo todo = (Todo)observerMessage.getParameter();
            model.removeTodo(todo);
        }
        
        if (observerMessage.getCommande().compareTo("updateTodo") == 0) {
            Todo todo = (Todo)observerMessage.getParameter();
            model.updateTodo(todo);
        }
    }
}
