

<p><a href="${pageContext.request.contextPath }/offers">Show current offers</a></p>
<p><a href="${pageContext.request.contextPath }/createoffer">Create offer</a></p>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p><a href='<c:url value="/admin"></c:url>'>Admin</a></p>
</sec:authorize>