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

import org.imie.DTO.ToDoDTO;

/**
 * Servlet implementation class ToDoForm
 */
@WebServlet("/ToDoForm")
public class ToDoForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TITRE = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToDoForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		ServletContext servletContext = request.getSession()
//				.getServletContext();
//		// System.out.println(toDoDTO.getText());
//		// récupération de l'objet en context (issue du setAttribute fait avant
//		// le forward)
//		Object chosenToDoObject = servletContext.getAttribute("chosenToDo");
//		ToDoDTO toDoDTO = null;
//		if (chosenToDoObject != null) {
//			toDoDTO = (ToDoDTO) chosenToDoObject;
//		}
//		if (toDoDTO != null) {
//			//PrintWriter writer = response.getWriter();
//			// writer.println(toDoDTO.getText());
//			//buildPage(request, writer, toDoDTO);
//		}
		request.getRequestDispatcher("./todoForm.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String toDoText = request.getParameter("toDoText");
		String toDoIdParam = request.getParameter("toDoId");
		Integer toDoId = null;
		if (toDoIdParam != null) {
			toDoId = Integer.valueOf(toDoIdParam);
		}
		if (toDoId != null) {
			Object toDoListSession = request.getSession().getAttribute(
					"toDoList");
			List<ToDoDTO> toDoDTOs = new ArrayList<ToDoDTO>();
			if (toDoListSession != null) {
				toDoDTOs = (List<ToDoDTO>) toDoListSession;
			}

			for (ToDoDTO toDoDTO : toDoDTOs) {
				if (toDoDTO.getId().equals(toDoId)) {
					toDoDTO.setText(toDoText);
				}
			}
		}
		
		response.sendRedirect("./ToDoView");
		
	}

}
