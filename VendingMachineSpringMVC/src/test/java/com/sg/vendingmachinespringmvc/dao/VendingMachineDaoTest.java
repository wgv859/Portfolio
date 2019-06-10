/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author wgv85
 */
public class VendingMachineDaoTest {
    
    private VendingMachineDao dao;
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("vendingMachineDao", VendingMachineDao.class);
    }
    
    @After
    public void tearDown() {
    }
    
//    @Test
//    public void addGetDeleteItem() {
//        Item item1 = new Item();
//        item1.setItemName("Item1");
//        item1.setItemPrice("1.00");
//        item1.setItemQuantity(10);
//        dao.addItem(item1);
//        
//        Item fromDb = dao.getItemById(item1.getItemNumber());
//        assertEquals(fromDb, item1);
//        dao.removeItem(item1.getItemNumber());
//    }
    
}
