/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Mugiesshan
 */
public class Reservation implements Serializable {
    
    private String name, phone;
    private Date checkIn, checkOut;
    private BigDecimal creditCard;
    private Collection<Room> rooms;

    public Reservation(String name, String phone, Date checkIn, Date checkOut, BigDecimal creditCard, Collection<Room> rooms) {
        this.name = name;
        this.phone = phone;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.creditCard = creditCard;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public BigDecimal getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(BigDecimal creditCard) {
        this.creditCard = creditCard;
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }
}
