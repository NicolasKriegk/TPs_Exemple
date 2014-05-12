<?xml version="1.0" encoding="UTF-8" ?>
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
	<form method="post" action="./ToDoForm">
		<c:set var="redirectToView" value="${empty chosenToDo.id}" />
		<c:if test="${! redirectToView}">
			<input type="text" name="toDoText" value="${chosenToDo.text}"></input>
			<input type="hidden" name="toDoId" value="${chosenToDo.id}">
			</input>
			<fmt:message key="button.modifier" bundle="${bundleLabel}"
				var="modifierLabel" />
			<input type="submit" name="updateAction" value="${modifierLabel}">
			</input>
		</c:if>
		<c:if test="${redirectToView}">
			<jsp:forward page="./ToDoView"></jsp:forward>
		</c:if>
	</form>
</body>
</html>