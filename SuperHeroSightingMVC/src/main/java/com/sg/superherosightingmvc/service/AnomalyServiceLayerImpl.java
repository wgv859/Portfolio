/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.service;

import com.sg.superherosightingmvc.dao.AnomalyDao;
import com.sg.superherosightingmvc.model.Anomaly;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author wgv85
 */
public class AnomalyServiceLayerImpl implements AnomalyServiceLayer {
    
    AnomalyDao anomalyDao;
    
    @Inject
    public AnomalyServiceLayerImpl(AnomalyDao anomalyDao) {
        this.anomalyDao = anomalyDao;
    }

    @Override
    public void addAnomaly(Anomaly anomaly) {
        anomalyDao.addAnomaly(anomaly);
    }

    @Override
    public void deleteAnomaly(int anomalyId) {
        anomalyDao.deleteAnomaly(anomalyId);
    }

    @Override
    public void updateAnomaly(Anomaly anomaly) {
        anomalyDao.updateAnomaly(anomaly);
    }

    @Override
    public Anomaly getAnomalyById(int anomalyId) {
        return anomalyDao.getAnomalyById(anomalyId);
    }

    @Override
    public List<Anomaly> getAllAnomalies() {
        return anomalyDao.getAllAnomalies();
    }

    @Override
    public List<Anomaly> getAnomaliesByLocationId(int locationId) {
        return anomalyDao.getAnomaliesByLocationId(locationId);
    }

    @Override
    public List<Anomaly> getAnomaliesByOrganizationId(int OrganizationId) {
        return anomalyDao.getAnomaliesByOrganizationId(OrganizationId);
    }

    @Override
    public List<Anomaly> getAnomaliesByAbilityId(int abilityId) {
        return anomalyDao.getAnomaliesByAbilityId(abilityId);
    }
    
}
