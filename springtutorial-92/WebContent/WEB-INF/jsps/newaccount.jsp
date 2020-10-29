<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Account</title>
<link href="${pageContext.request.contextPath }/static/css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery.js"></script>
<script type="text/javascript">
	
	function onLoad() {
		$("#password").keyup(checkPasswordsMatch);
		$("#cpass").keyup(checkPasswordsMatch);
	}
	
	function checkPasswordsMatch() {
		var password = $("#password").val();
		var cof = $("#cpass").val();
		
		if (password.length < 3 || cof.length < 3) {
			return;
		}
		
		if (password === cof) {
			$("#matchpass").text("<fmt:message key='MatchedPasswords.user.password'></fmt:message>");
			$("#matchpass").addClass("valid");
			$("#matchpass").removeClass("error");
		}
		
		else {
			$("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password'></fmt:message>");
			$("#matchpass").addClass("error");
			$("#matchpass").removeClass("valid");
		}
	}
	
	$(document).ready(onLoad);
	
</script>
</head>
<body>

	<sf:form method="post" action="${pageContext.request.contextPath }/createaccount" modelAttribute="user">
		<table>
			<tr><td>Username: </td><td><sf:input class="control" path="username" name="username" type="text"/><br><sf:errors path="username" cssClass="error"></sf:errors></td></tr>
			<tr><td>Email: </td><td><sf:input class="control" path="email" name="email" type="text"/><br><sf:errors path="email" cssClass="error"></sf:errors></td></tr>
			<tr><td>Password: </td><td><sf:input id="password" class="control" path="password" name="password" type="text"/><br><sf:errors path="password" cssClass="error"></sf:errors></td></tr>
			<tr><td>Confirm Password: </td><td><input id="cpass" class="control" name="cpass" type="text"/><br><div id="matchpass"></div></td></tr>
			<tr><td></td><td><input class="control" value="Create Account" type="submit"/></td></tr>
		</table>
	</sf:form>
</body>
</html>