/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author wgv85
 */
public class FlooringProductDaoFileImpl implements FlooringProductDao {
    
    public static final String PRODUCT_FILE = "products.txt";
    public static final String DELIMITER = "::";
    
    
    @Override
    public List<Product> getProductInfo() throws FlooringPersistenceException {
        List<Product> products = new ArrayList<>();
        
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)))) {
            while(scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] tokens = currentLine.split(DELIMITER);
                Product product = new Product();
                product.setProductName(tokens[0]);
                product.setCostPerSquareFoot(new BigDecimal(tokens[1]));
                product.setLaborCostPerSquareFoot(new BigDecimal(tokens[2]));
                products.add(product);
            }
            scanner.close();
        }catch(IOException e) {
            throw new FlooringPersistenceException("Could not access product information. Sorry.");
        }
        return products;
    }
    
}
