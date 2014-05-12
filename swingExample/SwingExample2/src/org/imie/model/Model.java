/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.imie.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author imie
 */
public class Model extends Observable {

    List<Todo> todoList = new ArrayList<Todo>();
    Integer incTodo = 1;

    public List<Todo> getTodoList() {
        return todoList;
    }

//    public void setTodoList(List<Todo> todoList) {
//        this.todoList = todoList;
//    }
    public void addTodo(Todo todo) {
        todo.setId(incTodo++);
        todoList.add(todo);
        this.setChanged();
        this.notifyObservers(todo);
    }

    public void removeTodo(Todo todo) {
        Todo findedTodo = null;
        for (Todo todoSearch : todoList) {
            if (todoSearch.getId().equals(todo.getId())) {
                findedTodo = todoSearch;
            }
        }
        todoList.remove(findedTodo);
        this.setChanged();
        this.notifyObservers(todo);
    }

    public void updateTodo(Todo todo) {
        Todo findedTodo = null;
        for (Todo todoSearch : todoList) {
            if (todoSearch.getId().equals(todo.getId())) {
                findedTodo = todoSearch;
            }
        }
        findedTodo.setText(todo.getText());
        this.setChanged();
        this.notifyObservers(todo);
    }
}
