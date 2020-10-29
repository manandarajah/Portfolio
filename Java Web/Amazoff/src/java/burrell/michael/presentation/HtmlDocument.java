/*
 * Property of Sheridan College (see Copyright Ownership Policy, ref #24)
 */

package burrell.michael.presentation;

import burrell.michael.data.ShoppingItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collection;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Burrell
 * @since 20-Nov-2015
 */
public class HtmlDocument implements AutoCloseable {
    private PrintWriter out;
    
    public HtmlDocument(HttpServletResponse response) throws IOException {
        this.out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
    }
    
    @Override
    public void close() {
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    
    public void printShippingAddressForm() {
        out.println("<form method='GET' action='Amazoff'>");
        out.println("Shipping address: " +
                "<input type='text' name='shippingaddress'>");
        out.println("<input type='submit' value='Start shopping'>");
        out.println("</form>");
    }
    
    public void printShippingAddress(String address) {
        out.println("<p>Your address is " + address + "</p>");
    }
    
    public void printShoppingCart(Collection<ShoppingItem> cart,
            BigDecimal total) {
        out.println("<table>");
        for (ShoppingItem item : cart) {
            out.println("<tr>");
            out.println("<td>" + item.getDescription() + "</td>");
            out.println("<td>" + item.getQuantity() +
                    " &times; $" + item.getUnitPrice() + "</td>");
            BigDecimal totalPrice =
                    item.getUnitPrice().multiply(
                            new BigDecimal(item.getQuantity()));
            out.println("<td>$" + totalPrice + "</td>");
            out.println("</tr>");
        }
        out.println("<tr><td colspan='2'>Total</td>");
        out.println("<td>$" + total + "</td></tr>");
        out.println("</table>");
    }
    
    public void printNewItemForm() {
        out.println("<form method='GET' action='Amazoff'>");
        out.println("<input type='text' name='description'>");
        out.println("<input type='number' name='quantity'>");
        out.println("<input type='number' step='0.01' name='price'>");
        out.println("<input type='submit' value='Add to cart'>");
        out.println("</form>");
    }
    
    public void printClearCartForm() {
        out.println("<form method='GET' action='Amazoff'>");
        out.println("<input type='submit' value='Clear cart' " +
                "name='clear'>");
        out.println("</form>");
    }
    
    public void printLogOutForm() {
        out.println("<form method='GET' action='Amazoff'>");
        out.println("<input type='submit' value='Log out' " +
                "name='logout'>");
        out.println("</form>");
    }
}
