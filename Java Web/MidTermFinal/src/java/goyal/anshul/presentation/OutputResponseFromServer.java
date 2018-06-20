/*
ANSHUL
MID TERM JAVA EXAM
17TH JULY

*/
package goyal.anshul.presentation;


import goyal.anshul.data.Database;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class OutputResponseFromServer {
    private HttpServletResponse response;

    public OutputResponseFromServer(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    public void love() throws IOException{

         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         
            out.println("<!DOCTYPE html>");
                out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
              out.println("<body>");
                out.println("<h1> "+ goyal.anshul.data.Database.outputlover.getLover()+ "</h1>");
                        out.println("</body>");
            
                                    out.println("</html>");
        }
}
}