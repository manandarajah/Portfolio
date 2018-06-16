/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import data.CarItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mugiesshan
 */
public class HtmlDocument implements AutoCloseable {
    
    private PrintWriter out;
    
    public HtmlDocument(HttpServletResponse response) throws IOException {
        this.out = response.getWriter();
        this.out.println("<html>");
        this.out.println("<head>");
        this.out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        this.out.println("</head>");
        this.out.println("<body>");
    }
    
    @Override
    public void close() {
        this.out.println("</body>");
        this.out.println("</html>");
        this.out.close();
    }
    
    public void output(Collection<CarItem> items) {
        this.out.println("<table><tr style='font-weight: bold;'><td>Item</td><td>Quantity X Balance</td><td>Total</td></tr>");
        double total = 0;
        NumberFormat formatter = new DecimalFormat("#0.00");
        
        for (CarItem i : items) {
            this.out.println("<tr><td>");
            this.out.println(i.getName());
            this.out.println("</td><td>");
            this.out.println(i.getQuantity());
            this.out.println("</td><td>X</td><td>$");
            this.out.println(formatter.format(i.getUnitPrice()));
            this.out.println("</td><td>$");
            BigDecimal totalPrice = i.getUnitPrice().multiply(new BigDecimal(i.getQuantity()));
            this.out.println(formatter.format(totalPrice));
            this.out.println("</td></tr>");
            total += totalPrice.doubleValue();
        }
        this.out.println("</table>");
        this.out.println("<p>Total: $");
        this.out.println(formatter.format(total));
        this.out.println("</p>");
        this.out.println("<a href='rake.html'>Rake</a><br>");
        this.out.println("<a href='chainsaw.html'>Chainsaw</a>");
    }
}
