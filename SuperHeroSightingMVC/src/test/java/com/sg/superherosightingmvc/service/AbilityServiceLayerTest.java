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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author wgv85
 */
public class AbilityServiceLayerTest {
    
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
    
    public AbilityServiceLayerTest() {
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

    /**
     * Test of addAbility method, of class AbilityServiceLayer.
     */
    @Test
    public void testAddAbility() {
        
        Ability ability = new Ability();
        abilityService.addAbility(ability);
        
        Ability fromService = abilityService.getAbilityById(ability.getAbilityId());
        
        assertEquals(fromService, ability);
        
    }

    /**
     * Test of deleteAbility method, of class AbilityServiceLayer.
     */
    @Test
    public void testDeleteAbility() {
        
        Ability ability = new Ability();
        abilityService.addAbility(ability);
        
        Ability fromService = abilityService.getAbilityById(ability.getAbilityId());
        
        assertEquals(ability, fromService);
        
        abilityService.deleteAbility(ability.getAbilityId());
        assertNull(abilityService.getAbilityById(ability.getAbilityId()));
        
    }

    /**
     * Test of updateAbility method, of class AbilityServiceLayer.
     */
    @Test
    public void testUpdateAbility() {
        
        Ability ability = new Ability();
        abilityService.addAbility(ability);
        
        Ability fromService = abilityService.getAbilityById(ability.getAbilityId());
        
        assertEquals(ability, fromService);
        
        ability.setAbilityDescription("test description");
        abilityService.updateAbility(ability);
        
        fromService = abilityService.getAbilityById(ability.getAbilityId());
        assertEquals(ability, fromService);
        
    }

    /**
     * Test of getAllAbilities method, of class AbilityServiceLayer.
     */
    @Test
    public void testGetAllAbilities() {
        
        Ability a1 = new Ability();
        a1.setAbilityName("Test");
        abilityService.addAbility(a1);
        
        Ability a2 = new Ability();
        a2.setAbilityName("TestTwo");
        abilityService.addAbility(a2);
        
        List<Ability> abilities = abilityService.getAllAbilities();
        
        assertEquals(2, abilities.size());
        
    }

    /**
     * Test of getAbilitiesByAnomalyId method, of class AbilityServiceLayer.
     */
    @Test
    public void testGetAbilitiesByAnomalyId() {
        
        Ability a1 = new Ability();
        a1.setAbilityName("Test");
        abilityService.addAbility(a1);
        
        Ability a2 = new Ability();
        a2.setAbilityName("TestTwo");
        abilityService.addAbility(a2);
        
        Organization org = new Organization();
        org.setOrganizationName("TestOrgName");
        organizationService.addOrganization(org);
        
        Anomaly an1 = new Anomaly();
        an1.setAnomalyName("Test Man");
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        an1.setOrganizations(orgs);
        List<Ability> abilitiesOne = new ArrayList<>();
        abilitiesOne.add(a2);
        an1.setAbilities(abilitiesOne);
        
        Anomaly an2 = new Anomaly();
        an2.setAnomalyName("Test Man Two");
        List<Ability> abilitiesTwo = new ArrayList<>();
        abilitiesTwo.add(a1);
        an2.setAbilities(abilitiesTwo);
        an2.setOrganizations(orgs);
        
        anomalyService.addAnomaly(an1);
        anomalyService.addAnomaly(an2);
        
        List <Ability> abilitiesThree;
        abilitiesThree = abilityService.getAbilitiesByAnomalyId(an2.getAnomalyId());
        assertEquals(abilitiesTwo, abilitiesThree);
        
    }
    
}
