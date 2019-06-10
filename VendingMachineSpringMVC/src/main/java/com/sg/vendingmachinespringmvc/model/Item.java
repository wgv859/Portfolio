/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.util.Objects;

/**
 *
 * @author wgv85
 */
public class Item {
    
    private long itemNumber;
    private String itemName;
    private String itemPrice;
    private long itemPriceLong;
    private long itemQuantity;

    public long getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(long itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public long getItemPriceLong() {
        return itemPriceLong;
    }

    public void setItemPriceLong(long itemPriceLong) {
        this.itemPriceLong = itemPriceLong;
    }

    public long getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.itemNumber ^ (this.itemNumber >>> 32));
        hash = 29 * hash + Objects.hashCode(this.itemName);
        hash = 29 * hash + Objects.hashCode(this.itemPrice);
        hash = 29 * hash + (int) (this.itemPriceLong ^ (this.itemPriceLong >>> 32));
        hash = 29 * hash + (int) (this.itemQuantity ^ (this.itemQuantity >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.itemNumber != other.itemNumber) {
            return false;
        }
        if (this.itemPriceLong != other.itemPriceLong) {
            return false;
        }
        if (this.itemQuantity != other.itemQuantity) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        return true;
    }

    
    
}