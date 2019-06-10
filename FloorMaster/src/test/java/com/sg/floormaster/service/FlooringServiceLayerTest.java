/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dao.FlooringAuditDao;
import com.sg.floormaster.dao.FlooringAuditDaoStubImpl;
import com.sg.floormaster.dao.FlooringDuplicateOrderNumberException;
import com.sg.floormaster.dao.FlooringInvalidDataException;
import com.sg.floormaster.dao.FlooringOrderDao;
import com.sg.floormaster.dao.FlooringOrderDaoStubFileImpl;
import com.sg.floormaster.dao.FlooringOrderNotFoundException;
import com.sg.floormaster.dao.FlooringPersistenceException;
import com.sg.floormaster.dao.FlooringProductDao;
import com.sg.floormaster.dao.FlooringProductDaoFileImpl;
import com.sg.floormaster.dao.FlooringTaxDao;
import com.sg.floormaster.dao.FlooringTaxDaoFileImpl;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author wgv85
 */
public class FlooringServiceLayerTest {
    
    private FlooringServiceLayer service;
    
    public FlooringServiceLayerTest() {
        FlooringOrderDao orderDao = new FlooringOrderDaoStubFileImpl();
        FlooringProductDao productDao = new FlooringProductDaoFileImpl();
        FlooringTaxDao taxDao = new FlooringTaxDaoFileImpl();
        FlooringAuditDao auditDao = new FlooringAuditDaoStubImpl();
        
        service = new FlooringServiceLayerImpl(orderDao, productDao, taxDao);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllOrdersForDate method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetAllOrdersForDate() throws Exception {
        try {
            List<Order> orderList = new ArrayList<>();
            orderList = service.getAllOrdersForDate(LocalDate.parse("2019-12-31"));
        } catch (FlooringInvalidDataException e) {
            return;
        }
    }
    
    @Test
    public void testGetAllOrdersForDatePastDate() throws Exception {
        try {
            List<Order> orderList = new ArrayList<>();
            orderList = service.getAllOrdersForDate(LocalDate.parse("2018-12-31"));
            fail("Expected FlooringInvalidDataException was not thrown.");
        } catch (FlooringInvalidDataException e) {
            return;
        }
    }
    
    @Test
    public void testGetAllOrdersForDateNullDate() throws Exception {
        try {
            List<Order> orderList = new ArrayList<>();
            orderList = service.getAllOrdersForDate(null);
            fail("Expected FlooringInvalidDataException was not thrown.");
        } catch (FlooringInvalidDataException e) {
            return;
        }
    }

    /**
     * Test of getAllOrders method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        assertEquals(1, service.getAllOrders().size());
    }

    /**
     * Test of getAllProducts method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        assertEquals(2, service.getAllProducts().size());
    }

    /**
     * Test of getAllTaxInfo method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetAllTaxInfo() throws Exception {
        assertEquals(2, service.getAllTaxInfo().size());
    }

    /**
     * Test of addNewOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testAddNewOrder() throws Exception {
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax tax = new Tax();
        tax.setState("KY");
        tax.setTaxRate(BigDecimal.valueOf(6));
        
        Order newOrder = new Order();
        newOrder.setOrderNumber(2);
        newOrder.setCustomerName("Abby Varner");
        newOrder.setOrderDate(LocalDate.parse("2019-12-31"));
        newOrder.setProductType(productOne);
        newOrder.setStateTax(tax);
        newOrder.setTotalSquareFootage(BigDecimal.valueOf(2000));
        
        service.addNewOrder(newOrder);
    }
    
    @Test
    public void testAddNewOrderDuplicateId() throws Exception {
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax tax = new Tax();
        tax.setState("KY");
        tax.setTaxRate(BigDecimal.valueOf(6));
        
        Order newOrder = new Order();
        newOrder.setOrderNumber(1);
        newOrder.setCustomerName("Abby Varner");
        newOrder.setOrderDate(LocalDate.parse("2019-12-31"));
        newOrder.setProductType(productOne);
        newOrder.setStateTax(tax);
        newOrder.setTotalSquareFootage(BigDecimal.valueOf(2000));
        
        try{
            service.addNewOrder(newOrder);
            fail("Expected FlooringDuplicateOrderNumberException was not thrown.");
        } catch(FlooringDuplicateOrderNumberException e) {
            return;
        }
    }
    
    @Test
    public void testAddNewOrderInvalidData() throws Exception {
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax tax = new Tax();
        tax.setState("KY");
        tax.setTaxRate(BigDecimal.valueOf(6));
        
        Order newOrder = new Order();
        newOrder.setOrderNumber(2);
        newOrder.setCustomerName(" ");
        newOrder.setOrderDate(LocalDate.parse("2019-12-31"));
        newOrder.setProductType(productOne);
        newOrder.setStateTax(tax);
        newOrder.setTotalSquareFootage(BigDecimal.valueOf(2000));
        
        try {
            service.addNewOrder(newOrder);
            fail("Expected FlooringInvalidDataException was not thrown");
        }catch (FlooringInvalidDataException e) {
            return;
        }
    }

    /**
     * Test of removeOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        Order testOrder = service.getOrderByOrderNumber(1);
        service.removeOrder(testOrder);
        
        assertEquals(0, service.getAllOrders().size());
    }

    /**
     * Test of updateOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testUpdateOrder() throws Exception {
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax tax = new Tax();
        tax.setState("KY");
        tax.setTaxRate(BigDecimal.valueOf(6));
        
        Order newOrder = new Order();
        newOrder.setOrderNumber(2);
        newOrder.setCustomerName("Abby Varner");
        newOrder.setOrderDate(LocalDate.parse("2019-12-31"));
        newOrder.setProductType(productOne);
        newOrder.setStateTax(tax);
        newOrder.setTotalSquareFootage(BigDecimal.valueOf(2000));
        
        service.addNewOrder(newOrder);
        
        Product productTwo = new Product();
        productTwo.setCostPerSquareFoot(BigDecimal.valueOf(3.0));
        productTwo.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.25));
        productTwo.setProductName("vinyl");
        
        Tax taxTwo = new Tax();
        taxTwo.setState("TN");
        taxTwo.setTaxRate(BigDecimal.valueOf(5));
        
        Order orderTwo = new Order();
        orderTwo.setOrderNumber(2);
        orderTwo.setCustomerName("Ducky Varner");
        orderTwo.setOrderDate(LocalDate.parse("2019-11-03"));
        orderTwo.setProductType(productTwo);
        orderTwo.setStateTax(taxTwo);
        orderTwo.setTotalSquareFootage(BigDecimal.valueOf(200));
        
        newOrder = orderTwo;
        
        service.updateOrder(newOrder);
        
        
        assertEquals(newOrder, orderTwo);
    }

    /**
     * Test of getOrderByOrderNumber method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetOrderByOrderNumber() throws Exception {
        try{
           Order testOrder = service.getOrderByOrderNumber(1); 
        } catch(FlooringOrderNotFoundException e) {
            return;
        }
    }
    
    @Test
    public void testGetOrderByOrderNumberNoOrderForNumber () throws Exception {
        try {
            Order testOrder = service.getOrderByOrderNumber(2);
            fail("expected FlooringOrderNotFoundException not thrown.");
        } catch(FlooringOrderNotFoundException e) {
            return;
        }
    }

    /**
     * Test of save method, of class FlooringServiceLayer.
     */
    @Test
    public void testSave() throws Exception {
        try{
            service.save();
        }catch(FlooringPersistenceException e) {
            return;
        }
    }

    /**
     * Test of getModeSelection method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetModeSelectionAndGetCurrentMode() {
        int userChoice = 2;
        service.getModeSelection(userChoice);
        int currentMode = service.getCurrentMode();
        assertEquals(userChoice, currentMode);
    }
    
    @Test
    public void testUploadOrderFile() throws Exception {
        try {
            service.uploadOrderFile();
        } catch(FlooringPersistenceException e) {
            return;
        }
    }

    /**
     * Test of generateNextAvailableIdNumber method, of class FlooringServiceLayer.
     */
    @Test
    public void testGenerateNextAvailableIdNumber() throws Exception {
        try {
            service.generateNextAvailableIdNumber();
        } catch(FlooringPersistenceException e) {
            return;
        }
    }

}
