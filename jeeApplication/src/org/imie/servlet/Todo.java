package org.imie.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.DTO.ToDoBean;
import org.imie.persitanceCommand.DataBaseCommand;
import org.imie.persitanceCommand.DataBaseSourceJNDI;
import org.imie.persitanceCommand.IDataBaseSource;
import org.imie.service.TodoService;

/**
 * Servlet implementation class Todo
 */
@WebServlet("/Todo")
public class Todo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoService todoService = new TodoService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// try {

		String labelSubmit = null;
		ResourceBundle resourceBundle = ResourceBundle
				.getBundle("org.imie.ressources.ihm");
		try {
			labelSubmit = resourceBundle.getString("ajouter.submit");
		} catch (Exception e) {
			labelSubmit = "I don't know what to say!";
		}
		request.setAttribute("labelSubmit", labelSubmit);

		List<ToDoBean> todos = todoService.selectAllTodo();

		request.setAttribute("todos", todos);
		request.getRequestDispatcher("ToDo.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String label = null;
		ResourceBundle helloWorldResourceBundle = ResourceBundle
				.getBundle("org.imie.ressources.ihm");
		try {
			label = helloWorldResourceBundle.getString("ajouter.label");
		} catch (Exception e) {
			label = "I don't know what to say!";
		}
		request.setAttribute("ajouterLabel", label);

		ToDoBean newToDoBean = new ToDoBean();
		newToDoBean.setTexte(request.getParameter("newTodo"));
		todoService.insertTodo(newToDoBean);

		List<ToDoBean> todos = todoService.selectAllTodo();

		request.setAttribute("todos", todos);
		request.getRequestDispatcher("ToDo.jsp").forward(request, response);

	}

}
