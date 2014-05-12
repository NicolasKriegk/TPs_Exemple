<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="chosenToDo" class="org.imie.DTO.ToDoDTO"
		scope="request" />
	<form method="post" action="./ToDoForm">
		<%
			if (chosenToDo.getId() != null) {
		%>
		<input type="text" name="toDoText"
			value="<jsp:getProperty property="text" name="chosenToDo" />"></input>
		<input type="hidden" name="toDoId"
			value="<jsp:getProperty property="id" name="chosenToDo" />">
		</input> <input type="submit" name="updateAction" value="modifier"> </input>
		<%
			} else {
		%>
		<jsp:forward page="./ToDoView"></jsp:forward>
		<%
			}
		%>

	</form>
</body>
</html>