package org.imie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.DTO.UserDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.exceptions.ServiceException;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IUserService userService = BaseConcreteFactory.getInstance()
				.createUserService();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		try {
			userDTOs = userService.getUsers();
		} catch (TransactionalConnectionException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		PrintWriter writer = response.getWriter();
		writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		writer.println("<LINK rel=stylesheet type=\"text/css\" href=\"./style.css\">");
		writer.println("<title>" + "TITRE" + "</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<div class=\"tableauContainer\">");
		writer.println("<div class=\"tableau\">");

		writer.println("<div class=\"ligneTableauLine ligneTableauHeader\">");
		writer.println("<div class=\"celluleTableauInTable celluleTableau200\">");
		writer.println("Nom");
		writer.println("</div>");
		writer.println("<div class=\"celluleTableauInTable celluleTableau100\">");
		writer.println("Prenom");
		writer.println("</div>");
		writer.println("<div class=\"celluleTableauInTable celluleTableau100\">");
		writer.println("Age");
		writer.println("</div>");
		writer.println("<div class=\"celluleTableauInTable celluleTableau100\">");
		writer.println("Action");
		writer.println("</div>");
		writer.println("</div>");
		Integer numLigne = 0;
		for (UserDTO userDTO : userDTOs) {
			Boolean isAlternativLigne = numLigne % 2 > 0;
			writer.println("<div class=\"ligneTableauLine"
					+ (isAlternativLigne ? " ligneTableauAlternativLine"
							: " ligneTableauNormalLine") + "\" >");

			writer.println("<div class=\"celluleTableauInTable celluleTableau celluleTableau200\">");
			writer.println(userDTO.getNom());
			writer.println("</div>");
			writer.println("<div class=\"celluleTableauInTable celluleTableau celluleTableau100\">");
			writer.println(userDTO.getPrenom());
			writer.println("</div>");
			writer.println("<div class=\"celluleTableauInTable celluleTableau celluleTableau100\">");
			writer.println(userDTO.getAge());
			writer.println("</div>");
			writer.println("<div class=\"celluleTableauInTable celluleTableau celluleTableau100\">");
			
			writer.println("<div class=\"iconEdit\">");
			writer.println("<a class=\"iconLink\" href=\"" + "./UserFormServlet?login="
					+ userDTO.getLogin() + "\">");
			writer.println("</a>");
			writer.println("</div>");
			
			//writer.println("<div class=\"fackeSpace\">");
			//writer.println("</div>");
			writer.println("</div>");

			writer.println("</div>");

			numLigne++;
		}
		writer.println("</div>");
		writer.println("</div>");
		writer.println("</body>");
		writer.println("</html>");

		response.setContentType("text/html");

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
