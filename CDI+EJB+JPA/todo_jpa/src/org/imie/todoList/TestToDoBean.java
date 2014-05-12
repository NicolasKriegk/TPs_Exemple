package org.imie.todoList;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class TestToDoBean
 */
@Stateless
public class TestToDoBean implements TestToDoBeanRemote, TestToDoBeanLocal {

	@PersistenceContext(unitName="todo_jpa") EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public TestToDoBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public List<ToDoDTO> getAllDTO(){
    	TypedQuery<ToDoDTO> query = entityManager.createQuery(
				"SELECT todo FROM ToDoDTO todo", ToDoDTO.class);
		return query.getResultList();
    }

}
