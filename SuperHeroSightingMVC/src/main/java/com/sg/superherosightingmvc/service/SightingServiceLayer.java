/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.service;

import com.sg.superherosightingmvc.model.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author wgv85
 */
public interface SightingServiceLayer {
    
    public void addSighting(Sighting sighting);
    
    public void deleteSighting(int sightingId);
    
    public void updateSighting(Sighting sighting);
    
    public Sighting getSightingById(int sightingId);
    
    public List<Sighting> getSightingsByLocationId(int locationId);
    
    public List<Sighting> getSightingsByAnomalyId(int anomalyId);
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsByDate(LocalDate date);
    
    public List<Sighting> getLastTenSightings();
    
}
