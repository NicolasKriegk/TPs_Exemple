package org.imie.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.imie.IHMBean.ToDoBean;

/**
 * Servlet implementation class Todo
 */
@WebServlet("/Todo")
public class Todo extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		try {

			
			List<ToDoBean> todos = new ArrayList<ToDoBean>();

			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:jboss");

			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("pgsqlDataSource");

			Connection con = ds.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from todo");
			while (rs.next()) {
				ToDoBean toDoBean = new ToDoBean();
				toDoBean.setNumero(rs.getDouble("id"));
				toDoBean.setTexte(rs.getString("libelle"));
				todos.add(toDoBean);
			}
			con.close();
			stmt.close();
			rs.close();

			request.setAttribute("todos", todos);
			request.getRequestDispatcher("ToDo.jsp").forward(request, response);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			List<ToDoBean> todos = new ArrayList<ToDoBean>();

			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:jboss");
			
			String label = null;
			ResourceBundle helloWorldResourceBundle = ResourceBundle.getBundle("org.imie.ressources.ihm");
			try{
				label = helloWorldResourceBundle.getString("ajouter.label");
			}catch( Exception e){
				label = "I don't know what to say!";
			}
			request.setAttribute("ajouterLabel", label);
			

			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("pgsqlDataSource");

			synchronized (ds) {
				Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				stmt.execute("insert into todo(libelle) values ('"
						+ request.getParameter("newTodo") + "')");
				ResultSet rs = stmt.executeQuery("select * from todo");
				while (rs.next()) {
					ToDoBean toDoBean = new ToDoBean();
					toDoBean.setNumero(rs.getDouble("id"));
					toDoBean.setTexte(rs.getString("libelle"));
					todos.add(toDoBean);
				}
				
				rs.close();
				stmt.close();
				con.close();
			}
			request.setAttribute("todos", todos);
			request.getRequestDispatcher("ToDo.jsp").forward(request, response);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
