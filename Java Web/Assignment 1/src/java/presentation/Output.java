/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import data.FoodInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mugiesshan
 */
//Output class that displays the results in a web page
public class Output {
    
    private static double fav = 0, gp = 0, mla = 0, mea = 0, servings = 0;
    
    //Java generated HTML code to design a new web page
    static void writeHTML (HttpServletResponse response, String content) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel='stylesheet' type='text/css' href='style.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Food Table</h1>");
        out.println(content);
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    
    //HTML code for food table content
    public static void foodInfoTable(HttpServletResponse response, Collection<FoodInfo> foodinfo) throws IOException {
        StringBuilder table = new StringBuilder();
        table.append("<table>");
        table.append("<tr><th>Percentage</th><th>Food Group</th>");
        
        for (FoodInfo f : foodinfo) {
            foodGroupBreakdown(f.getFoodGroup(), f.getServings());
        }
        table.append("<tr style='color: #00FF00;'><td>");
        table.append(((int) ((fav/servings)*100)));
        table.append("%</td><td>Fruits & Vegetables</td></tr>");
        table.append("<tr style='color: #CC6600;'><td>");
        table.append(((int) ((gp/servings)*100)));
        table.append("%</td><td>Grains & Products</td></tr>");
        table.append("<tr style='color: yellow;'><td>");
        table.append(((int) ((mla/servings)*100)));
        table.append("%</td><td>Milk & Alternatives</td></tr>");
        table.append("<tr style='color: red;'><td>");
        table.append(((int) ((mea/servings)*100)));
        table.append("%</td><td>Meat & Alternatives</td></tr>");
        table.append("</table>");
        table.append("<form method='POST' action='FoodSubmit'>");
        table.append("Food Eaten: <input type='text' name='food' required><br>");
        table.append("Food Group: <select name='foodgroup' required>");
        table.append("<option selected value=\"Fruits & Vegetables\">Fruits & Vegetables</option>");
        table.append("<option value=\"Grains & Products\">Grains & Products</option>");
        table.append("<option value=\"Milk & Alternatives\">Milk & Alternatives</option>");
        table.append("<option value=\"Meat & Alternatives\">Meat & Alternatives</option>");
        table.append("</select><br>");
        table.append("Servings: <input type='number' name='servings' required><br>");
        table.append("<input type='submit'>");
        table.append("</form>");
        table.append("<div>");
        table.append("<table>");
        table.append("<tr><th>Date</th><th>Description</th><th>Food Group</th><th>Servings</th></tr>");
        
        for (FoodInfo f : foodinfo) {
            String tr = setTRStyle(f.getFoodGroup());
            table.append(tr);
            table.append(f.getDate());
            table.append("</td>");
            table.append("<td>");
            table.append(f.getFood());
            table.append("</td>");
            table.append("<td>");
            table.append(f.getFoodGroup());
            table.append("</td>");
            table.append("<td>");
            table.append(f.getServings());
            table.append("</td></tr>");
        }
        table.append("</table>");
        table.append("</div>");
        reset();
        writeHTML(response, table.toString());
    }
    
    //Calculates food group percentage
    static void foodGroupBreakdown(String foodgroup, int servings) {
        Output.servings += servings;
        
        switch (foodgroup) {
            case "Fruits & Vegetables":
                fav += servings;
                break;
            case "Grains & Products":
                gp += servings;
                break;
            case "Milk & Alternatives":
                mla += servings;
                break;
            case "Meat & Alternatives":
                mea += servings;
                break;
        }
    }
    
    //sets the style for the TR tag depending on the food group
    static String setTRStyle(String foodgroup) {
        String tr = "";
        
        switch(foodgroup) {
            case "Fruits & Vegetables":
                tr = "<tr style='color: #00FF00;'><td>";
                break;
            case "Grains & Products":
                tr = "<tr style='color: #CC6600;'><td>";
                break;
            case "Milk & Alternatives":
                tr = "<tr style='color: yellow;'><td>";
                break;
            case "Meat & Alternatives":
                tr = "<tr style='color: red;'><td>";
                break;
        }
        return tr;
    }
    
    //Resets variables
    static void reset() {
        fav = 0;
        gp = 0;
        mla = 0;
        mea = 0;
        servings = 0;
    }
    
    //Prints exception message when error occurs in HTML
    public static void printErrorMessage(HttpServletResponse response, Exception e) throws IOException {
        writeHTML(response, "<p style='color: #FF99FF;'>Error: "+e.getMessage()+"</p>");
    }
}
