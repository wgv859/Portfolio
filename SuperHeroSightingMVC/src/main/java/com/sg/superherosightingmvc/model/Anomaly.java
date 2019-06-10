/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author wgv85
 */
public class Anomaly {
    
    private int anomalyId;
    
    private String anomalyName;
    
    private String anomalyDescription;
    
    private List<Ability> abilities;
    
    private List<Organization> organizations;

    public int getAnomalyId() {
        return anomalyId;
    }

    public void setAnomalyId(int anomalyId) {
        this.anomalyId = anomalyId;
    }

    public String getAnomalyName() {
        return anomalyName;
    }

    public void setAnomalyName(String anomalyName) {
        this.anomalyName = anomalyName;
    }

    public String getAnomalyDescription() {
        return anomalyDescription;
    }

    public void setAnomalyDescription(String anomalyDescription) {
        this.anomalyDescription = anomalyDescription;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.anomalyId;
        hash = 97 * hash + Objects.hashCode(this.anomalyName);
        hash = 97 * hash + Objects.hashCode(this.anomalyDescription);
        hash = 97 * hash + Objects.hashCode(this.abilities);
        hash = 97 * hash + Objects.hashCode(this.organizations);
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
        final Anomaly other = (Anomaly) obj;
        if (this.anomalyId != other.anomalyId) {
            return false;
        }
        if (!Objects.equals(this.anomalyName, other.anomalyName)) {
            return false;
        }
        if (!Objects.equals(this.anomalyDescription, other.anomalyDescription)) {
            return false;
        }
        if (!Objects.equals(this.abilities, other.abilities)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        return true;
    }
    
    
    
}
