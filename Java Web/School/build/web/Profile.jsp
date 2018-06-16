<%-- 
    Document   : Profile
    Created on : 11-Dec-2015, 10:02:52 AM
    Author     : Mugiesshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean scope="session" id="user" class="model.User"></jsp:useBean>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ul>
            <li>Username: $(user.username)</li>
            <li>Teacher: $(user.teacher)</li>
        </ul>
    </body>
</html>
