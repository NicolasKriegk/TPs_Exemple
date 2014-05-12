package org.imie.todoList;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ToDoDAO {

	@PersistenceContext(unitName = "todo_jpa")
	EntityManager entityManager;
	
	public List<ToDoDTO> getAllTodo(){
	    TypedQuery<ToDoDTO> query = entityManager.createQuery("SELECT todo FROM ToDoDTO todo", ToDoDTO.class);
	    List<ToDoDTO> results = query.getResultList();
	    return results;
	}
	
}
