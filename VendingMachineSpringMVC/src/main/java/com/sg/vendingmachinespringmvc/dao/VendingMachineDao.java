/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wgv85
 */
public interface VendingMachineDao {
    
    public Item addItem(Item item);
    
    public void removeItem(long itemId);
    
    public void updateItem(Item item);
    
    public HashMap<Long, Item> getAllItems();
    
    public Item getItemById(long itemId);
    
    public List<Item> searchItems(Map<SearchTerm, String> criteria);
    
}