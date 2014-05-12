<%@page import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.Scope"%>
<%@ page import="java.util.jar.Attributes.Name"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="bean0" class="DTO.Bean" scope="page" />
	<jsp:useBean id="bean1" class="DTO.Bean" scope="request" />
	<jsp:useBean id="bean2" class="DTO.Bean" scope="session" />
	<jsp:useBean id="bean3" class="DTO.Bean" scope="application" />
	<a href="./Index.jsp">Index</a>
	<br />
	<br />${bean0.attrib1}
	<br />
	<jsp:getProperty property="attrib2" name="bean0" /><br />
	<br /> ${bean1.attrib1}
	<br /> <%=bean1.getAttrib1() %>
	<br />
	<jsp:getProperty property="attrib2" name="bean1" /><br />
	<br /> ${bean2.attrib1}
	<br />
	<jsp:getProperty property="attrib2" name="bean2" /><br />
	<br /> ${bean3.attrib1}
	<br />
	<jsp:getProperty property="attrib2" name="bean3" /><br />
	<br />
</body>
</html>