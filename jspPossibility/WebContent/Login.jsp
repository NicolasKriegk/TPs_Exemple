<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%-- <jsp:useBean id="errorMessage" class="java.lang.String" scope="request"/>
	<jsp:useBean id="redirectUrlLogin" class="java.lang.String" scope="session"/>
	--%>
	<form action="${redirectUrlLogin}">
		login <input id="userLogin" name="userLogin" type="text"></input> <br />
		password <input id="userPassword" name="userPassword" type="text"></input>
		<br /> <input type="submit" name="loginButton" value="loginValue"></input>
	</form>
	<br/>
	<span>${errorMessage}</span>
</body>
</html>