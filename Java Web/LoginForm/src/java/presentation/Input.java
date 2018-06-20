/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Mugiesshan
 */
public class Input {
    
    public static String getName(HttpServletRequest request) {
        return request.getParameter("name");
    }
    
    public static String getUsername(HttpServletRequest request) {
        return request.getParameter("username");
    }
    
    public static String getPassword(HttpServletRequest request) {
        return request.getParameter("password");
    }
    
    public static String getConfirmationPassword (HttpServletRequest request) {
        return request.getParameter("confirmpassword");
    }
}
