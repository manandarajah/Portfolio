<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assignment 1</title>
</head>
<body>
	<c:forEach var="movie" items="${movies}">
		<div>
			<h2>${movie.name}</h2>
			<h3>${movie.director}</h3>
			<p>Rating: ${movie.rating}/5<br><br>${movie.review}</p>
		</div>
	</c:forEach>
	<form method="post" action="HomeController">
		<input type="text" name="search" required><br>
		<input type="radio" name="filter" value="name">Filter by name
		<input type="radio" name="filter" value="rating">Filter by rating
		<input type="radio" name="filter" value="director">Filter by director<br>
		<input type="submit">
	</form>
	<h4>Write a Review</h4>
	<form method="post" name="form2" action="HomeController">
		<input type="text" name="movie" required><br>
		<textarea name="comment" required></textarea><br>
		<input type="submit" name="submitReview" value="Add Review">
	</form>
</body>
</html>