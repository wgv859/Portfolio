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
public class Ability {
    
     int abilityId;
    
    String abilityName;
    
    String abilityDescription;

    public int getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(int abilityId) {
        this.abilityId = abilityId;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getAbilityDescription() {
        return abilityDescription;
    }

    public void setAbilityDescription(String abilityDescription) {
        this.abilityDescription = abilityDescription;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.abilityId;
        hash = 29 * hash + Objects.hashCode(this.abilityName);
        hash = 29 * hash + Objects.hashCode(this.abilityDescription);
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
        final Ability other = (Ability) obj;
        if (this.abilityId != other.abilityId) {
            return false;
        }
        if (!Objects.equals(this.abilityName, other.abilityName)) {
            return false;
        }
        if (!Objects.equals(this.abilityDescription, other.abilityDescription)) {
            return false;
        }
        return true;
    }
    
    
    
}
