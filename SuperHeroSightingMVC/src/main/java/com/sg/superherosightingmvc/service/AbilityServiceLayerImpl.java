/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.service;

import com.sg.superherosightingmvc.dao.AbilityDao;
import com.sg.superherosightingmvc.model.Ability;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author wgv85
 */
public class AbilityServiceLayerImpl implements AbilityServiceLayer {
    
    AbilityDao abilityDao;
    
    @Inject
    public AbilityServiceLayerImpl(AbilityDao abilityDao) {
        this.abilityDao = abilityDao;
    }

    @Override
    public void addAbility(Ability ability) {
        abilityDao.addAbility(ability);
    }

    @Override
    public void deleteAbility(int abilityId) {
        abilityDao.deleteAbility(abilityId);
    }

    @Override
    public void updateAbility(Ability ability) {
        abilityDao.updateAbility(ability);
    }

    @Override
    public Ability getAbilityById(int abilityId) {
        return abilityDao.getAbilityById(abilityId);
    }

    @Override
    public List<Ability> getAllAbilities() {
        return abilityDao.getAllAbilities();
    }

    @Override
    public List<Ability> getAbilitiesByAnomalyId(int anomalyId) {
        return abilityDao.getAbilitiesByAnomalyId(anomalyId);
    }
    
}
