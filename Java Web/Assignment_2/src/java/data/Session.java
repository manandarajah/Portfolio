/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import presentation.Input;

/**
 *
 * @author Mugiesshan
 */
public class Session {
    
    private HttpSession session;
    
    public Session(HttpServletRequest request) {
        this.session = request.getSession(true);
    }
    
    public void addToCart(CarItem item) {
        Collection<CarItem> carItem = this.getItems();
        boolean inSystem = false;
        
        for (CarItem ci : carItem) {
            if (item.getName().equals(ci.getName())) {
                ci.addToQuantity(item.getQuantity());
                inSystem = true;
                break;
            }
        }
        
        if (carItem.isEmpty() || !inSystem) {
            carItem.add(item);
        }
    }
    
    public Collection<CarItem> getItems() {
        Collection<CarItem> items = (Collection<CarItem>) this.session.getAttribute("caritem");
        
        if (items == null) {
            items = new ArrayList<>();
            this.session.setAttribute("caritem", items);
        }
        return items;
    }
}
