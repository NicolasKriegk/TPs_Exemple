/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.imie.swingexample2;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.imie.controller.ControlerToDo;
import org.imie.model.*;
import org.imie.vue.VueTodo;

/**
 *
 * @author imie
 */
public class Launcher {

    public static class ModelWorker extends SwingWorker {

        Model model;

        public ModelWorker(Model model) {
            this.model = model;

        }

        @Override
        protected Object doInBackground() throws Exception {
            Todo todo1 = new Todo();
            todo1.setText("todo1");
            todo1.setId(1);
            Todo todo2 = new Todo();
            todo2.setText("todo2");
            todo2.setId(2);
            Todo todo3 = new Todo();
            todo3.setText("todo3");
            todo3.setId(3);
            Thread.sleep(1000);
            model.addTodo(todo1);
            Thread.sleep(1000);
            model.addTodo(todo2);
            Thread.sleep(1000);
            model.addTodo(todo3);
            return null;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VueTodo vueTodo = new VueTodo();
                vueTodo.init();
                Model model = new Model();
                vueTodo.setModel(model);
                model.addObserver(vueTodo);
                ControlerToDo controlerToDo = new ControlerToDo(model);
                vueTodo.addObserver(controlerToDo);


                ModelWorker worker = new ModelWorker(model);
                worker.execute();

            }
        });
    }
}
