/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author wgv85
 */
public class FlooringOrderDaoTrainingFileImpl implements FlooringOrderDao {
    
    private Map<Integer, Order> orders = new HashMap<>();
    private final String DELIMITER = "::";
    private final String ORDER_FILE = "orderFile.txt";

    @Override
    public Order addOrder(Order order) throws FlooringPersistenceException, FlooringDuplicateOrderNumberException, FlooringInvalidDataException {
        if(orders.isEmpty()) {
            loadOrderFile();
        }
        
        if (orders.containsKey(order.getOrderNumber())) {
            throw new FlooringDuplicateOrderNumberException("Order number already exists. Try again");
        } else {
            Order returnOrder = orders.put(order.getOrderNumber(), order);
            
            return returnOrder;
        }
    }

    @Override
    public List<Order> getAllOrdersForDate(LocalDate date) throws FlooringPersistenceException, FlooringInvalidDataException {
        if(orders.isEmpty()) {
            loadOrderFile();
        }
        List<Order> returnList = new ArrayList<>();
        List<Order> orderList = getAllOrders();
        for (Order currentOrder : orderList) {
            if(currentOrder.getOrderDate().equals(date)) {
                returnList.add(currentOrder);
            }
        }
        return returnList;
    }

    @Override
    public List<Order> getAllOrders() throws FlooringPersistenceException {
        if(orders.isEmpty()) {
            loadOrderFile();
        }
        return new ArrayList<>(orders.values());
    }
    

    @Override
    public Order removeOrder(Order order) throws FlooringPersistenceException, FlooringInvalidDataException, FlooringOrderNotFoundException {
        Order removedOrder = order;
        if(orders.isEmpty()) {
            loadOrderFile();
        }
        orders.remove(removedOrder.getOrderNumber());
        
        return removedOrder;
    }

    @Override
    public Order updateOrder(Order order) throws FlooringPersistenceException, FlooringInvalidDataException {
        Order returnOrder = order;
        if(orders.isEmpty()) {
            loadOrderFile();
        }
        orders.put(order.getOrderNumber(), order);
        
        return returnOrder;
    }

    @Override
    public Order getOrderByOrderNumber(int orderNumber) throws FlooringOrderNotFoundException, FlooringPersistenceException {
        if(orders.isEmpty()) {
            loadOrderFile();
        }
        return orders.get(orderNumber);
    }
    
    @Override
    public void saveWork() throws FlooringPersistenceException {
        writeOrderFile();
    }
    
    private void loadOrderFile() throws FlooringPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Could not load file data. Sorry.", e);
        }
        String currentLine;
        String[] tokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            tokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product();
            currentProduct.setProductName(tokens[5]);
            currentProduct.setCostPerSquareFoot(new BigDecimal(tokens[7]));
            currentProduct.setLaborCostPerSquareFoot(new BigDecimal(tokens[8]));

            Tax currentTax = new Tax();
            currentTax.setState(tokens[3]);
            currentTax.setTaxRate(new BigDecimal(tokens[4]));

            Order currentOrder = new Order();

            currentOrder.setOrderNumber(Integer.parseInt(tokens[0]));
            currentOrder.setOrderDate(LocalDate.parse(tokens[1]));
            currentOrder.setCustomerName(tokens[2]);
            currentOrder.setTotalSquareFootage(new BigDecimal(tokens[6]));
            currentOrder.setStateTax(currentTax);
            currentOrder.setProductType(currentProduct);
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        scanner.close();
    }

    private void writeOrderFile() throws FlooringPersistenceException {
        // Do nothing
    }

    @Override
    public int modeSelection(int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCurrentMode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void uploadOrderFile() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int generateNextAvailableIdNumber() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
