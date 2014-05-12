package presentataion;

import java.io.IOException;
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

import DTO.Bean;

/**
 * Servlet implementation class ServletJSTL
 */
@WebServlet("/ServletJSTL")
public class ServletJSTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletJSTL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat formater = new SimpleDateFormat("dd/mm/yyy");
		ArrayList<Bean> beanList = new ArrayList<Bean>();
		Bean bean1= new Bean();
		bean1.setAttrib1("value1.1");
		bean1.setAttrib2("value1.2");
		bean1.setCheck(true);
		try {
			bean1.setDate(formater.parse("01/01/2013"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		beanList.add(bean1);
		
		Bean bean2= new Bean();
		bean2.setAttrib1("value2.1");
		bean2.setAttrib2("value2.2");
		bean2.setCheck(false);
		try {
			bean2.setDate(formater.parse("01/01/2013"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		beanList.add(bean2);
		
		Bean bean3= new Bean();
		bean3.setAttrib1("value3.1");
		bean3.setAttrib2("value3.2");
		bean3.setCheck(true);
		try {
			bean3.setDate(formater.parse("01/01/2013"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		beanList.add(bean3);
		
		request.setAttribute("beanList", beanList);
		getServletContext().getRequestDispatcher("/jspJSTL.jsp").forward(request,response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
