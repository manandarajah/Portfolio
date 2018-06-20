/*
ANSHUL
MID TERM JAVA EXAM
17TH JULY

*/

package goyal.anshul.presentation;

import goyal.anshul.data.Boring;
import javax.servlet.http.HttpServletRequest;

 public class InputRequestToServer {
    private HttpServletRequest request;

    public InputRequestToServer(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public void loverDetails(){
        String love = request.getParameter("love");
        String lover = request.getParameter("lover");
        

}
}
