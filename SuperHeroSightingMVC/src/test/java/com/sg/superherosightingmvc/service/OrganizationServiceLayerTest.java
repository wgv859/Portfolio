/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.service;

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
public class OrganizationServiceLayerTest {
    
//    AbilityDao abilityDao;
    AbilityServiceLayer abilityService;
//    AnomalyDao anomalyDao;
    AnomalyServiceLayer anomalyService;
//    LocationDao locationDao;
    LocationServiceLayer locationService;
//    OrganizationDao organizationDao;
    OrganizationServiceLayer organizationService;
//    SightingDao sightingDao;
    SightingServiceLayer sightingService;
    
    public OrganizationServiceLayerTest() {
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
        
//        abilityDao = ctx.getBean("AbilityDao", AbilityDao.class);
        abilityService = ctx.getBean("AbilityService", AbilityServiceLayer.class);
//        anomalyDao = ctx.getBean("AnomalyDao", AnomalyDao.class);
        anomalyService = ctx.getBean("AnomalyService", AnomalyServiceLayer.class);
//        locationDao = ctx.getBean("LocationDao", LocationDao.class);
        locationService = ctx.getBean("LocationService", LocationServiceLayer.class);
//        organizationDao = ctx.getBean("OrganizationDao", OrganizationDao.class);
        organizationService = ctx.getBean("OrganizationService", OrganizationServiceLayer.class);
//        sightingDao = ctx.getBean("SightingDao", SightingDao.class);
        sightingService = ctx.getBean("SightingService", SightingServiceLayer.class);
        
        List<Ability> abilities = abilityService.getAllAbilities();
        for(Ability current : abilities) {
            abilityService.deleteAbility(current.getAbilityId());
        }
        
        List<Anomaly> anomalies = anomalyService.getAllAnomalies();
        for(Anomaly current : anomalies)  {
            anomalyService.deleteAnomaly(current.getAnomalyId());
        }
        
        List<Location> locations = locationService.getAllLocations();
        for(Location current : locations) {
            locationService.deleteLocation(current.getLocationId());
        }
        
        List<Organization> organizations = organizationService.getAllOrganizations();
        for(Organization current : organizations) {
            organizationService.deleteOrganization(current.getOrganizationId());
        }
        
        List<Sighting> sightings = sightingService.getAllSightings();
        for(Sighting current : sightings) {
            sightingService.deleteSighting(current.getSightingId());
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
        
        organizationService.addOrganization(org);
        
        Organization fromService = organizationService.getOrganizationById(org.getOrganizationId());
        
        assertEquals(fromService, org);
        
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
        
        organizationService.addOrganization(org);
        
        Organization fromService = organizationService.getOrganizationById(org.getOrganizationId());
        
        assertEquals(fromService, org);
        
        organizationService.deleteOrganization(org.getOrganizationId());
        assertNull(organizationService.getOrganizationById(org.getOrganizationId()));
        
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
        
        organizationService.addOrganization(org);
        
        Organization fromService = organizationService.getOrganizationById(org.getOrganizationId());
        
        assertEquals(fromService, org);
        
        org.setOrganizationName("Updated Org Name");
        
        organizationService.updateOrganization(org);
        
        fromService = organizationService.getOrganizationById(org.getOrganizationId());
        
        assertEquals(org, fromService);
        
    }
    
    @Test
    public void testAddGetOrganizationById() {
        Organization org = new Organization();
        organizationService.addOrganization(org);
        
        Organization fromService = organizationService.getOrganizationById(org.getOrganizationId());
        assertEquals(org, fromService);
    }
    
    @Test
    public void testGetAllOrganizations() {
        Organization org = new Organization();
        organizationService.addOrganization(org);
        
        Organization org2 = new Organization();
        organizationService.addOrganization(org2);
        
        List<Organization> orgs = organizationService.getAllOrganizations();
        assertEquals(2, orgs.size());
    }
    
    @Test
    public void testGetAllOrganizationsByAnomalyId() {
        
        Organization org1 = new Organization();
        org1.setOrganizationName("TestOrg");
        organizationService.addOrganization(org1);
        
        Organization org2 = new Organization();
        org2.setOrganizationName("TestOrgTwo");
        organizationService.addOrganization(org2);
        
        Ability ab = new Ability();
        ab.setAbilityName("test");
        abilityService.addAbility(ab);
        
        Anomaly an1 = new Anomaly();
        an1.setAnomalyName("Test Man");
        List<Ability> abs = new ArrayList<>();
        abs.add(ab);
        an1.setAbilities(abs);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org1);
        orgs.add(org2);
        an1.setOrganizations(orgs);
        
        anomalyService.addAnomaly(an1);
        
        List<Organization> orgsTest;
        orgsTest = organizationService.getOrganizationsByAnomalyId(an1.getAnomalyId());
        assertEquals(orgs, orgsTest);
    }

}
