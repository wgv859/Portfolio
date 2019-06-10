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
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author wgv85
 */
public class FlooringOrderDaoTest {
    
    private FlooringOrderDao dao = new FlooringOrderDaoProductionFileImpl();
    
    public FlooringOrderDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<Order> orderList = dao.getAllOrders();
        for(Order order : orderList) {
            dao.removeOrder(order);
        }
        dao.saveWork();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testAddGetOrder() throws Exception {
        LocalDate date = LocalDate.parse("2019-12-31");
        
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax tax = new Tax();
        tax.setState("KY");
        tax.setTaxRate(BigDecimal.valueOf(6));
        
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Will Varner");
        order.setOrderDate(date);
        order.setProductType(productOne);
        order.setStateTax(tax);
        order.setTotalSquareFootage(BigDecimal.valueOf(1000));
        
        dao.addOrder(order);
        
        Order fromDao = dao.getOrderByOrderNumber(1);
        
        assertEquals(order, fromDao);
    }

    /**
     * Test of removeOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        LocalDate dateOne = LocalDate.parse("2019-12-31");
        LocalDate dateTwo = LocalDate.parse("2019-04-20");
        
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax taxOne = new Tax();
        taxOne.setState("KY");
        taxOne.setTaxRate(BigDecimal.valueOf(6));
        
        Order orderOne = new Order();
        orderOne.setOrderNumber(1);
        orderOne.setCustomerName("Will Varner");
        orderOne.setOrderDate(dateOne);
        orderOne.setProductType(productOne);
        orderOne.setStateTax(taxOne);
        orderOne.setTotalSquareFootage(BigDecimal.valueOf(1000));
        
        dao.addOrder(orderOne);
        
        Product productTwo = new Product();
        productTwo.setCostPerSquareFoot(BigDecimal.valueOf(3.0));
        productTwo.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.25));
        productTwo.setProductName("vinyl");
        
        Tax taxTwo = new Tax();
        taxTwo.setState("TN");
        taxTwo.setTaxRate(BigDecimal.valueOf(5));
        
        Order orderTwo = new Order();
        orderTwo.setOrderNumber(2);
        orderTwo.setCustomerName("Abby Varner");
        orderTwo.setOrderDate(dateTwo);
        orderTwo.setProductType(productTwo);
        orderTwo.setStateTax(taxTwo);
        orderTwo.setTotalSquareFootage(BigDecimal.valueOf(2000));
        
        dao.addOrder(orderTwo);
        
        dao.removeOrder(orderOne);
        assertEquals(1, dao.getAllOrders().size());
        assertNull(dao.getOrderByOrderNumber(1));
        dao.removeOrder(orderTwo);
        assertEquals(0,dao.getAllOrders().size());
        assertNull(dao.getOrderByOrderNumber(2));
    }

    /**
     * Test of updateOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testUpdateOrder() throws Exception {
        LocalDate dateOne = LocalDate.parse("2019-12-31");
        LocalDate dateTwo = LocalDate.parse("2019-04-20");
        
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax taxOne = new Tax();
        taxOne.setState("KY");
        taxOne.setTaxRate(BigDecimal.valueOf(6));
        
        Order orderOne = new Order();
        orderOne.setOrderNumber(1);
        orderOne.setCustomerName("Will Varner");
        orderOne.setOrderDate(dateOne);
        orderOne.setProductType(productOne);
        orderOne.setStateTax(taxOne);
        orderOne.setTotalSquareFootage(BigDecimal.valueOf(1000));
        
        dao.addOrder(orderOne);
        
        Product productTwo = new Product();
        productTwo.setCostPerSquareFoot(BigDecimal.valueOf(3.0));
        productTwo.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.25));
        productTwo.setProductName("vinyl");
        
        Tax taxTwo = new Tax();
        taxTwo.setState("TN");
        taxTwo.setTaxRate(BigDecimal.valueOf(5));
        
        Order orderTwo = new Order();
        orderTwo.setOrderNumber(2);
        orderTwo.setCustomerName("Abby Varner");
        orderTwo.setOrderDate(dateTwo);
        orderTwo.setProductType(productTwo);
        orderTwo.setStateTax(taxTwo);
        orderTwo.setTotalSquareFootage(BigDecimal.valueOf(2000));
        
        dao.addOrder(orderTwo);
        
        orderOne = orderTwo;
        
        dao.updateOrder(orderOne);
        Order testOrder = dao.getOrderByOrderNumber(orderOne.getOrderNumber());
        
        assertEquals(testOrder, orderTwo);
    }

    /**
     * Test of getAllOrdersForDate method, of class FlooringOrderDao.
     */
    @Test
    public void testGetAllOrdersForDate() throws Exception {
        LocalDate dateOne = LocalDate.parse("2019-12-31");
        LocalDate dateTwo = LocalDate.parse("2019-04-20");
        
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax taxOne = new Tax();
        taxOne.setState("KY");
        taxOne.setTaxRate(BigDecimal.valueOf(6));
        
        Order orderOne = new Order();
        orderOne.setOrderNumber(1);
        orderOne.setCustomerName("Will Varner");
        orderOne.setOrderDate(dateOne);
        orderOne.setProductType(productOne);
        orderOne.setStateTax(taxOne);
        orderOne.setTotalSquareFootage(BigDecimal.valueOf(1000));
        
        dao.addOrder(orderOne);
        
        Product productTwo = new Product();
        productTwo.setCostPerSquareFoot(BigDecimal.valueOf(3.0));
        productTwo.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.25));
        productTwo.setProductName("vinyl");
        
        Tax taxTwo = new Tax();
        taxTwo.setState("TN");
        taxTwo.setTaxRate(BigDecimal.valueOf(5));
        
        Order orderTwo = new Order();
        orderTwo.setOrderNumber(2);
        orderTwo.setCustomerName("Abby Varner");
        orderTwo.setOrderDate(dateTwo);
        orderTwo.setProductType(productTwo);
        orderTwo.setStateTax(taxTwo);
        orderTwo.setTotalSquareFootage(BigDecimal.valueOf(2000));
        
        dao.addOrder(orderTwo);
        
        List<Order> ordersForDate = dao.getAllOrdersForDate(dateOne);
        assertEquals(1, ordersForDate.size());
    }

    /**
     * Test of getAllOrders method, of class FlooringOrderDao.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        LocalDate dateOne = LocalDate.parse("2019-12-31");
        LocalDate dateTwo = LocalDate.parse("2019-04-20");
        
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax taxOne = new Tax();
        taxOne.setState("KY");
        taxOne.setTaxRate(BigDecimal.valueOf(6));
        
        Order orderOne = new Order();
        orderOne.setOrderNumber(1);
        orderOne.setCustomerName("Will Varner");
        orderOne.setOrderDate(dateOne);
        orderOne.setProductType(productOne);
        orderOne.setStateTax(taxOne);
        orderOne.setTotalSquareFootage(BigDecimal.valueOf(1000));
        
        dao.addOrder(orderOne);
        
        Product productTwo = new Product();
        productTwo.setCostPerSquareFoot(BigDecimal.valueOf(3.0));
        productTwo.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.25));
        productTwo.setProductName("vinyl");
        
        Tax taxTwo = new Tax();
        taxTwo.setState("TN");
        taxTwo.setTaxRate(BigDecimal.valueOf(5));
        
        Order orderTwo = new Order();
        orderTwo.setOrderNumber(2);
        orderTwo.setCustomerName("Abby Varner");
        orderTwo.setOrderDate(dateTwo);
        orderTwo.setProductType(productTwo);
        orderTwo.setStateTax(taxTwo);
        orderTwo.setTotalSquareFootage(BigDecimal.valueOf(2000));
        
        dao.addOrder(orderTwo);
        
        assertEquals(2, dao.getAllOrders().size());
    }

    /**
     * Test of getOrderByOrderNumber method, of class FlooringOrderDao.
     */
    @Test
    public void testGetOrderByOrderNumber() throws Exception {
        LocalDate dateOne = LocalDate.parse("2019-12-31");
        LocalDate dateTwo = LocalDate.parse("2019-04-20");
        
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax taxOne = new Tax();
        taxOne.setState("KY");
        taxOne.setTaxRate(BigDecimal.valueOf(6));
        
        Order orderOne = new Order();
        orderOne.setOrderNumber(1);
        orderOne.setCustomerName("Will Varner");
        orderOne.setOrderDate(dateOne);
        orderOne.setProductType(productOne);
        orderOne.setStateTax(taxOne);
        orderOne.setTotalSquareFootage(BigDecimal.valueOf(1000));
        
        dao.addOrder(orderOne);
        
        Product productTwo = new Product();
        productTwo.setCostPerSquareFoot(BigDecimal.valueOf(3.0));
        productTwo.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.25));
        productTwo.setProductName("vinyl");
        
        Tax taxTwo = new Tax();
        taxTwo.setState("TN");
        taxTwo.setTaxRate(BigDecimal.valueOf(5));
        
        Order orderTwo = new Order();
        orderTwo.setOrderNumber(2);
        orderTwo.setCustomerName("Abby Varner");
        orderTwo.setOrderDate(dateTwo);
        orderTwo.setProductType(productTwo);
        orderTwo.setStateTax(taxTwo);
        orderTwo.setTotalSquareFootage(BigDecimal.valueOf(2000));
        
        dao.addOrder(orderTwo);
        
        Order testOrder = dao.getOrderByOrderNumber(1);
        assertEquals(testOrder, orderOne);
    }

    /**
     * Test of saveWork method, of class FlooringOrderDao.
     */
    @Test
    public void testSaveWork() throws Exception {
    }

    /**
     * Test of modeSelection method, of class FlooringOrderDao.
     */
    @Test
    public void testModeSelectionAndGetCurrentMode() {
        int modeSelectionNumber = 1;
        dao.modeSelection(modeSelectionNumber);
        int currentMode = dao.getCurrentMode();
        assertEquals(currentMode, modeSelectionNumber);
    }
    
    /**
     * Test of uploadOrderFile method, of class FlooringOrderDao.
     */
    @Test
    public void testUploadOrderFile() throws Exception {
    }

    /**
     * Test of generateNextAvailableIdNumber method, of class FlooringOrderDao.
     */
    @Test
    public void testGenerateNextAvailableIdNumber() throws Exception {
        LocalDate dateOne = LocalDate.parse("2019-12-31");
        LocalDate dateTwo = LocalDate.parse("2019-04-20");
        
        Product productOne = new Product();
        productOne.setCostPerSquareFoot(BigDecimal.valueOf(3.5));
        productOne.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.00));
        productOne.setProductName("carpet");
        
        Tax taxOne = new Tax();
        taxOne.setState("KY");
        taxOne.setTaxRate(BigDecimal.valueOf(6));
        
        Order orderOne = new Order();
        orderOne.setOrderNumber(1);
        orderOne.setCustomerName("Will Varner");
        orderOne.setOrderDate(dateOne);
        orderOne.setProductType(productOne);
        orderOne.setStateTax(taxOne);
        orderOne.setTotalSquareFootage(BigDecimal.valueOf(1000));
        
        dao.addOrder(orderOne);
        
        Product productTwo = new Product();
        productTwo.setCostPerSquareFoot(BigDecimal.valueOf(3.0));
        productTwo.setLaborCostPerSquareFoot(BigDecimal.valueOf(1.25));
        productTwo.setProductName("vinyl");
        
        Tax taxTwo = new Tax();
        taxTwo.setState("TN");
        taxTwo.setTaxRate(BigDecimal.valueOf(5));
        
        Order orderTwo = new Order();
        orderTwo.setOrderNumber(2);
        orderTwo.setCustomerName("Abby Varner");
        orderTwo.setOrderDate(dateTwo);
        orderTwo.setProductType(productTwo);
        orderTwo.setStateTax(taxTwo);
        orderTwo.setTotalSquareFootage(BigDecimal.valueOf(2000));
        
        dao.addOrder(orderTwo);
        dao.saveWork();
        
        int nextIdNumber = dao.generateNextAvailableIdNumber();
        assertEquals(3, nextIdNumber);
    }

    
    
}
