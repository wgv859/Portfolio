/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wgv85
 */
public class FlooringOrderDaoStubFileImpl implements FlooringOrderDao {

    private Map<Integer, Order> orders = new HashMap<>();
    private int modeChoice = 1;
    private Order onlyOrder;
    
    private List<Order> orderList = new ArrayList<>();
    
    public FlooringOrderDaoStubFileImpl() {
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax tax = new Tax();
        tax.setState("KY");
        tax.setTaxRate(BigDecimal.valueOf(6));
        
        onlyOrder = new Order();
        onlyOrder.setOrderNumber(1);
        onlyOrder.setOrderDate(LocalDate.parse("2019-12-31"));
        onlyOrder.setCustomerName("Will Varner");
        onlyOrder.setProductType(productOne);
        onlyOrder.setStateTax(tax);
        onlyOrder.setTotalSquareFootage(BigDecimal.valueOf(1000));
        
        orderList.add(onlyOrder);
    }
    
    
    @Override
    public Order addOrder(Order order) throws FlooringPersistenceException, FlooringDuplicateOrderNumberException, FlooringInvalidDataException {
        if (order == onlyOrder) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order removeOrder(Order order) throws FlooringPersistenceException, FlooringInvalidDataException, FlooringOrderNotFoundException {
        if(order == onlyOrder) {
            orderList.remove(order);
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order updateOrder(Order order) throws FlooringPersistenceException, FlooringInvalidDataException {
        if(order == onlyOrder) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrdersForDate(LocalDate date) throws FlooringPersistenceException, FlooringInvalidDataException {
        if(date.equals(onlyOrder.getOrderDate())) {
            return orderList;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() throws FlooringPersistenceException {
        return orderList;
    }

    @Override
    public Order getOrderByOrderNumber(int orderNumber) throws FlooringOrderNotFoundException, FlooringPersistenceException {
        if(orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            throw new FlooringOrderNotFoundException("No order for that order number found. Try again");
        }
    }
    
    @Override
    public void saveWork() throws FlooringPersistenceException {
        //do nothing
    }
    
    @Override
    public int modeSelection(int number) {
        int userChoice = number;
        modeChoice = userChoice;
        return modeChoice;
    }
    
    @Override
    public int getCurrentMode() {
        return modeChoice;
    }
    
    @Override
    public void uploadOrderFile() throws FlooringPersistenceException {
        //do nothing
    }
    
    @Override
    public int generateNextAvailableIdNumber() throws FlooringPersistenceException {
        return onlyOrder.getOrderNumber() + 1;
    }
    
    
    
}
