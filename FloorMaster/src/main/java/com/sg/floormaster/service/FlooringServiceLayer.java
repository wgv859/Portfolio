/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dao.FlooringDuplicateOrderNumberException;
import com.sg.floormaster.dao.FlooringInvalidDataException;
import com.sg.floormaster.dao.FlooringOrderNotFoundException;
import com.sg.floormaster.dao.FlooringPersistenceException;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author wgv85
 */
public interface FlooringServiceLayer {
    
    public List<Order> getAllOrdersForDate(LocalDate date) throws FlooringPersistenceException, FlooringInvalidDataException;
    //
    public List<Order> getAllOrders() throws FlooringPersistenceException;
    //
    public List<Product> getAllProducts() throws FlooringPersistenceException, FlooringInvalidDataException;
    //
    public List<Tax> getAllTaxInfo() throws FlooringPersistenceException, FlooringInvalidDataException;
    //
    public Order addNewOrder(Order order) throws FlooringPersistenceException, FlooringInvalidDataException, FlooringDuplicateOrderNumberException;
    //
    public Order removeOrder(Order order) throws FlooringPersistenceException, FlooringInvalidDataException, FlooringOrderNotFoundException;
    //
    /**
     *
     * @param order
     * @return
     * @throws FlooringPersistenceException
     * @throws FlooringDuplicateOrderNumberException
     * @throws FlooringInvalidDataException
     */
    public Order updateOrder(Order order) throws FlooringPersistenceException, FlooringDuplicateOrderNumberException, FlooringInvalidDataException;
    //
    public Order getOrderByOrderNumber(int orderNumber) throws FlooringPersistenceException, FlooringOrderNotFoundException;
    //
    public void save() throws FlooringPersistenceException;
    //
    public int getModeSelection(int number);
    //
    public int getCurrentMode();
    //
    public void uploadOrderFile() throws FlooringPersistenceException;
    //
    public int generateNextAvailableIdNumber() throws FlooringPersistenceException;
    //
}
