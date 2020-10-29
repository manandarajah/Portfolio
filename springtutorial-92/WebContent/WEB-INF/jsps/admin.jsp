<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin page</title>
<link href="${pageContext.request.contextPath }/static/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h3>Authorized users only!</h3>
	
	<table class="formtable">
		<tr><td>Username</td><td>Email</td><td>Role</td><td>Enabled</td></tr>
		
		<c:forEach items="${users }" var="user">
			<tr>
				<td><c:out value="${user.username })"></c:out></td>
				<td><c:out value="${user.email }"></c:out></td>
				<td><c:out value="${user.authority }"></c:out></td>
				<td><c:out value="${user.enabled }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>