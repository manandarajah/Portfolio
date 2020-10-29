/*
 * Property of Sheridan College (see Copyright Ownership Policy, ref #24)
 */

package burrell.michael.presentation;

import burrell.michael.data.ShoppingItem;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Michael Burrell
 * @since 20-Nov-2015
 */
public class Input {
    private HttpServletRequest request;

    public Input(HttpServletRequest request) {
        this.request = request;
    }
    
    public boolean isLoggedIn() {
        return request.getSession(false) != null;
    }
    
    public boolean shippingAddressSubmitted() {
        return request.getParameter("shippingaddress") != null;
    }
    
    public String getShippingAddress() {
        return request.getParameter("shippingaddress");
    }
    
    public boolean newItemFormSubmitted() {
        return request.getParameter("description") != null;
    }
    
    public ShoppingItem getNewItem() {
        String desc = request.getParameter("description");
        int q = Integer.parseInt(request.getParameter("quantity"));
        BigDecimal unitPrice = new BigDecimal(
            request.getParameter("price"));
        return new ShoppingItem(desc, unitPrice, q);
    }
    
    public boolean clearCartButtonClicked() {
        return request.getParameter("clear") != null;
    }
    
    public boolean logOutButtonClicked() {
        return request.getParameter("logout") != null;
    }
}
