/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

/**
 *
 * @author wgv85
 */
public class Money {
    private final int dollar = 100;
    private final int quarter = 25;
    private final int dime = 10;
    private final int nickel = 5;

    public int getDollar() {
        return dollar;
    }

    public int getQuarter() {
        return quarter;
    }

    public int getDime() {
        return dime;
    }

    public int getNickel() {
        return nickel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.dollar;
        hash = 83 * hash + this.quarter;
        hash = 83 * hash + this.dime;
        hash = 83 * hash + this.nickel;
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
        final Money other = (Money) obj;
        if (this.dollar != other.dollar) {
            return false;
        }
        if (this.quarter != other.quarter) {
            return false;
        }
        if (this.dime != other.dime) {
            return false;
        }
        if (this.nickel != other.nickel) {
            return false;
        }
        return true;
    }
    
    
}