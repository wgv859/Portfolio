/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dao.FlooringDuplicateOrderNumberException;

import com.sg.floormaster.dao.FlooringInvalidDataException;
import com.sg.floormaster.dao.FlooringOrderDao;
import com.sg.floormaster.dao.FlooringOrderNotFoundException;
import com.sg.floormaster.dao.FlooringPersistenceException;
import com.sg.floormaster.dao.FlooringProductDao;
import com.sg.floormaster.dao.FlooringTaxDao;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Tax;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wgv85
 */
public class FlooringServiceLayerImpl implements FlooringServiceLayer {

    FlooringOrderDao orderDao;
    FlooringProductDao productDao;
    FlooringTaxDao taxDao;

    public FlooringServiceLayerImpl(FlooringOrderDao orderDao, FlooringProductDao productDao, FlooringTaxDao taxDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;

    }

    @Override
    public List<Order> getAllOrdersForDate(LocalDate date) throws FlooringPersistenceException, FlooringInvalidDataException {
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new FlooringInvalidDataException("Invalid date detected. Try again.");
        }
        return orderDao.getAllOrdersForDate(date);
    }

    @Override
    public List<Order> getAllOrders() throws FlooringPersistenceException {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Product> getAllProducts() throws FlooringPersistenceException, FlooringInvalidDataException {
        List<Product> productList = productDao.getProductInfo();
        if (productList.isEmpty()) {
            throw new FlooringInvalidDataException("Error. Sorry.");
        }
        return productList;
    }

    @Override
    public List<Tax> getAllTaxInfo() throws FlooringPersistenceException, FlooringInvalidDataException {
        List<Tax> taxList = taxDao.getTaxInfo();
        if (taxList.isEmpty()) {
            throw new FlooringInvalidDataException("Error. Sorry.");
        }
        return taxList;
    }

    @Override
    public Order addNewOrder(Order order) throws FlooringPersistenceException, FlooringInvalidDataException, FlooringDuplicateOrderNumberException {
        checkOrderFields(order);
        Order returnOrder = orderDao.addOrder(order);
        return returnOrder;
    }

    @Override
    public Order removeOrder(Order order) throws FlooringPersistenceException, FlooringInvalidDataException, FlooringOrderNotFoundException {
        Order removedOrder = orderDao.removeOrder(order);
        return removedOrder;
    }

    @Override
    public Order updateOrder(Order order) throws FlooringPersistenceException, FlooringDuplicateOrderNumberException, FlooringInvalidDataException {
        checkUpdatedOrderFields(order);
        Order updatedOrder = orderDao.updateOrder(order);
        return updatedOrder;
    }

    @Override
    public Order getOrderByOrderNumber(int orderNumber) throws FlooringPersistenceException, FlooringOrderNotFoundException {
        Order returnOrder = orderDao.getOrderByOrderNumber(orderNumber);
        return returnOrder;
    }

    @Override
    public void save() throws FlooringPersistenceException {
        orderDao.saveWork();
    }

    @Override
    public int getModeSelection(int number) {
        int modeSelection = orderDao.modeSelection(number);
        return modeSelection;
    }

    @Override
    public int getCurrentMode() {
        int currentMode = orderDao.getCurrentMode();
        return currentMode;
    }

    @Override
    public void uploadOrderFile() throws FlooringPersistenceException {
        orderDao.uploadOrderFile();
    }

    @Override
    public int generateNextAvailableIdNumber() throws FlooringPersistenceException {
        int nextOrderNumber = orderDao.generateNextAvailableIdNumber();
        return nextOrderNumber;
    }

    private void checkOrderFields(Order order) throws FlooringPersistenceException, FlooringDuplicateOrderNumberException, FlooringInvalidDataException {
        try {
            List<Order> orderList = orderDao.getAllOrders();
            Map<Integer, Order> orderMap = new HashMap<>();
            for (Order currentOrder : orderList) {
                orderMap.put(currentOrder.getOrderNumber(), currentOrder);
            }
            if(order.getOrderNumber() < 1) {
                throw new FlooringInvalidDataException("Invalid data.");
            }
            
            if (orderMap.containsKey(order.getOrderNumber())) {
                throw new FlooringDuplicateOrderNumberException("Order Number Already Exists");
            }
            
            if (order.getCustomerName().isEmpty() || order.getCustomerName().equalsIgnoreCase(" ")) {
                throw new FlooringInvalidDataException("Invalid Data.");
            }
            
            if(order.getOrderDate().isBefore(LocalDate.now())) {
                throw new FlooringInvalidDataException("Invalid Data.");
            }
            
            if(order.getProductType().getProductName().isEmpty()) {
                throw new FlooringInvalidDataException("Invalid Data.");
            }
            if(order.getStateTax().getState().isEmpty()) {
                throw new FlooringInvalidDataException("Invalid Data.");
            }
            if(order.getTotalSquareFootage().equals(0)) {
                throw new FlooringInvalidDataException("Invalid Data.");
            }

        } catch (FlooringPersistenceException | FlooringDuplicateOrderNumberException e) {
            throw new FlooringDuplicateOrderNumberException("ID number already exists.");
        }

    }
    
    private void checkUpdatedOrderFields(Order order) throws FlooringPersistenceException, FlooringDuplicateOrderNumberException, FlooringInvalidDataException {
        try {
            List<Order> orderList = orderDao.getAllOrders();
            Map<Integer, Order> orderMap = new HashMap<>();
            for (Order currentOrder : orderList) {
                orderMap.put(currentOrder.getOrderNumber(), currentOrder);
            }
            if(order.getOrderNumber() < 1) {
                throw new FlooringInvalidDataException("Invalid data.");
            }
            
//            if (orderMap.containsKey(order.getOrderNumber())) {
//                throw new FlooringDuplicateOrderNumberException("Order Number Already Exists");
//            }
            
            if (order.getCustomerName().isEmpty() || order.getCustomerName().equalsIgnoreCase(" ")) {
                throw new FlooringInvalidDataException("Invalid Data.");
            }
            if(order.getOrderDate().isBefore(LocalDate.now())) {
                throw new FlooringInvalidDataException("Invalid Data.");
            }
            if(order.getOrderNumber() < 1) {
                throw new FlooringInvalidDataException("Invalid Data.");
            }
            if(order.getProductType().getProductName().isEmpty()) {
                throw new FlooringInvalidDataException("Invalid Data.");
            }
            if(order.getStateTax().getState().isEmpty()) {
                throw new FlooringInvalidDataException("Invalid Data.");
            }
            if(order.getTotalSquareFootage().equals(0)) {
                throw new FlooringInvalidDataException("Invalid Data.");
            }

        } catch (FlooringPersistenceException e) {
            
        }

    }

}
