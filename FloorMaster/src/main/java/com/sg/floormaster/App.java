/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster;

import com.sg.floormaster.controller.FlooringController;
import com.sg.floormaster.dao.FlooringDuplicateOrderNumberException;

import com.sg.floormaster.dao.FlooringInvalidDataException;
import com.sg.floormaster.dao.FlooringPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author wgv85
 */
public class App {
    
    public static void main(String[] args) throws FlooringPersistenceException, FlooringInvalidDataException, FlooringDuplicateOrderNumberException {
//        UserIO myIo = new UserIOConsoleImpl();
//        FlooringView myView = new FlooringView(myIo);
//        FlooringOrderDao myOrderDao = new FlooringOrderDaoTrainingFileImpl();
//        FlooringProductDao myProductDao = new FlooringProductDaoFileImpl();
//        FlooringTaxDao myTaxDao = new FlooringTaxDaoFileImpl();
//        
//        FlooringAuditDao myAuditDao = new FlooringAuditDaoFileImpl();
//        FlooringServiceLayer myService = new FlooringServiceLayerImpl(myOrderDao, myProductDao, myTaxDao);
//        FlooringController controller = new FlooringController(myService, myView);
//        controller.run();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController controller = ctx.getBean("flooringController", FlooringController.class);
        controller.run();
    }
    
}
