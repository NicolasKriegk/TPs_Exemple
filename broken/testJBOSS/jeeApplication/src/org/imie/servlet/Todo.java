package org.imie.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.IHMBean.ToDoBean;
import org.imie.persitanceCommand.DataBaseCommand;
import org.imie.persitanceCommand.DataBaseSourceJNDI;
import org.imie.persitanceCommand.IDataBaseConnection;

/**
 * Servlet implementation class Todo
 */
@WebServlet("/Todo")
public class Todo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IDataBaseConnection dataBaseConnection = new DataBaseSourceJNDI();
	

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

		DataBaseCommand dataBaseCommand = buildSelectCommand();

		List<ToDoBean> todos = new ArrayList<ToDoBean>();
		todos = castCommandResult(dataBaseCommand);

		request.setAttribute("todos", todos);
		request.getRequestDispatcher("ToDo.jsp").forward(request, response);


	}

	private DataBaseCommand buildSelectCommand() {
		DataBaseCommand dataBaseCommand = new DataBaseCommand(dataBaseConnection,
				"select * from todo", true) {
			@Override
			protected Object ResultSetTreatment(ResultSet resultSet)
					throws SQLException {
				List<ToDoBean> todos = new ArrayList<ToDoBean>();
				while (resultSet.next()) {
					ToDoBean toDoBean = new ToDoBean();
					toDoBean.setNumero(resultSet.getDouble("id"));
					toDoBean.setTexte(resultSet.getString("libelle"));
					todos.add(toDoBean);
				}
				return todos;
			}
		};
		return dataBaseCommand;
	}

	@SuppressWarnings("unchecked")
	private List<ToDoBean> castCommandResult(DataBaseCommand dataBaseCommand) {
		return (List<ToDoBean>) dataBaseCommand.execute();
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

		DataBaseCommand dataBaseCommand = new DataBaseCommand(dataBaseConnection,
				"insert into todo(libelle) values ('"
						+ request.getParameter("newTodo") + "')", false) {

		};
		dataBaseCommand.execute();
		
		
		DataBaseCommand dataBaseCommand2 = buildSelectCommand();

		List<ToDoBean> todos = new ArrayList<ToDoBean>();
		todos = castCommandResult(dataBaseCommand2);

		request.setAttribute("todos", todos);
		request.getRequestDispatcher("ToDo.jsp").forward(request, response);

	}


}
