/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anandarajah.mugiesshan.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Mugiesshan
 */
public class Input {
    
    public static String getName(HttpServletRequest request) {
        return request.getParameter("name");
    }
    
    public static Date getDate(HttpServletRequest request) throws ParseException {
        SimpleDateFormat sdm = new SimpleDateFormat("yyy-mm-dd");
        return sdm.parse(request.getParameter("date"));
    }
}
