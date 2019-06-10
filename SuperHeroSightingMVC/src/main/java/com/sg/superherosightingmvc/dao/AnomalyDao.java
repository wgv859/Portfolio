/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.dao;

import com.sg.superherosightingmvc.model.Anomaly;
import java.util.List;

/**
 *
 * @author wgv85
 */
public interface AnomalyDao {
    
    public void addAnomaly(Anomaly anomaly);
    
    public void deleteAnomaly(int anomalyId);
    
    public void updateAnomaly(Anomaly anomaly);
    
    public Anomaly getAnomalyById(int anomalyId);
    
    public List<Anomaly> getAllAnomalies();
    
    public List<Anomaly> getAnomaliesByLocationId(int locationId);
    
    public List<Anomaly> getAnomaliesByOrganizationId(int OrganizationId);
    
     public List<Anomaly> getAnomaliesByAbilityId(int abilityId);
    
}
