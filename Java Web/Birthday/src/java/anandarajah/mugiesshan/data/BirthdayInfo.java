/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anandarajah.mugiesshan.data;

import java.util.Date;

/**
 *
 * @author Mugiesshan
 */
public class BirthdayInfo {
    
    private String name;
    private Date date;
    
    public BirthdayInfo (String name, Date date) {
        this.name = name;
        this.date = date;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void getDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
}
