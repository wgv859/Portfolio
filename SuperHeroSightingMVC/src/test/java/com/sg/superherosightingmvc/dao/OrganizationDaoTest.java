/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.dao;

import com.sg.superherosightingmvc.model.Ability;
import com.sg.superherosightingmvc.model.Anomaly;
import com.sg.superherosightingmvc.model.Location;
import com.sg.superherosightingmvc.model.Organization;
import com.sg.superherosightingmvc.model.Sighting;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author wgv85
 */
public class OrganizationDaoTest {
    
    AbilityDao abilityDao;
    AnomalyDao anomalyDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    
    public OrganizationDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        
        abilityDao = ctx.getBean("AbilityDao", AbilityDao.class);
        anomalyDao = ctx.getBean("AnomalyDao", AnomalyDao.class);
        locationDao = ctx.getBean("LocationDao", LocationDao.class);
        organizationDao = ctx.getBean("OrganizationDao", OrganizationDao.class);
        sightingDao = ctx.getBean("SightingDao", SightingDao.class);
        
        List<Ability> abilities = abilityDao.getAllAbilities();
        for(Ability current : abilities) {
            abilityDao.deleteAbility(current.getAbilityId());
        }
        
        List<Anomaly> anomalies = anomalyDao.getAllAnomalies();
        for(Anomaly current : anomalies)  {
            anomalyDao.deleteAnomaly(current.getAnomalyId());
        }
        
        List<Location> locations = locationDao.getAllLocations();
        for(Location current : locations) {
            locationDao.deleteLocation(current.getLocationId());
        }
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization current : organizations) {
            organizationDao.deleteOrganization(current.getOrganizationId());
        }
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting current : sightings) {
            sightingDao.deleteSighting(current.getSightingId());
        }
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetOrganization() {
        
        Organization org = new Organization();
        org.setOrganizationName("Org Name");
        org.setOrganizationDescription("test");
        org.setOrganizationStreet("test");
        org.setOrganizationCity("test");
        org.setOrganizationState("test");
        org.setOrganizationZip("11111");
        org.setOrganizationPhone("1112223333");
        org.setOrganizationEmail("test@test.com");
        
        organizationDao.addOrganization(org);
        
        Organization fromDao = organizationDao.getOrganizationById(org.getOrganizationId());
        
        assertEquals(fromDao, org);
        
    }
    
    @Test
    public void testAddGetDeleteOrganization() {
        
        Organization org = new Organization();
        org.setOrganizationName("Org Name");
        org.setOrganizationDescription("test");
        org.setOrganizationStreet("test");
        org.setOrganizationCity("test");
        org.setOrganizationState("test");
        org.setOrganizationZip("11111");
        org.setOrganizationPhone("1112223333");
        org.setOrganizationEmail("test@test.com");
        
        organizationDao.addOrganization(org);
        
        Organization fromDao = organizationDao.getOrganizationById(org.getOrganizationId());
        
        assertEquals(fromDao, org);
        
        organizationDao.deleteOrganization(org.getOrganizationId());
        assertNull(organizationDao.getOrganizationById(org.getOrganizationId()));
        
    }
    
    @Test
    public void testAddGetUpdateOrganization() {
        
        Organization org = new Organization();
        org.setOrganizationName("Org Name");
        org.setOrganizationDescription("test");
        org.setOrganizationStreet("test");
        org.setOrganizationCity("test");
        org.setOrganizationState("test");
        org.setOrganizationZip("11111");
        org.setOrganizationPhone("1112223333");
        org.setOrganizationEmail("test@test.com");
        
        organizationDao.addOrganization(org);
        
        Organization fromDao = organizationDao.getOrganizationById(org.getOrganizationId());
        
        assertEquals(fromDao, org);
        
        org.setOrganizationName("Updated Org Name");
        
        organizationDao.updateOrganization(org);
        
        fromDao = organizationDao.getOrganizationById(org.getOrganizationId());
        
        assertEquals(org, fromDao);
        
    }
    
    @Test
    public void testAddGetOrganizationById() {
        Organization org = new Organization();
        organizationDao.addOrganization(org);
        
        Organization fromDao = organizationDao.getOrganizationById(org.getOrganizationId());
        assertEquals(org, fromDao);
    }
    
    @Test
    public void testGetAllOrganizations() {
        Organization org = new Organization();
        organizationDao.addOrganization(org);
        
        Organization org2 = new Organization();
        organizationDao.addOrganization(org2);
        
        List<Organization> orgs = organizationDao.getAllOrganizations();
        assertEquals(2, orgs.size());
    }
    
    @Test
    public void testGetAllOrganizationsByAnomalyId() {
        
        Organization org1 = new Organization();
        org1.setOrganizationName("TestOrg");
        organizationDao.addOrganization(org1);
        
        Organization org2 = new Organization();
        org2.setOrganizationName("TestOrgTwo");
        organizationDao.addOrganization(org2);
        
        Ability ab = new Ability();
        ab.setAbilityName("test");
        abilityDao.addAbility(ab);
        
        Anomaly an1 = new Anomaly();
        an1.setAnomalyName("Test Man");
        List<Ability> abs = new ArrayList<>();
        abs.add(ab);
        an1.setAbilities(abs);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org1);
        orgs.add(org2);
        an1.setOrganizations(orgs);
        
        anomalyDao.addAnomaly(an1);
        
        List<Organization> orgsTest;
        orgsTest = organizationDao.getOrganizationsByAnomalyId(an1.getAnomalyId());
        assertEquals(orgs, orgsTest);
    }
    
}
