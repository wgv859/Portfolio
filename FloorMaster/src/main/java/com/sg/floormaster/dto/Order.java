/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author wgv85
 */
public class Order {
    
    
    private int orderNumber;
    private LocalDate orderDate;
    private String customerName;
    private Tax stateTax;
    private Product productType;
    private BigDecimal totalSquareFootage;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Tax getStateTax() {
        return stateTax;
    }

    public void setStateTax(Tax stateTax) {
        this.stateTax = stateTax;
    }

    public Product getProductType() {
        return productType;
    }

    public void setProductType(Product productType) {
        this.productType = productType;
    }

    public BigDecimal getTotalSquareFootage() {
        return totalSquareFootage;
    }

    public void setTotalSquareFootage(BigDecimal totalSquareFootage) {
        this.totalSquareFootage = totalSquareFootage;
    }
    
    public BigDecimal getTotalMaterialCost() {
        return productType.getCostPerSquareFoot().multiply(totalSquareFootage).setScale(2, RoundingMode.HALF_UP);
    }
    
    public BigDecimal getTotalLaborCost() {
        return productType.getLaborCostPerSquareFoot().multiply(totalSquareFootage).setScale(2, RoundingMode.HALF_UP);
    }
    
    public BigDecimal getSubTotal() {
        return getTotalMaterialCost().add(getTotalLaborCost()).setScale(2, RoundingMode.HALF_UP);
    }
    
    public BigDecimal getTotalTax() {
        BigDecimal tax = stateTax.getTaxRate().divide(new BigDecimal("100"));
        return getSubTotal().multiply(tax).setScale(2, RoundingMode.HALF_UP);
    }
    
    public BigDecimal getTotalCost() {
        return getSubTotal().add(getTotalTax().setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.orderNumber;
        hash = 83 * hash + Objects.hashCode(this.orderDate);
        hash = 83 * hash + Objects.hashCode(this.customerName);
        hash = 83 * hash + Objects.hashCode(this.stateTax);
        hash = 83 * hash + Objects.hashCode(this.productType);
        hash = 83 * hash + Objects.hashCode(this.totalSquareFootage);
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
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.stateTax, other.stateTax)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.totalSquareFootage, other.totalSquareFootage)) {
            return false;
        }
        return true;
    }
    
      

}
