package org.imie.todoList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
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
	
	@EJB TestToDoBeanLocal testToDoBean1;
	@EJB(lookup="java:module/TestToDoBean!org.imie.todoList.TestToDoBeanLocal") TestToDoBeanLocal testToDoBean2;
	@EJB(lookup="java:module/TestToDoBean!org.imie.todoList.TestToDoBeanRemote") TestToDoBeanRemote testToDoBean3;
	
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
		out.println(testToDoBean1.toString());
		out.println(testToDoBean2.toString());
		out.println(testToDoBean3.toString());
		EntityManager em = null;
		List<ToDoDTO> results = null;
//		try {
//			tx.begin();
//			em = emf.createEntityManager();
//			TypedQuery<ToDoDTO> query = em.createQuery(
//					"SELECT todo FROM ToDoDTO todo", ToDoDTO.class);
//			results = query.getResultList();
//
//			tx.commit();
//		} catch (Exception e) {
//			try {
//				tx.rollback();
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//		} finally {
//			if (em != null) {
//				em.close();
//			}
//		}
		results=testToDoBean1.getAllDTO();
		for (ToDoDTO toDoDTO : results) {
			out.print(toDoDTO.getId());
			out.print(" : ");
			out.println(toDoDTO.getLabel());
		}
		results=testToDoBean2.getAllDTO();
		for (ToDoDTO toDoDTO : results) {
			out.print(toDoDTO.getId());
			out.print(" : ");
			out.println(toDoDTO.getLabel());
		}
		results=testToDoBean3.getAllDTO();
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
