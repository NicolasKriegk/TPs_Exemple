package org.imie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imie.DTO.ToDoDTO;

import sun.org.mozilla.javascript.tools.debugger.GuiCallback;

import com.sun.corba.se.spi.orbutil.fsm.Guard;

/**
 * Servlet implementation class ToDoView
 */
@WebServlet("/ToDoView")
// @WebServlet()
public class ToDoView extends HttpServlet {
	private static final String TITRE = "Todo List";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToDoView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// récupération le paramètre de la requête
		String numLigneParam = request.getParameter("numLigne");
		// transformation du paramètre dans le type désiré
		Integer numLigne = null;
		if (numLigneParam != null) {
			numLigne = Integer.valueOf(numLigneParam);
		}

		if (numLigne != null) {
			// si paramètres satisfaisant, forward sur le formulaire avec
			// passage de paramètre par la request
			ToDoDTO toDoDTO = getToDoList(request).get(numLigne - 1);
			request.setAttribute("chosenToDo", toDoDTO);
			request.getRequestDispatcher("./ToDoForm").forward(request,
					response);
		} else {
			// sinon, affichage de la liste
			request.getRequestDispatcher("./todoView.jsp").forward(request,
					response);
			// PrintWriter writer = response.getWriter();
			// buildPage(request, writer);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String insertToDo = request.getParameter("toDoInsert");
		if (insertToDo != null) {
			// System.out.println(insertToDo);
			ToDoDTO toDoDTO = new ToDoDTO();
			toDoDTO.setText(insertToDo);
			toDoDTO.setId((int) Math.floor(Math.random() * 10000));
			addToDoList(request, toDoDTO);
		}
		request.getRequestDispatcher("./todoView.jsp").forward(request,
				response);
		// PrintWriter writer = response.getWriter();
		// buildPage(request, writer);
	}

	private List<ToDoDTO> getToDoList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object toDoListObject = session.getAttribute("toDoList");
		List<ToDoDTO> retour = new ArrayList<ToDoDTO>();
		if (toDoListObject != null) {
			retour = (List<ToDoDTO>) toDoListObject;
		}
		return retour;
	}

	private void addToDoList(HttpServletRequest request, ToDoDTO toDoDTO) {
		List<ToDoDTO> toDoDTOs = getToDoList(request);
		toDoDTOs.add(toDoDTO);
		HttpSession session = request.getSession();
		session.setAttribute("toDoList", toDoDTOs);
	}

}
