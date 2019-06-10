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
public class AbilityDaoTest {
    
    AbilityDao abilityDao;
    AnomalyDao anomalyDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    
    public AbilityDaoTest() {
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
    
    @Test
    public void testAddGetAbility() {
        
        Ability ability = new Ability();
        abilityDao.addAbility(ability);
        
        Ability fromDao = abilityDao.getAbilityById(ability.getAbilityId());
        
        assertEquals(ability, fromDao);
    }
    
    @Test
    public void testAddGetDeleteAbility() {
        
        Ability ability = new Ability();
        abilityDao.addAbility(ability);
        
        Ability fromDao = abilityDao.getAbilityById(ability.getAbilityId());
        
        assertEquals(ability, fromDao);
        
        abilityDao.deleteAbility(ability.getAbilityId());
        assertNull(abilityDao.getAbilityById(ability.getAbilityId()));
    }
    
    @Test
    public void testAddGetUpdateAbility() {
        
        Ability ability = new Ability();
        abilityDao.addAbility(ability);
        
        Ability fromDao = abilityDao.getAbilityById(ability.getAbilityId());
        
        assertEquals(ability, fromDao);
        
        ability.setAbilityDescription("test description");
        abilityDao.updateAbility(ability);
        
        fromDao = abilityDao.getAbilityById(ability.getAbilityId());
        assertEquals(ability, fromDao);
    }
    
    @Test
    public void testAddGetAllAbilities() {
        
        Ability a1 = new Ability();
        a1.setAbilityName("Test");
        abilityDao.addAbility(a1);
        
        Ability a2 = new Ability();
        a2.setAbilityName("TestTwo");
        abilityDao.addAbility(a2);
        
        List<Ability> abilities = abilityDao.getAllAbilities();
        assertEquals(2, abilities.size());
        
    }
    
    @Test
    public void testGetAbilitiesByAnomalyId() {
        Ability a1 = new Ability();
        a1.setAbilityName("Test");
        abilityDao.addAbility(a1);
        
        Ability a2 = new Ability();
        a2.setAbilityName("TestTwo");
        abilityDao.addAbility(a2);
        
        Organization org = new Organization();
        org.setOrganizationName("TestOrgName");
        organizationDao.addOrganization(org);
        
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
        
        anomalyDao.addAnomaly(an1);
        anomalyDao.addAnomaly(an2);
        
        List <Ability> abilitiesThree;
        abilitiesThree = abilityDao.getAbilitiesByAnomalyId(an2.getAnomalyId());
        assertEquals(abilitiesTwo, abilitiesThree);
       
    }
    
}
