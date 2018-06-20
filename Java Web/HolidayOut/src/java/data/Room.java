/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;

/**
 *
 * @author Mugiesshan
 */
public class Room implements Serializable {
    
    private int noOfAdults, noOfChildren;
    private String roomType;
    private boolean smoking;

    public Room(int noOfAdults, int noOfChildren, String roomType, boolean smoking) {
        this.noOfAdults = noOfAdults;
        this.noOfChildren = noOfChildren;
        this.roomType = roomType;
        this.smoking = smoking;
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }
}
