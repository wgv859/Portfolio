/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.controller;

import com.sg.floormaster.dao.FlooringDuplicateOrderNumberException;
import com.sg.floormaster.dao.FlooringInvalidDataException;
import com.sg.floormaster.dao.FlooringOrderNotFoundException;
import com.sg.floormaster.dao.FlooringPersistenceException;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Tax;
import com.sg.floormaster.ui.FlooringView;
import com.sg.floormaster.service.FlooringServiceLayer;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author wgv85
 */
public class FlooringController {

    private final FlooringView view;
    private final FlooringServiceLayer service;

    public FlooringController(FlooringServiceLayer service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        try {
            service.uploadOrderFile();
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        displayOrdersForDate();
                        break;
                    case 3:
                        createOrder();
                        break;
                    case 4:
                        editOrder();
                        break;
                    case 5:
                        deleteOrder();
                        break;
                    case 6:
                        saveCurrentWork();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    case 8:
                        setMode();
                        break;
                    case 9:
                        getCurrentMode();
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (FlooringPersistenceException | FlooringInvalidDataException
                | FlooringDuplicateOrderNumberException | FlooringOrderNotFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() throws FlooringPersistenceException {
        view.displayDisplayOrdersBanner();
        List<Order> orders = service.getAllOrders();
        view.displayListOfOrders(orders);
        view.displayListOfOrdersSuccessBanner();
    }

    private void displayOrdersForDate() throws FlooringPersistenceException, FlooringInvalidDataException {
        view.displayDisplayOrdersBanner();
        LocalDate date = view.getDate();
        if (date == null || service.getAllOrdersForDate(date).isEmpty()) {
            view.displayErrorMessage("Invalid date. Please try again.");
            return;
        }
        List<Order> orders = service.getAllOrdersForDate(date);
        view.displayListOfOrders(orders);
        view.displayListOfOrdersSuccessBanner();
    }

    private void createOrder() throws FlooringPersistenceException, FlooringInvalidDataException, FlooringDuplicateOrderNumberException {
        view.displayCreateOrderBanner();
        List<Product> productList = service.getAllProducts();
        List<Tax> taxList = service.getAllTaxInfo();
        int orderNumber = service.generateNextAvailableIdNumber();
        Order currentOrder = view.createOrder(productList, taxList, orderNumber);
        
        try {
            service.addNewOrder(currentOrder);
        } catch (FlooringInvalidDataException e) {
            view.displayCreateOrderFailureBanner();
            return;//is this ok? Will it work?
        }
        view.displayCreateOrderSuccessBanner();
    }

    private void editOrder() throws FlooringPersistenceException, FlooringDuplicateOrderNumberException, FlooringOrderNotFoundException, FlooringInvalidDataException {
        view.displayEditOrderBanner();
        List<Product> productList = service.getAllProducts();
        List<Tax> taxList = service.getAllTaxInfo();
        int orderNumberChoice = view.displayGetOrderNumber();
        LocalDate dateChoice = view.getDate();
        Order orderOne = service.getOrderByOrderNumber(orderNumberChoice);
        Order bailOutOrder = service.getOrderByOrderNumber(orderNumberChoice);
        
            if (orderOne != null && dateChoice.equals(orderOne.getOrderDate())) {
                try {
                orderOne = view.getUpdateOrderInfo(orderOne, productList, taxList);
                service.updateOrder(orderOne);
                if(orderOne.equals(bailOutOrder)){
                    view.displayEditOrderSuccessBanner();
                }
                } catch(FlooringInvalidDataException  e) {
                    view.displayEditOrderFailureBanner();
                }
                
            } else {
                view.displayEditOrderSuccessBanner();
            }
    }

    private void deleteOrder() throws FlooringPersistenceException, FlooringOrderNotFoundException, FlooringInvalidDataException {
        view.displayRemoveOrderBanner();
        int orderNumberChoice = view.displayGetOrderNumber();
        LocalDate dateChoice = view.getDate();
        Order orderOne = service.getOrderByOrderNumber(orderNumberChoice);
        if (orderOne != null && dateChoice.equals(orderOne.getOrderDate())) {
            int userConfirmation = view.removeOrderConfirmation(orderOne);
            if(userConfirmation == 1) {
                try {
                    service.removeOrder(orderOne);
                    view.displayRemoveOrderSuccessBanner();
                } catch (FlooringPersistenceException e) {
                    view.displayErrorMessage(e.getMessage());
                    }
            }
            if(userConfirmation == 2) {
                view.displayRemoveOrderFailBanner();
            }
        }
    }

    private void saveCurrentWork() throws FlooringPersistenceException {
        view.displaySaveWorkBanner();
        view.displayBlankLine();
        int confirmation = view.getSaveWorkConfirmation();
        if (confirmation == 1) {
            try {
                service.save();
                view.displaySaveWorkSuccessBanner();
            } catch (FlooringPersistenceException e) {
                view.displaySaveWorkFailureBanner();
            }
        }
        if (confirmation == 2) {
            view.displaySaveWorkFailureBanner();
        }
    }

    private void exitMessage() throws FlooringPersistenceException {
        try {
            service.save();
            view.displaySaveWorkSuccessBanner();
        } catch(FlooringPersistenceException e) {
            view.displaySaveWorkFailureBanner();
        }
        
        view.displayGoodbyeMessage();
    }
    
    private void setMode() {
        view.displaySetModeBanner();
        int userChoice = view.displayGetModeSelection();
        service.getModeSelection(userChoice);
        if(userChoice == 1) {
            view.displayProductionModeSwitchSuccessBanner();
        }
        if(userChoice == 2) {
            view.displayTrainingModeSwitchSuccessBanner();
        }
    }
    
    private void getCurrentMode() {
        int currentMode = service.getCurrentMode();
        if(currentMode == 1) {
            view.displayProductionModeSwitchSuccessBanner();
        }
        if(currentMode == 2) {
            view.displayTrainingModeSwitchSuccessBanner();
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

}
