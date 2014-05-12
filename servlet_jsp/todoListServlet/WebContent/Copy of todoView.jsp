<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="org.imie.DTO.ToDoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="toDoList" class="java.util.ArrayList" scope="session" />
	<form method="post" action="./ToDoView">
		<input type="text" name="toDoInsert"> </input> <input type="submit"
			name="insertAction" value="ajouter"> </input>
	</form>
	<ul>
		<%
			Integer i = 1;

			for (Object toDoDTO : toDoList) {
		%>
		<li><a href="./ToDoView?numLigne=<%=i%>"> <%=((ToDoDTO) toDoDTO).getText()%>
		</a></li>
		<%
			i++;
			}
		%>
	</ul>
</body>
</html>