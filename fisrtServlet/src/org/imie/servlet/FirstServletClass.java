package org.imie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServletClass
 */
@WebServlet(description = "la premiere servlet de DLCDI", urlPatterns = { "/FirstServletClass" })
public class FirstServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FirstServletClass() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("YIPI!!!");
		PrintWriter writer = response.getWriter();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

		writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		writer.println("<LINK rel=stylesheet type=\"text/css\" href=\"/style.css\">");
		writer.println("<title>" + "TITRE" + "</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<span class=\"styleMessage\">");
		writer.println("YIPI HTML!!! il est :"+simpleDateFormat.format(new Date()));
		writer.println("</span>");
		writer.println("</body>");
		writer.println("</html>");
		
		response.setContentType("text/html");
	}
}
