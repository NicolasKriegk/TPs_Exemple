package presentataion;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DTO.User;

/**
 * Servlet Filter implementation class AuthentificationFilter
 */
@WebFilter(urlPatterns = "/*")
public class AuthentificationFilter implements Filter {

	private static final String LOGIN_PAGE = "Login.jsp";

	/**
	 * Default constructor.
	 */
	public AuthentificationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// ((HttpServletRequest)request).
		// authentificaiton automatisée JEE :
		// http://onjava.com/pub/a/onjava/2002/06/12/form.html

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String contextPath = httpRequest.getContextPath();
		String resource = httpRequest.getRequestURI();

		HttpSession session = httpRequest.getSession(true);
		User currentUser = (User) session.getAttribute("user");

		String formSubmitName = request.getParameter("loginButton");
		Boolean secureAcces = false;

		if (currentUser == null) {

			if (formSubmitName != null) {
				String login = request.getParameter("userLogin");
				String password = request.getParameter("userPassword");
				if ("admin".equals(login)) {
					User user = new User();
					user.setLogin(login);
					user.setPassword(password);
					session.setAttribute("user", user);

					secureAcces = true;
				} else {
					request.setAttribute("errorMessage", login
							+ " n'est pas un login valide");
				}
			}
			// returnError(request,response,"User does not exist in session!");
		} else {
			if (resource.contains("Deconnexion")) {
				session.removeAttribute("user");
				session.setAttribute("redirectUrlLogin", "Index.jsp");
			} else {
				secureAcces = true;
			}

		}

		if (secureAcces) {
			session.removeAttribute("redirectUrlLogin");
			chain.doFilter(request, response);
		} else {
			if (!resource.contains("Deconnexion")) {
				String redirectionURL = (String) session
						.getAttribute("redirectUrlLogin");

				session.setAttribute("redirectUrlLogin", resource);
			}
			RequestDispatcher requestDispatcher = httpRequest
					.getRequestDispatcher(LOGIN_PAGE);
			requestDispatcher.forward(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
