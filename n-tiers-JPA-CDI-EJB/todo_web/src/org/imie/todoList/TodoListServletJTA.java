package org.imie.todoList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 * Servlet implementation class toListServlet
 */
@WebServlet("/TodoListServletJTA")
public class TodoListServletJTA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource
	private UserTransaction tx;

	@PersistenceUnit(unitName = "todo_jpa")
	private EntityManagerFactory emf;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodoListServletJTA() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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

		for (ToDoDTO toDoDTO : results) {
			out.print(toDoDTO.getId());
			out.print(" : ");
			out.println(toDoDTO.getLabel());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
