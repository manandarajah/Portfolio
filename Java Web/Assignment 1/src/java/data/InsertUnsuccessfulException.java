/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Mugiesshan
 */
//Exception class that throws an exception when an error occurs
public class InsertUnsuccessfulException extends Exception {
    public InsertUnsuccessfulException(String reason) {
        super(reason);
    }
}
