<%-- 
    Document   : ReserveRooms
    Created on : 30-Nov-2015, 12:51:16 PM
    Author     : burrelmi
--%>

<%@page import="burrell.michael.data.Room"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="reservation" scope="session"
             class="burrell.michael.data.Reservation">
    <%-- code that's executed if this session variable
    is being constructed --%>
    <jsp:setProperty name="reservation"
                     property="name"
                     value='<%= request.getParameter("name") %>'/>
    <jsp:setProperty name="reservation"
                 property="phoneNumber"
                 param="phone"/>
    <%
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    reservation.setCheckIn(
            format.parse(request.getParameter("checkin")));
    reservation.setCheckOut(
            format.parse(request.getParameter("checkout")));
    reservation.setCreditCardNumber(request.getParameter("creditcard"));
    reservation.setRooms(new ArrayList<Room>());
    %>
</jsp:useBean>
<%
if (request.getParameter("roomtype") != null) {
    String roomType = request.getParameter("roomtype");
    int numAdults = Integer.parseInt(request.getParameter("numadults"));
    int numKids = Integer.parseInt(request.getParameter("numchildren"));
    boolean smoking = request.getParameter("smoking") != null;
    Room newRoom = new Room(numAdults, numKids, roomType, smoking);
    reservation.getRooms().add(newRoom);
}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Holiday Outt</title>
    </head>
    <body>
        <h1>Hello <%= reservation.getName() %>!</h1>
        
        <h2>Rooms currently part of your reservation:</h2>
        <table>
            <tr><th>Room type</th><th>Number of adults/children</th>
                <th>Smoking?</th></tr>
            <%
            for (Room r : reservation.getRooms()) {
            %>
            <tr><td><%= r.getRoomType() %></td>
                <td><%= r.getNumberOfAdults() %>/<%= r.getNumberOfChildren() %></td>
                <td><%= r.isSmoking() %></td></tr>
            <%
            }
            %>
            <tr>
            <form method="GET" action="ReserveRooms.jsp">
                <td>
                    <select name="roomtype">
                        <option value="standard">Standard room</option>
                        <option value="suite">Suite</option>
                    </select>
                </td>
                <td>
                    <input type="number" name="numadults">/
                    <input type="number" name="numchildren">
                </td>
                <td>
                    <input type="checkbox" name="smoking">
                </td>
                <td>
                    <input type="submit" value="Add additional room">
                </td>
            </form>
            </tr>
        </table>
            
        <form method="GET" action="FinishReservation.jsp">
            <input type="submit" value="Finished adding rooms">
        </form>
    </body>
</html>
