/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Mugiesshan
 */
//Input class that intakes input information from the web page
public class Input {
    
    public static String getFood(HttpServletRequest request) {
        return request.getParameter("food");
    }
    
    public static String getFoodGroup(HttpServletRequest request) {
        return request.getParameter("foodgroup");
    }
    
    public static int getServings(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("servings"));
    }
}
