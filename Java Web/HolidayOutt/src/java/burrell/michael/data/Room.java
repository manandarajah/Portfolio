/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burrell.michael.data;

import java.io.Serializable;

/**
 *
 * @author burrelmi
 */
public class Room implements Serializable {
    private int numberOfAdults, numberOfChildren;
    private String roomType;
    private boolean smoking;

    public Room() {
    }

    public Room(int numberOfAdults, int numberOfChildren, String roomType, boolean smoking) {
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.roomType = roomType;
        this.smoking = smoking;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
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
