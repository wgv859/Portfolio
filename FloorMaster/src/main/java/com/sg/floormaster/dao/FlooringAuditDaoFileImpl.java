/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author wgv85
 */
public class FlooringAuditDaoFileImpl implements FlooringAuditDao {
    public static final String AUDIT_FILE = "audit.txt";
    
    /**
     * 
     * @param entry
     * @throws FlooringPersistenceException 
     */
    @Override
    public void writeAuditEntry(String entry) throws FlooringPersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
            LocalDateTime timestamp = LocalDateTime.now();
            out.println(timestamp.toString() + " : " + entry);
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new FlooringPersistenceException("Could not persist audit information,", e);
        }
        
    }
    
}
