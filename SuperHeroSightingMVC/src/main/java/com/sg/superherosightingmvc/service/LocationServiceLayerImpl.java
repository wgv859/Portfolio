/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.service;

import com.sg.superherosightingmvc.dao.LocationDao;
import com.sg.superherosightingmvc.model.Location;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author wgv85
 */
public class LocationServiceLayerImpl implements LocationServiceLayer {
    
    LocationDao locationDao;
    
    @Inject
    public LocationServiceLayerImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public void addLocation(Location location) {
        locationDao.addLocation(location);
    }

    @Override
    public void deleteLocation(int locationId) {
        locationDao.deleteLocation(locationId);
    }

    @Override
    public void updateLocation(Location location) {
        locationDao.updateLocation(location);
    }

    @Override
    public Location getLocationById(int locationId) {
        return locationDao.getLocationById(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public List<Location> GetLocationsBySightingId(int sightingId) {
        return locationDao.GetLocationsBySightingId(sightingId);
    }

    @Override
    public List<Location> getLocationsByAnomalyId(int anomalyId) {
        return locationDao.getLocationsByAnomalyId(anomalyId);
    }
    
}
