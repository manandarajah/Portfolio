<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Please sign in</title>
    <!-- <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/> -->
    <link href="${pageContext.request.contextPath }/static/css/main.css" rel="stylesheet" type="text/css">
  </head>
  <body>
     <div class="container">
     
     <c:if test="${params.error != null }">
     	<p class="error">Login failed! Check that your username and password are correct</p>
     </c:if>
     
      <form class="form-signin" method="post" action="${pageContext.request.contextPath}/login">
        <h2 class="form-signin-heading">Please sign in</h2>
        <p>
          <label for="username" class="sr-only">Username</label>
          <input type="text" id="username" name="username" class="control" placeholder="Username" required autofocus>
        </p>
        <p>
          <label for="password" class="sr-only">Password</label>
          <input type="password" id="password" name="password" class="control" placeholder="Password" required>
        </p>
        <p>
          <label for="rememberme" class="sr-only">Remember me: </label>
          <input type="checkbox" id="rememberme" name="rememberme" class="control" checked>
        </p>
		<input name="_csrf" type="hidden" value="a8f2bdf2-33ac-4987-8fa8-a0282b0c97bb" />
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
      <p><a href='<c:url value="/newaccount"></c:url>'>Create new account</a></p>
	</div>
</body>
</html>