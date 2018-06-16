<%-- 
    Document   : ReserveRooms
    Created on : 30-Nov-2015, 12:51:30 PM
    Author     : Mugiesshan
--%>

<%@page import="data.Room"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="reservation" scope="session" class="data.Reservation">
    <jsp:setProperty name="reservation" property="name" value='<%= request.getParameter("name") %>'/>
        <jsp:setProperty name="reservation" property="phoneNum" param="phone"/>
        <%
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            reservation.setCheckIn(format.parse(request.getParameter("checkin")));
            reservation.setCheckOut(format.parse(request.getParameter("checkout")));
            reservation.setCreditCard(new BigDecimal(request.getParameter("creditcard")));
            reservation.setRooms(new ArrayList<Room>());
        %>;
</jsp:useBean>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Holiday Out</title>
    </head>
    <body>
        <h1>Hello <%= reservation.getName() %>!</h1>
    </body>
</html>
