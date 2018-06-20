/*
ANSHUL
MID TERM JAVA EXAM
17TH JULY

*/

package goyal.anshul.application;


import goyal.anshul.presentation.InputRequestToServer;
import goyal.anshul.presentation.OutputResponseFromServer;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoverApplication extends HttpServlet {
   
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        InputRequestToServer i =new InputRequestToServer(request);
        OutputResponseFromServer o=new OutputResponseFromServer(response);
        i.loverDetails();
        o.love();

        }

}
