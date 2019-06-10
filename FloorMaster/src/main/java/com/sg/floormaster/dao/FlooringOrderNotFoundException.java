/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

/**
 *
 * @author wgv85
 */
public class FlooringOrderNotFoundException extends Exception {
    
    public FlooringOrderNotFoundException(String message) {
        super(message);
    }
    
    public FlooringOrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
