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
public class InsertUnsuccessfulException extends Exception {
    public InsertUnsuccessfulException(String reason) {
        super(reason);
    }
}
