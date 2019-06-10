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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class AnomalyDaoTest {
    
    AbilityDao abilityDao;
    AnomalyDao anomalyDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    
    public AnomalyDaoTest() {
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
    public void testAddGetAnomaly() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        
        Ability abilityOne = new Ability();
        abilityOne.setAbilityName("Giggling");
        abilityOne.setAbilityDescription("The power of laughter");
        abilityDao.addAbility(abilityOne);
        List<Ability> abilities = new ArrayList<>();
        abilities.add(abilityOne);
        
        anomaly.setAbilities(abilities);
        
        Organization org = new Organization();
        org.setOrganizationName("YMCA");
        org.setOrganizationDescription("Its a YMCA");
        org.setOrganizationStreet("Beale St.");
        org.setOrganizationCity("Memphis");
        org.setOrganizationState("Tennessee");
        org.setOrganizationZip("38111");
        org.setOrganizationPhone("9013276933");
        org.setOrganizationEmail("aMan@anomaly.com");
        organizationDao.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationDao.addLocation(location);
        
        anomalyDao.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingDao.addSighting(sighting);
        
        Anomaly fromDao = anomalyDao.getAnomalyById(anomaly.getAnomalyId());
        assertEquals(anomaly, fromDao);
        
    }
    
    @Test
    public void testAddGetDeleteAnaomaly() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        
        Ability abilityOne = new Ability();
        abilityOne.setAbilityName("Giggling");
        abilityOne.setAbilityDescription("The power of laughter");
        abilityDao.addAbility(abilityOne);
        List<Ability> abilities = new ArrayList<>();
        abilities.add(abilityOne);
        
        anomaly.setAbilities(abilities);
        
        Organization org = new Organization();
        org.setOrganizationName("YMCA");
        org.setOrganizationDescription("Its a YMCA");
        org.setOrganizationStreet("Beale St.");
        org.setOrganizationCity("Memphis");
        org.setOrganizationState("Tennessee");
        org.setOrganizationZip("38111");
        org.setOrganizationPhone("9013276933");
        org.setOrganizationEmail("aMan@anomaly.com");
        organizationDao.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationDao.addLocation(location);
        
        anomalyDao.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingDao.addSighting(sighting);
        
        Anomaly fromDao = anomalyDao.getAnomalyById(anomaly.getAnomalyId());
        assertEquals(anomaly, fromDao);
        
        anomalyDao.deleteAnomaly(anomaly.getAnomalyId());
        assertNull(anomalyDao.getAnomalyById(anomaly.getAnomalyId()));
        
    }
    
    @Test
    public void testAddGetUpdateAnomaly() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        
        Ability abilityOne = new Ability();
        abilityOne.setAbilityName("Giggling");
        abilityOne.setAbilityDescription("The power of laughter");
        abilityDao.addAbility(abilityOne);
        List<Ability> abilities = new ArrayList<>();
        abilities.add(abilityOne);
        
        anomaly.setAbilities(abilities);
        
        Organization org = new Organization();
        org.setOrganizationName("YMCA");
        org.setOrganizationDescription("Its a YMCA");
        org.setOrganizationStreet("Beale St.");
        org.setOrganizationCity("Memphis");
        org.setOrganizationState("Tennessee");
        org.setOrganizationZip("38111");
        org.setOrganizationPhone("9013276933");
        org.setOrganizationEmail("aMan@anomaly.com");
        organizationDao.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationDao.addLocation(location);
        
        anomalyDao.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingDao.addSighting(sighting);
        
        Anomaly fromDao = anomalyDao.getAnomalyById(anomaly.getAnomalyId());
        assertEquals(anomaly, fromDao);
        
        anomaly.setAnomalyName("Anomaly Dude");
        anomalyDao.updateAnomaly(anomaly);
        
        fromDao = anomalyDao.getAnomalyById(anomaly.getAnomalyId());
        assertEquals(anomaly, fromDao);
    }
    
    @Test
    public void testAddGetAnomaliesByLocationId() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        
        Ability abilityOne = new Ability();
        abilityOne.setAbilityName("Giggling");
        abilityOne.setAbilityDescription("The power of laughter");
        abilityDao.addAbility(abilityOne);
        List<Ability> abilities = new ArrayList<>();
        abilities.add(abilityOne);
        
        anomaly.setAbilities(abilities);
        
        Organization org = new Organization();
        org.setOrganizationName("YMCA");
        org.setOrganizationDescription("Its a YMCA");
        org.setOrganizationStreet("Beale St.");
        org.setOrganizationCity("Memphis");
        org.setOrganizationState("Tennessee");
        org.setOrganizationZip("38111");
        org.setOrganizationPhone("9013276933");
        org.setOrganizationEmail("aMan@anomaly.com");
        organizationDao.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationDao.addLocation(location);
        
        anomalyDao.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingDao.addSighting(sighting);
        
        List<Anomaly> fromDao;
        fromDao = anomalyDao.getAnomaliesByLocationId(location.getLocationId());
        assertEquals(anomalies, fromDao);
    }
    
    @Test
    public void testAddGetAnomaliesByOrganizationId() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        
        Ability abilityOne = new Ability();
        abilityOne.setAbilityName("Giggling");
        abilityOne.setAbilityDescription("The power of laughter");
        abilityDao.addAbility(abilityOne);
        List<Ability> abilities = new ArrayList<>();
        abilities.add(abilityOne);
        
        anomaly.setAbilities(abilities);
        
        Organization org = new Organization();
        org.setOrganizationName("YMCA");
        org.setOrganizationDescription("Its a YMCA");
        org.setOrganizationStreet("Beale St.");
        org.setOrganizationCity("Memphis");
        org.setOrganizationState("Tennessee");
        org.setOrganizationZip("38111");
        org.setOrganizationPhone("9013276933");
        org.setOrganizationEmail("aMan@anomaly.com");
        organizationDao.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationDao.addLocation(location);
        
        anomalyDao.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingDao.addSighting(sighting);
        
        List<Anomaly> fromDao;
        fromDao = anomalyDao.getAnomaliesByOrganizationId(org.getOrganizationId());
        assertEquals(anomalies, fromDao);
    }
    
    @Test
    public void testAddGetAnomaliesByAbilityId() {
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        
        Ability abilityOne = new Ability();
        abilityOne.setAbilityName("Giggling");
        abilityOne.setAbilityDescription("The power of laughter");
        abilityDao.addAbility(abilityOne);
        List<Ability> abilities = new ArrayList<>();
        abilities.add(abilityOne);
        
        anomaly.setAbilities(abilities);
        
        Organization org = new Organization();
        org.setOrganizationName("YMCA");
        org.setOrganizationDescription("Its a YMCA");
        org.setOrganizationStreet("Beale St.");
        org.setOrganizationCity("Memphis");
        org.setOrganizationState("Tennessee");
        org.setOrganizationZip("38111");
        org.setOrganizationPhone("9013276933");
        org.setOrganizationEmail("aMan@anomaly.com");
        organizationDao.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationDao.addLocation(location);
        
        anomalyDao.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingDao.addSighting(sighting);
        
        List<Anomaly> fromDao;
        fromDao = anomalyDao.getAnomaliesByAbilityId(abilityOne.getAbilityId());
        assertEquals(anomalies, fromDao);
    }
    
    @Test
    public void testAddGetAllAnomalies() {
        
        Ability a = new Ability();
        a.setAbilityName("Ability");
        a.setAbilityDescription("Amazing");
        abilityDao.addAbility(a);
        
        Organization o = new Organization();
        o.setOrganizationName("test");
        o.setOrganizationDescription("test");
        o.setOrganizationStreet("test street");
        o.setOrganizationCity("test city");
        o.setOrganizationState("test state");
        o.setOrganizationZip("11111");
        o.setOrganizationPhone("1112223333");
        o.setOrganizationEmail("test@test.com");
        organizationDao.addOrganization(o);
        
        Anomaly an = new Anomaly();
        an.setAnomalyName("Test Man");
        an.setAnomalyDescription("Test description");
        List<Ability> abilities = new ArrayList<>();
        abilities.add(a);
        an.setAbilities(abilities);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(o);
        an.setOrganizations(orgs);
        
        anomalyDao.addAnomaly(an);
        
        Anomaly an2 = new Anomaly();
        an2.setAnomalyName("Test Man Two");
        an2.setAnomalyDescription("Test Description");
        abilities.add(a);
        an2.setAbilities(abilities);
        orgs.add(o);
        an2.setOrganizations(orgs);
        
        anomalyDao.addAnomaly(an2);
        
        assertEquals(2, anomalyDao.getAllAnomalies().size());
        
    }
    
}
