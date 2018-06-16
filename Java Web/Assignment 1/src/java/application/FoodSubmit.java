/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

import data.Database;
import data.FoodInfo;
import data.InsertUnsuccessfulException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Input;
import presentation.Output;

/**
 *
 * @author Mugiesshan
 */
//Food Table servlet class that handles web requests and responses
public class FoodSubmit extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws data.InsertUnsuccessfulException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, InsertUnsuccessfulException {

        try {
            String food = Input.getFood(request);
            String foodgroup = Input.getFoodGroup(request);
            int servings = Input.getServings(request);
            
            boolean foodInDatabase = Database.updateTable(food, servings);
            Database.insertIntoTable(food, foodgroup, servings, foodInDatabase);
            Collection<FoodInfo> foodinfo = Database.getFoodInfo();
            
            Output.foodInfoTable(response, foodinfo);
        }
        
        catch (SQLException e) {
            Output.printErrorMessage(response, e);
            throw new ServletException(e);
        }
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
        try {
            processRequest(request, response);
        } catch (InsertUnsuccessfulException ex) {
            Logger.getLogger(FoodSubmit.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (InsertUnsuccessfulException ex) {
            Logger.getLogger(FoodSubmit.class.getName()).log(Level.SEVERE, null, ex);
        }
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
