<%-- 
    Document   : FinishReservation
    Created on : 30-Nov-2015, 2:20:45 PM
    Author     : burrelmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="reservation" scope="session"
             class="burrell.michael.data.Reservation"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>You owe us $<%= reservation.getRooms().size() * 130 %>
            per night</h1>
    </body>
</html>
