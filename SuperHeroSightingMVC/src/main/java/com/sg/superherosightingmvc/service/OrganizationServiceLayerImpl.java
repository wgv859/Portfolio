/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.service;

import com.sg.superherosightingmvc.dao.OrganizationDao;
import com.sg.superherosightingmvc.model.Organization;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author wgv85
 */
public class OrganizationServiceLayerImpl implements OrganizationServiceLayer {
    
    OrganizationDao organizationDao;
    
    @Inject
    public OrganizationServiceLayerImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    public void addOrganization(Organization organization) {
        organizationDao.addOrganization(organization);
    }

    @Override
    public void deleteOrganization(int OrganizationId) {
        organizationDao.deleteOrganization(OrganizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        organizationDao.updateOrganization(organization);
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        return organizationDao.getOrganizationById(organizationId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }

    @Override
    public List<Organization> getOrganizationsByAnomalyId(int anomalyId) {
        return organizationDao.getOrganizationsByAnomalyId(anomalyId);
    }
    
}
