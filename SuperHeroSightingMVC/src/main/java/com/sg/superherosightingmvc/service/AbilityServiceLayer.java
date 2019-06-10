/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.service;

import com.sg.superherosightingmvc.model.Ability;
import java.util.List;

/**
 *
 * @author wgv85
 */
public interface AbilityServiceLayer {
    
    public void addAbility(Ability ability);
    
    public void deleteAbility(int abilityId);
    
    public void updateAbility(Ability ability);
    
    public Ability getAbilityById(int abilityId);
    
    public List<Ability> getAllAbilities();
    
    public List<Ability> getAbilitiesByAnomalyId(int anomalyId);
    
}
