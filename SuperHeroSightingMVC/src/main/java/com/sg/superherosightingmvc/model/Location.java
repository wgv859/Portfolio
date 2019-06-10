/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author wgv85
 */
public class Location {
    
    private int locationId;
    
    private String locationName;
    
    private String locationDescription;
    
    private String locationStreet;
    
    private String locationCity;
    
    private String locationState;
    
    private String locationZip;
    
    private BigDecimal locationLatitude;
    
    private BigDecimal locationLongitude;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getLocationStreet() {
        return locationStreet;
    }

    public void setLocationStreet(String locationStreet) {
        this.locationStreet = locationStreet;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationState() {
        return locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getLocationZip() {
        return locationZip;
    }

    public void setLocationZip(String locationZip) {
        this.locationZip = locationZip;
    }

    public BigDecimal getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(BigDecimal locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public BigDecimal getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(BigDecimal locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.locationId;
        hash = 29 * hash + Objects.hashCode(this.locationName);
        hash = 29 * hash + Objects.hashCode(this.locationDescription);
        hash = 29 * hash + Objects.hashCode(this.locationStreet);
        hash = 29 * hash + Objects.hashCode(this.locationCity);
        hash = 29 * hash + Objects.hashCode(this.locationState);
        hash = 29 * hash + Objects.hashCode(this.locationZip);
        hash = 29 * hash + Objects.hashCode(this.locationLatitude);
        hash = 29 * hash + Objects.hashCode(this.locationLongitude);
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.locationDescription, other.locationDescription)) {
            return false;
        }
        if (!Objects.equals(this.locationStreet, other.locationStreet)) {
            return false;
        }
        if (!Objects.equals(this.locationCity, other.locationCity)) {
            return false;
        }
        if (!Objects.equals(this.locationState, other.locationState)) {
            return false;
        }
        if (!Objects.equals(this.locationZip, other.locationZip)) {
            return false;
        }
        if (!Objects.equals(this.locationLatitude, other.locationLatitude)) {
            return false;
        }
        if (!Objects.equals(this.locationLongitude, other.locationLongitude)) {
            return false;
        }
        return true;
    }
    
    
    
}
