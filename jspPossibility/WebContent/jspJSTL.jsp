<%@page import="DTO.Bean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="./Index.jsp">Index</a>
	<table border="1">
		<c:forEach var="bean" items="${beanList}">
			<tr>

				<td><c:out value="${bean.attrib1}"></c:out></td>
				<td><c:out value="${bean.attrib2}"></c:out></td>
				<td><c:choose>
						<c:when test="${bean.check}">
							<span>
						</c:when>
						<c:when test="${!bean.check}">
							<span style="background-color: red;">
						</c:when>
					</c:choose> <c:out value="${bean.check}"></c:out> </span></td>
				<td><fmt:formatDate value="${bean.date}" var="dateParsed"
						pattern="dd-MM-yyyy" /> <c:out value="${dateParsed}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>