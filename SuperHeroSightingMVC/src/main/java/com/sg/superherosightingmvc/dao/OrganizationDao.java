/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.dao;

import com.sg.superherosightingmvc.model.Organization;
import java.util.List;

/**
 *
 * @author wgv85
 */
public interface OrganizationDao {
    
    public void addOrganization(Organization organization);
    
    public void deleteOrganization(int OrganizationId);
    
    public void updateOrganization(Organization organization);
    
    public Organization getOrganizationById(int organizationId);
    
    public List<Organization> getAllOrganizations();
    
    public List<Organization> getOrganizationsByAnomalyId(int anomalyId);
    
}
