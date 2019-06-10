/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.model.Money;
import java.util.ArrayList;
import java.util.HashMap;
import javax.inject.Inject;

/**
 *
 * @author wgv85
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    Money money;
    double userMoney;
    long userMoneyLong;
    String userMoneyString;

    @Inject
    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public ArrayList<Item> getAllItems() {
        HashMap itemMap = dao.getAllItems();
        ArrayList itemList = new ArrayList<>();

        itemMap.values().forEach((item) -> {
            itemList.add(item);
        });
        return itemList;
    }

    @Override
    public Item getItem(String itemNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String purchaseItem(long itemNumber) {
        Item purchaseItem = dao.getItemById(itemNumber);

        long purchaseItemPrice = purchaseItem.getItemPriceLong();

        String message = "";

        if (userMoney < purchaseItemPrice) {
            message = "Please deposit more money";
        }

        if (purchaseItem.getItemQuantity() == 0) {
            message = "Sold out. Please choose another item.";
        }

        if (userMoney >= purchaseItemPrice && purchaseItem.getItemQuantity() > 0) {
            purchaseItem.setItemQuantity(purchaseItem.getItemQuantity() - 1);
            dao.updateItem(purchaseItem);
            message = "THANK YOU";
        }

        return message;

    }

    @Override
    public String refundMoney(long itemNumberLong) {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        Item purchasedItem = dao.getItemById(itemNumberLong);
        long purchasedItemPrice = purchasedItem.getItemPriceLong();
        long changeDue = userMoneyLong - purchasedItemPrice;
        String changeString;
        while (changeDue > 0) {
            if (changeDue >= 25) {
                changeDue = changeDue - 25;
                quarters = quarters + 1;
            } else if (changeDue >= 10) {
                changeDue = changeDue - 10;
                dimes = dimes + 1;
            } else if (changeDue >= 5) {
                changeDue = changeDue - 5;
                nickels = nickels + 1;
            }
        }
        
        changeString = "Quarters: " + quarters + " Dimes: " + dimes + " Nickels: " + nickels;

        
        return changeString;

    }

    @Override
    public String addDollar() {
        userMoney += 100;
        userMoneyLong += 100;
        userMoneyString = Double.toString(userMoney);
        return userMoneyString;
    }

    @Override
    public String addQuarter() {
        userMoney += 25;
        userMoneyLong += 25;
        userMoneyString = Double.toString(userMoney);
        return userMoneyString;
    }

    @Override
    public String addDime() {
        userMoney += 10;
        userMoneyLong += 10;
        userMoneyString = Double.toString(userMoney);
        return userMoneyString;
    }

    @Override
    public String addNickel() {
        userMoney += 5;
        userMoneyLong += 5;
        userMoneyString = Double.toString(userMoney);
        return userMoneyString;
    }

    @Override
    public double getUserMoneyTotal() {
        double userMoneyTotal = userMoney / 100;
        return userMoneyTotal;
    }

    @Override
    public void clearUserMoneyTotal() {
        userMoney = 0;
        userMoneyLong = 0;
    }

    @Override
    public Item getItem(long itemId) {
        Item chosenItem = dao.getItemById(itemId);
        return chosenItem;
    }

}
