
<%@page import="org.imie.DTO.ToDoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fmt:setBundle basename="org.imie.ressources.ihm" />
	<table border="1">
		<form action="Todo" method="post">
			<br /> <label><fmt:message key="ajouter.label" /> : </label><input
				type="text" name="newTodo" /><input type="submit"
				value="<fmt:message key="ajouter.submit"/>">
		</form>
		<c:forEach var="bean" items="${todos}">
			<tr>

				<td><c:out value="${bean.numero}"></c:out></td>
				<td><c:out value="${bean.texte}"></c:out></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>