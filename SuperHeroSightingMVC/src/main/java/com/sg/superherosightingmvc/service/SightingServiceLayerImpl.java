/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.service;

import com.sg.superherosightingmvc.dao.SightingDao;
import com.sg.superherosightingmvc.model.Sighting;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author wgv85
 */
public class SightingServiceLayerImpl implements SightingServiceLayer {
    
    SightingDao sightingDao;
    
    @Inject
    public SightingServiceLayerImpl(SightingDao sightingDao) {
        this.sightingDao = sightingDao;
    }

    @Override
    public void addSighting(Sighting sighting) {
        sightingDao.addSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightingId) {
        sightingDao.deleteSighting(sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        sightingDao.updateSighting(sighting);
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        return sightingDao.getSightingById(sightingId);
    }

    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        return sightingDao.getSightingsByLocationId(locationId);
    }

    @Override
    public List<Sighting> getSightingsByAnomalyId(int anomalyId) {
        return sightingDao.getSightingsByAnomalyId(anomalyId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate date) {
        return sightingDao.getAllSightingsByDate(date);
    }

    @Override
    public List<Sighting> getLastTenSightings() {
        return sightingDao.getLastTenSightings();
    }
    
}
