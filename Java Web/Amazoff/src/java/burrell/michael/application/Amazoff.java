/*
 * Property of Sheridan College (see Copyright Ownership Policy, ref #24)
 */

package burrell.michael.application;

import burrell.michael.data.Session;
import burrell.michael.data.ShoppingItem;
import burrell.michael.presentation.HtmlDocument;
import burrell.michael.presentation.Input;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Burrell
 * @since 20-Nov-2015
 */
public class Amazoff extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try (HtmlDocument html = new HtmlDocument(response)) {
            Input inp = new Input(request);
            if (inp.logOutButtonClicked()) {
                Session session = new Session(request);
                session.destroy();
            }
            if (!inp.isLoggedIn() && !inp.shippingAddressSubmitted())
                html.printShippingAddressForm();
            else {
                Session session = new Session(request);
                if (inp.shippingAddressSubmitted())
                    session.setShippingAddress(inp.getShippingAddress());
                else if (inp.newItemFormSubmitted()) {
                    ShoppingItem newItem = inp.getNewItem();
                    session.addShoppingItem(newItem);
                } else if (inp.clearCartButtonClicked())
                    session.clearShoppingCart();
                html.printShippingAddress(session.getShippingAddress());
                
                Collection<ShoppingItem> cart = session.getShoppingCart();
                html.printShoppingCart(cart, calculateTotal(cart));
                
                html.printNewItemForm();
                
                html.printClearCartForm();
                
                html.printLogOutForm();
            }
        }
    }
    
    private BigDecimal calculateTotal(Collection<ShoppingItem> cart) {
        BigDecimal total = new BigDecimal("0.00");
        for (ShoppingItem item : cart) {
            BigDecimal itemPrice = item.getUnitPrice().multiply(
                    new BigDecimal(item.getQuantity()));
            total = total.add(itemPrice);
        }
        return total;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
