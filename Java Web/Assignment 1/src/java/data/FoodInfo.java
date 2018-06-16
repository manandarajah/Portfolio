/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Date;

/**
 *
 * @author Mugiesshan
 */
//FoodInfo object with information on a certain food
public class FoodInfo {
    private Date date;
    private String food, foodgroup;
    private int servings;
    
    public FoodInfo (Date date, String food, String foodgroup, int servings) {
        this.date = date;
        this.food = food;
        this.foodgroup = foodgroup;
        this.servings = servings;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setFood(String food) {
        this.food = food;
    }
    
    public String getFood() {
        return this.food;
    }
    
    public void setFoodGroup(String foodgroup) {
        this.foodgroup = foodgroup;
    }
    
    public String getFoodGroup() {
        return this.foodgroup;
    }
    
    public void setServings(int servings) {
        this.servings = servings;
    }
    
    public int getServings() {
        return this.servings;
    }
}
