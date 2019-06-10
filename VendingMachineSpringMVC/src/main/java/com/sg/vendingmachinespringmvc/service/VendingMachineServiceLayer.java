/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author wgv85
 */
public interface VendingMachineServiceLayer {
    
    ArrayList<Item> getAllItems();
    
    Item getItem(String itemNumber);
    
    String purchaseItem(long userMoney);
    
    String refundMoney(long itemNumberLong);
    
    String addDollar();
    String addQuarter();
    String addDime();
    String addNickel();
    
    double getUserMoneyTotal();
    
    void clearUserMoneyTotal();
    
    Item getItem(long itemId);
    
}