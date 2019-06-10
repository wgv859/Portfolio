/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.dao;

import com.sg.superherosightingmvc.model.Location;
import java.util.List;

/**
 *
 * @author wgv85
 */
public interface LocationDao {
    
    public void addLocation(Location location);

    public void deleteLocation(int locationId);
    
    public void updateLocation(Location location);
    
    public Location getLocationById(int locationId);
    
    public List<Location> getAllLocations();
    
    public List<Location> GetLocationsBySightingId(int sightingId);
    
    public List<Location> getLocationsByAnomalyId(int anomalyId);
    
}
