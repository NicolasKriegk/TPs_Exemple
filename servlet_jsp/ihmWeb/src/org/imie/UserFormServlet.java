package org.imie;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imie.DTO.CursusDTO;
import org.imie.DTO.UserDTO;
import org.imie.factory.BaseConcreteFactory;
import org.imie.service.exceptions.ServiceException;
import org.imie.service.interfaces.ICursusService;
import org.imie.service.interfaces.IUserService;
import org.imie.transactionalFramework.TransactionalConnectionException;

/**
 * Servlet implementation class UserFormServlet
 */
@WebServlet("/UserFormServlet")
public class UserFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String loginUser = request.getParameter("login");
		UserDTO filtre = new UserDTO();
		filtre.setLogin(loginUser);
		UserDTO userAAfficher = null;
		List<UserDTO> userDTOFinded = new ArrayList<UserDTO>();
		try {
			userDTOFinded = BaseConcreteFactory.getInstance()
					.createUserService().getUsers(filtre);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransactionalConnectionException e) {
			// TODO Auto-generated catch block
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
		if (userDTOFinded.size() == 0) {
			writer.println("<h1>Login inconnu</h1>");
		} else if (userDTOFinded.size() > 1) {
			writer.println("<h1>Plus de 1 user avec ce login</h1>");
		} else {
			UserDTO userInForm = userDTOFinded.get(0);
			writer.println("<div>");

			writer.println("<div class=\"tableauContainer\">");
			writer.println("<form id=\"formUser\" method=\"POST\" action=\"UserFormServlet\">");
			writer.println("<div >");
			writer.println("<input type=\"hidden\" value=\""
					+ userInForm.getId() + "\" name=\"userId\"/>");

			writer.println("<div class=\"ligneTableauNormalLine\">");

			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableau200 celluleTableauLabel\">");
			writer.println("Nom :");
			writer.println("</div>");
			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableau200\">");
			writer.println("<input type=\"text\" value=\""
					+ userInForm.getNom() + "\" name=\"userNom\"/>");
			// writer.println(userInForm.getNom());
			writer.println("</div>");
			writer.println("</div>");

			writer.println("<div class=\" ligneTableauNormalLine\">");
			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableau200 celluleTableauLabel\">");
			writer.println("Prenom :");
			writer.println("</div>");
			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableau200\">");
			writer.println("<input type=\"text\" value=\""
					+ userInForm.getPrenom() + "\" name=\"userPrenom\"/>");
			// writer.println(userInForm.getPrenom());
			writer.println("</div>");
			writer.println("</div>");

			writer.println("<div class=\"ligneTableauNormalLine\">");
			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableau200 celluleTableauLabel \">");
			writer.println("Age :");
			writer.println("</div>");
			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableau200\">");
			writer.println(userInForm.getAge());
			writer.println("</div>");
			writer.println("</div>");
			writer.println("</div>");

			writer.println("<div class=\"ligneTableauNormalLine\">");
			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableau200 celluleTableauLabel\">");
			writer.println("Date de naissance :");
			writer.println("</div>");
			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableau200\">");
			writer.println("<input type=\"text\" value=\""
					+ new SimpleDateFormat("dd/MM/yyyy").format(userInForm
							.getDateNaiss()) + "\" name=\"userDateNaiss\"/>");
			// writer.println(new
			// SimpleDateFormat("dd/MM/yyyy").format(userInForm.getDateNaiss()));
			writer.println("</div>");
			writer.println("</div>");

			writer.println("<div class=\"ligneTableauNormalLine\">");
			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableau200 celluleTableauLabel\">");
			writer.println("Cursus :");
			writer.println("</div>");
			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableau200\">");
			writer.println("<select name=\"userCursus\" form=\"formUser\">");
			ICursusService cursusService = BaseConcreteFactory.getInstance()
					.createCursusService();
			List<CursusDTO> cursusDTOs = new ArrayList<CursusDTO>();
			try {
				cursusDTOs = cursusService.findAll(new CursusDTO());
			} catch (TransactionalConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (CursusDTO cursusDTO : cursusDTOs) {
				Boolean isUserCursus = cursusDTO.getId().equals(
						userInForm.getCursus().getId());
				writer.println("<option "
						+ (isUserCursus ? "selected=\"selected\" " : "")
						+ "value=\"" + cursusDTO.getId() + "\">"
						+ cursusDTO.getLibelle() + "</option>");
			}
			writer.println("</select>");
			// if (userInForm.getCursus()!=null){
			// writer.println(userInForm.getCursus().getLibelle());
			// }
			writer.println("</div>");
			writer.println("</div>");

			writer.println("<div class=\"ligneTableauNormalLine\">");

			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableauLabel\">");
			writer.println("<a class=\"iconRetour\" href=\"./UserServlet\">");
			writer.println("</a>");
			writer.println("</div>");

			writer.println("<div class=\"celluleTableauInLine celluleTableau celluleTableauLabel\">");
			writer.println("<input type=\"submit\" value=\"modifier\"/>");
			writer.println("</div>");

			writer.println("</div>");

			writer.println("</div>");
			writer.println("</form>");
			writer.println("</div>");

			writer.println("</div>");
		}
		writer.println("</body>");
		writer.println("</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userIdParam = request.getParameter("userId");
		Integer userId = null;
		if (userIdParam != null) {
			userId = Integer.valueOf(userIdParam);
		}
		if (userId != null) {
			IUserService userService = BaseConcreteFactory.getInstance()
					.createUserService();
			UserDTO filtre = new UserDTO();
			filtre.setId(userId);
			List<UserDTO> userDTOs = new ArrayList<UserDTO>();
			try {
				userDTOs = userService.getUsers(filtre);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionalConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (userDTOs.size() > 0) {
				UserDTO userDTOToUpdate = userDTOs.get(0);
				String userNomParam = request.getParameter("userNom");
				String userPrenomParam = request.getParameter("userPrenom");
				String userDateNaissParam = request.getParameter("userDateNaiss");
				String userCursusParam = request.getParameter("userCursus");
				userDTOToUpdate.setNom(userNomParam);
				userDTOToUpdate.setPrenom(userPrenomParam);
				
				try {
					Date userDate = new SimpleDateFormat("dd/MM/yyyy").parse(userDateNaissParam);
					userDTOToUpdate.setDateNaiss(userDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
								
				Integer cursusId = null;
				if (userCursusParam != null) {
					cursusId = Integer.valueOf(userCursusParam);
				}
				
				if (cursusId!=null){					
					ICursusService cursusService= BaseConcreteFactory.getInstance().createCursusService();
					CursusDTO filtreCursus = new CursusDTO();
					filtreCursus.setId(cursusId);
					List<CursusDTO> cursusDTOs = new ArrayList<CursusDTO>();
					try {
						cursusDTOs = cursusService.findAll(filtreCursus);
					} catch (TransactionalConnectionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (cursusDTOs.size()>0){
						userDTOToUpdate.setCursus(cursusDTOs.get(0));
					}
				}
				
				try {
					userService.updateUser(userDTOToUpdate);
				} catch (TransactionalConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				response.sendRedirect("./UserServlet");
			}
		}

	}
}
