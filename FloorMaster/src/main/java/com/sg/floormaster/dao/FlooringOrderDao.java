/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author wgv85
 */
public interface FlooringOrderDao {
    
    /**
     * 
     * @param order Order to be added to file
     * @return order if it exists, null otherwise
     * @throws FlooringPersistenceException
     * @throws FlooringDuplicateOrderNumberException
     * @throws FlooringInvalidDataException 
     */
    Order addOrder(Order order) throws FlooringPersistenceException, FlooringDuplicateOrderNumberException, FlooringInvalidDataException;
    
    /**
     * 
     * @param order
     * @return Order to be removed
     * @throws FlooringPersistenceException
     * @throws FlooringInvalidDataException 
     * @throws com.sg.floormaster.dao.FlooringOrderNotFoundException 
     */
    Order removeOrder(Order order) throws FlooringPersistenceException, FlooringInvalidDataException, FlooringOrderNotFoundException;
    
    /**
     * 
     * @param order
     * @throws FlooringPersistenceException
     * @throws FlooringInvalidDataException 
     */
    Order updateOrder(Order order) throws FlooringPersistenceException, FlooringInvalidDataException;
    
    /**
     * 
     * @param date Date that we want all the orders for
     * @return A list of Orders associated with the given date.
     * @throws FlooringPersistenceException
     * @throws FlooringInvalidDataException 
     */
    List<Order> getAllOrdersForDate(LocalDate date) throws FlooringPersistenceException, FlooringInvalidDataException;
    
    
    List<Order> getAllOrders() throws FlooringPersistenceException;
    
    Order getOrderByOrderNumber(int orderNumber) throws FlooringOrderNotFoundException, FlooringPersistenceException;
    
    void saveWork() throws FlooringPersistenceException;
    
    int modeSelection(int number);
    
    int getCurrentMode();
    
    void uploadOrderFile() throws FlooringPersistenceException;
    
    public int generateNextAvailableIdNumber() throws FlooringPersistenceException;
}
