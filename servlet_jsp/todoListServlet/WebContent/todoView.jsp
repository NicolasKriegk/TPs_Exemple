<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="org.imie.DTO.ToDoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<fmt:setBundle basename="org.imie.label" var="bundleLabel" />
	<form method="post" action="./ToDoView">
		<fmt:message key="button.ajouter" bundle="${bundleLabel}"
			var="ajouterLabel" />
		<input type="text" name="toDoInsert"> </input> <input type="submit"
			name="insertAction" value="${ajouterLabel}"> </input>
	</form>
	<ul>
		<c:forEach var="toDoBean" items="${toDoList}"
			varStatus="forToDoStatuts">

			<li><a href="./ToDoView?numLigne=${forToDoStatuts.index + 1}">
					<c:out value="${toDoBean.text}"></c:out>
			</a></li>
		</c:forEach>

	</ul>
</body>
</html>