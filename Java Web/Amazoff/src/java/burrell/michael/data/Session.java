/*
 * Property of Sheridan College (see Copyright Ownership Policy, ref #24)
 */

package burrell.michael.data;

import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael Burrell
 * @since 20-Nov-2015
 */
public class Session {
    private HttpSession session;
    
    public Session(HttpServletRequest request) {
        this.session = request.getSession(true);
    }
    
    public void destroy() {
        session.invalidate();
    }
    
    public void setShippingAddress(String address) {
        session.setAttribute("shippingAddress", address);
    }
    
    public String getShippingAddress() {
        return (String)session.getAttribute("shippingAddress");
    }
    
    public void addShoppingItem(ShoppingItem item) {
        Collection<ShoppingItem> cart = getShoppingCart();
        cart.add(item);
    }
    
    public Collection<ShoppingItem> getShoppingCart() {
        Collection<ShoppingItem> cart = (Collection<ShoppingItem>)
                session.getAttribute("shoppingCart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("shoppingCart", cart);
        }
        return cart;
    }
    
    public void clearShoppingCart() {
        session.setAttribute("shoppingCart",
                new ArrayList<ShoppingItem>());
    }
}
