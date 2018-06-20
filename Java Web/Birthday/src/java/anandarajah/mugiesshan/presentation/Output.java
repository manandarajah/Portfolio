/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anandarajah.mugiesshan.presentation;

import anandarajah.mugiesshan.data.BirthdayInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mugiesshan
 */
public class Output {
    
    static void writeHTML(HttpServletResponse response, String content) throws IOException {
        PrintWriter out = response.getWriter();
        out.append("<html>");
        out.append("<head>");
        out.append("<title>Birthday</title>");
        out.append("</head>");
        out.append("<body>");
        out.append(content);
        out.append("</body>");
        out.append("</html>");
    }
    
    public static void displayBirthdayInfo(HttpServletResponse response, Collection<BirthdayInfo> bi) throws IOException {
        StringBuilder table = new StringBuilder();
        table.append("<form method='post' action='BSubmit'>");
        table.append("<h1>Birthday</h1>");
        table.append("Name: <input type='text' name='name' placeholder='Enter your name'><br>");
        table.append("Date: <input type='date' name='date'><br>");
        table.append("<input type='submit'>");
        table.append("</form>");
        table.append("<table>");
        table.append("<tr><th>Name</th><th>Date</th></tr>");
        
        for (BirthdayInfo b : bi) {
            table.append("<tr><td>");
            table.append(b.getName());
            table.append("</td><td>");
            table.append(b.getDate());
            table.append("</td></tr>");
        }
        table.append("</table>");
        
        if (bi.size() == 1) {
            table.append("<div style='color: green;'>you are the only person with that birthdate</div>");
        }
        writeHTML(response, table.toString());
    }
    
    public static void printErrorMessage(HttpServletResponse response, Exception e) throws IOException {
        writeHTML(response, "<div style='color: red;'>"+e.getMessage()+"</div>");
    }
}
