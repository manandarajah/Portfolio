/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import data.LoginInfo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mugiesshan
 */
public class Output {
    static void writeHTML(HttpServletResponse response, String content) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel='stylesheet' type='text/css' href='login.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println(content);
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    
    public static void showLoginInfo(HttpServletResponse response, LoginInfo login) throws IOException {
        writeHTML(response, "<ul>"
                                +"<li>Username: "+login.getUsername()+"</li>"
                                +"<li>Administrator: "+login.getAdmin()+"</li>"
                                +"<li>Real name: "+login.getName()+"</li>"
                            +"</ul>");
    }
    
    public static void loginUnSuccessfulMessage(HttpServletResponse response) throws IOException {
        writeHTML(response, "<p>Incorrect username or password</p>");
    }
    
    public static void printErrorMessage(HttpServletResponse response, Exception e) throws IOException {
        writeHTML(response, "<p>There was an error! " + e.getMessage()+"</p>");
    }
    
    public static void accountCreatedSuccessfully (HttpServletResponse response) throws IOException {
        writeHTML(response, "<p>Your account has been created succesfully</p>");
    }
}
