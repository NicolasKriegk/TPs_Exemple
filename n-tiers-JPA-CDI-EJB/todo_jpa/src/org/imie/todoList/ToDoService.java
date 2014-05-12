package org.imie.todoList;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

public class ToDoService implements iToDoService {
	@Resource
	private UserTransaction tx;

	@PersistenceUnit(unitName = "todo_jpa")
	private EntityManagerFactory emf;

	@Override
	public List<ToDoDTO> getAllToDo() {
		EntityManager em = null;
		List<ToDoDTO> results = null;
		try {
			tx.begin();
			em = emf.createEntityManager();
			TypedQuery<ToDoDTO> query = em.createQuery(
					"SELECT todo FROM ToDoDTO todo", ToDoDTO.class);
			results = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return results;
	}
}
