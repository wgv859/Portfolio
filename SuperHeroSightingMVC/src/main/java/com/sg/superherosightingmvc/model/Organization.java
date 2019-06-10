/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.model;

import java.util.Objects;

/**
 *
 * @author wgv85
 */
public class Organization {
    
    private int organizationId;
    
    private String organizationName;
    
    private String organizationDescription;
    
    private String organizationStreet;
    
    private String organizationCity;
    
    private String organizationState;
    
    private String organizationZip;
    
    private String organizationPhone;
    
    private String organizationEmail;

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public String getOrganizationStreet() {
        return organizationStreet;
    }

    public void setOrganizationStreet(String organizationStreet) {
        this.organizationStreet = organizationStreet;
    }

    public String getOrganizationCity() {
        return organizationCity;
    }

    public void setOrganizationCity(String organizationCity) {
        this.organizationCity = organizationCity;
    }

    public String getOrganizationState() {
        return organizationState;
    }

    public void setOrganizationState(String organizationState) {
        this.organizationState = organizationState;
    }

    public String getOrganizationZip() {
        return organizationZip;
    }

    public void setOrganizationZip(String organizationZip) {
        this.organizationZip = organizationZip;
    }

    public String getOrganizationPhone() {
        return organizationPhone;
    }

    public void setOrganizationPhone(String organizationPhone) {
        this.organizationPhone = organizationPhone;
    }

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.organizationId;
        hash = 97 * hash + Objects.hashCode(this.organizationName);
        hash = 97 * hash + Objects.hashCode(this.organizationDescription);
        hash = 97 * hash + Objects.hashCode(this.organizationStreet);
        hash = 97 * hash + Objects.hashCode(this.organizationCity);
        hash = 97 * hash + Objects.hashCode(this.organizationState);
        hash = 97 * hash + Objects.hashCode(this.organizationZip);
        hash = 97 * hash + Objects.hashCode(this.organizationPhone);
        hash = 97 * hash + Objects.hashCode(this.organizationEmail);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.organizationName, other.organizationName)) {
            return false;
        }
        if (!Objects.equals(this.organizationDescription, other.organizationDescription)) {
            return false;
        }
        if (!Objects.equals(this.organizationStreet, other.organizationStreet)) {
            return false;
        }
        if (!Objects.equals(this.organizationCity, other.organizationCity)) {
            return false;
        }
        if (!Objects.equals(this.organizationState, other.organizationState)) {
            return false;
        }
        if (!Objects.equals(this.organizationZip, other.organizationZip)) {
            return false;
        }
        if (!Objects.equals(this.organizationPhone, other.organizationPhone)) {
            return false;
        }
        if (!Objects.equals(this.organizationEmail, other.organizationEmail)) {
            return false;
        }
        return true;
    }
    
    
    
}
