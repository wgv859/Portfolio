/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Tax;
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
public class FlooringTaxDaoFileImpl implements FlooringTaxDao {
    
    public static final String TAX_FILE = "taxFile.txt";
    public static final String DELIMITER = "::";
    

    @Override
    public List<Tax> getTaxInfo() throws FlooringPersistenceException {
        return readFile();
    }
    
    private List<Tax> readFile() throws FlooringPersistenceException {
        List<Tax> taxes = new ArrayList<>();
        
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(TAX_FILE)))) {
            while(scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] tokens = currentLine.split(DELIMITER);
                Tax currentTax = new Tax();
                currentTax.setState(tokens[0]);
                currentTax.setTaxRate(new BigDecimal(tokens[1]));
                taxes.add(currentTax);
            }
            scanner.close();
        }catch(IOException e) {
            throw new FlooringPersistenceException("Could not access tax information. Sorry.");
        }
        return taxes;
    }

}
