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
public interface FlooringAuditDao {
    public void writeAuditEntry(String entry) throws FlooringPersistenceException;
}
