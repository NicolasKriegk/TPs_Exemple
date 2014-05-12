package presentataion;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Bean;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Bean bean1= new Bean();
		bean1.setAttrib1("value1.1");
		bean1.setAttrib2("value1.2");
		
		request.setAttribute("bean1", bean1);
		
		Bean bean2= new Bean();
		bean2.setAttrib1("value2.1");
		bean2.setAttrib2("value2.2");
		request.getSession().setAttribute("bean2", bean2);
		
		
		Bean bean3= new Bean();
		bean3.setAttrib1("value3.1");
		bean3.setAttrib2("value3.2");
		
		request.getServletContext().setAttribute("bean3", bean3);
		
		getServletContext().getRequestDispatcher("/jsp1.jsp").forward(request,response);
		//response.sendRedirect("jsp1.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
