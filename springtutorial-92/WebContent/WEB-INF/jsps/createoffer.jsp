<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create offer</title>
<link href="${pageContext.request.contextPath }/static/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<sf:form method="post"
		action="${pageContext.request.contextPath }/docreate" modelAttribute="offer">
		<table>
			<tr><td>Name: </td><td><sf:input class="control" path="name" name="name" type="text"/><br><sf:errors path="name" cssClass="error"></sf:errors></td></tr>
			<tr><td>Email: </td><td><sf:input class="control" path="email" name="email" type="text"/><br><sf:errors path="email" cssClass="error"></sf:errors></td></tr>
			<tr><td>Your Offer: </td><td><sf:textarea class="control" path="text" name="text" rows="10" cols="10" ></sf:textarea><br><sf:errors path="text" cssClass="error"></sf:errors></td></tr>
			<tr><td></td><td><input class="control" value="Create Offer" type="submit"/></td></tr>
		</table>	
	</sf:form>
</body>
</html>