package org.imie.todoList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
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
@WebServlet("/TodoListServlet2")
public class TodoListServletEJB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ToDoDAO toDoDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodoListServletEJB() {
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

		List<ToDoDTO> results = toDoDAO.getAllTodo();
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
