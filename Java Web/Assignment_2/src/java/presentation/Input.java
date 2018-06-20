/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import data.CarItem;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Mugiesshan
 */
public class Input {
    
    private HttpServletRequest request;
    
    public Input(HttpServletRequest request) {
        this.request = request;
    }
    
    public String getName() {
        return request.getParameter("name");
    }
    
    public int getQuantity() {
        return Integer.parseInt(request.getParameter("quantity"));
    }
    
    public boolean newItemFormSubmitted() {
        return request.getParameter("name") != null;
    }
    
    public CarItem getNewItem() {
        BigDecimal bg = new BigDecimal(0.00);
        
        switch (this.getName()) {
            case "Rake":
                bg = new BigDecimal(26.50);
                break;
            case "Chainsaw":
                bg = new BigDecimal(102.39);
                break;
        }
        return new CarItem(this.getName(), bg, this.getQuantity());
    }
}
