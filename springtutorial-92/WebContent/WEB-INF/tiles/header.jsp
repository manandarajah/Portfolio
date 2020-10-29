<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="<c:url value='/'></c:url>">Offers</a>

<sec:authorize access="!isAuthenticated()">
	<p><a href='<c:url value="/login"></c:url>'>Log in</a></p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<p><a href='<c:url value="/loggedout"></c:url>'>Log out</a></p>
</sec:authorize>