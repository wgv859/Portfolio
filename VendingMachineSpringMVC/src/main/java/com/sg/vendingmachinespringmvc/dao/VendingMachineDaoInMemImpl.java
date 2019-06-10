/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author wgv85
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao {
    
    public VendingMachineDaoInMemImpl() {
        fillMachine();
    }

    private HashMap<Long, Item> itemMap = new HashMap<>();
    
        

    private ArrayList<Item> fillMachine() {
        long itemIdCounter = 1;
        ArrayList<Item> itemList = new ArrayList<>();
        
        Item item1 = new Item();
        item1.setItemNumber(itemIdCounter);
        itemIdCounter++;
        item1.setItemName("Pretzels");
        item1.setItemPrice("1.25");
        item1.setItemPriceLong(125);
        item1.setItemQuantity(10);
        itemList.add(item1);
        
        Item item2 = new Item();
        item2.setItemNumber(itemIdCounter);
        itemIdCounter++;
        item2.setItemName("Doritos");
        item2.setItemPrice("1.25");
        item2.setItemPriceLong(125);
        item2.setItemQuantity(10);
        itemList.add(item2);
        
        Item item3 = new Item();
        item3.setItemNumber(itemIdCounter);
        itemIdCounter++;
        item3.setItemName("Lays BBQ");
        item3.setItemPrice("1.25");
        item3.setItemPriceLong(125);
        item3.setItemQuantity(10);
        itemList.add(item3);
        
        Item item4 = new Item();
        item4.setItemNumber(itemIdCounter);
        itemIdCounter++;
        item4.setItemName("Snickers");
        item4.setItemPrice("1.50");
        item4.setItemPriceLong(150);
        item4.setItemQuantity(10);
        itemList.add(item4);
        
        Item item5 = new Item();
        item5.setItemNumber(itemIdCounter);
        itemIdCounter++;
        item5.setItemName("Mars");
        item5.setItemPrice("1.50");
        item5.setItemPriceLong(150);
        item5.setItemQuantity(10);
        itemList.add(item5);
        
        Item item6 = new Item();
        item6.setItemNumber(itemIdCounter);
        itemIdCounter++;
        item6.setItemName("WatChaMaCallIt");
        item6.setItemPrice("1.50");
        item6.setItemPriceLong(150);
        item6.setItemQuantity(10);
        itemList.add(item6);
        
        Item item7 = new Item();
        item7.setItemNumber(itemIdCounter);
        itemIdCounter++;
        item7.setItemName("Coca-Cola");
        item7.setItemPrice("1.75");
        item7.setItemPriceLong(175);
        item7.setItemQuantity(10);
        itemList.add(item7);
        
        Item item8 = new Item();
        item8.setItemNumber(itemIdCounter);
        itemIdCounter++;
        item8.setItemName("Mountain Dew");
        item8.setItemPrice("1.75");
        item8.setItemPriceLong(175);
        item8.setItemQuantity(10);
        itemList.add(item8);
        
        Item item9 = new Item();
        item9.setItemNumber(itemIdCounter);
        itemIdCounter++;
        item9.setItemName("Water");
        item9.setItemPrice("3.00");
        item9.setItemPriceLong(300);
        item9.setItemQuantity(10);
        itemList.add(item9);
        
        itemList.forEach((item) -> {
            itemMap.put(item.getItemNumber(), item);
        });
        
        return itemList;
    }

    @Override
    public Item addItem(Item item) {
        
        return item;
    }

    @Override
    public void removeItem(long itemId) {
        itemMap.remove(itemId);
    }

    @Override
    public void updateItem(Item item) {
        itemMap.put(item.getItemNumber(), item);
    }

    @Override
    public HashMap<Long, Item> getAllItems() {
        return itemMap;
    }

    @Override
    public Item getItemById(long itemId) {
        return itemMap.get(itemId);
    }

    @Override
    public List<Item> searchItems(Map<SearchTerm, String> criteria) {
        String itemNameSearchCriteria = criteria.get(SearchTerm.ITEM_NAME);
        String itemPriceSearchCriteria = criteria.get(SearchTerm.ITEM_PRICE);

        Predicate<Item> itemNameSearchPredicate;
        Predicate<Item> itemPriceSearchPredicate;

        Predicate<Item> truePredicate = (c) -> {
            return true;
        };

        if (itemNameSearchCriteria == null || itemNameSearchCriteria.isEmpty()) {
            itemNameSearchPredicate = truePredicate;
        } else {
            itemNameSearchPredicate = (c) -> c.getItemName().equals(itemNameSearchCriteria);
        }

        if (itemPriceSearchCriteria == null || itemPriceSearchCriteria.isEmpty()) {
            itemPriceSearchPredicate = truePredicate;
        } else {
            itemPriceSearchPredicate = (c) -> c.getItemPrice().equals(itemPriceSearchCriteria);
        }

        return itemMap.values().stream()
                .filter(itemNameSearchPredicate
                        .and(itemPriceSearchPredicate))
                .collect(Collectors.toList());
    }

}
