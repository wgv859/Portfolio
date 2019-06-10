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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class AnomalyServiceLayerTest {
    
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
    
    public AnomalyServiceLayerTest() {
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
    public void testAddGetAnomaly() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        
        Ability abilityOne = new Ability();
        abilityOne.setAbilityName("Giggling");
        abilityOne.setAbilityDescription("The power of laughter");
        abilityService.addAbility(abilityOne);
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
        organizationService.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationService.addLocation(location);
        
        anomalyService.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingService.addSighting(sighting);
        
        Anomaly fromService = anomalyService.getAnomalyById(anomaly.getAnomalyId());
        assertEquals(anomaly, fromService);
        
    }
    
    @Test
    public void testAddGetDeleteAnaomaly() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        
        Ability abilityOne = new Ability();
        abilityOne.setAbilityName("Giggling");
        abilityOne.setAbilityDescription("The power of laughter");
        abilityService.addAbility(abilityOne);
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
        organizationService.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationService.addLocation(location);
        
        anomalyService.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingService.addSighting(sighting);
        
        Anomaly fromService = anomalyService.getAnomalyById(anomaly.getAnomalyId());
        assertEquals(anomaly, fromService);
        
        anomalyService.deleteAnomaly(anomaly.getAnomalyId());
        assertNull(anomalyService.getAnomalyById(anomaly.getAnomalyId()));
        
    }
    
    @Test
    public void testAddGetUpdateAnomaly() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        
        Ability abilityOne = new Ability();
        abilityOne.setAbilityName("Giggling");
        abilityOne.setAbilityDescription("The power of laughter");
        abilityService.addAbility(abilityOne);
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
        organizationService.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationService.addLocation(location);
        
        anomalyService.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingService.addSighting(sighting);
        
        Anomaly fromService = anomalyService.getAnomalyById(anomaly.getAnomalyId());
        assertEquals(anomaly, fromService);
        
        anomaly.setAnomalyName("Anomaly Dude");
        anomalyService.updateAnomaly(anomaly);
        
        fromService = anomalyService.getAnomalyById(anomaly.getAnomalyId());
        assertEquals(anomaly, fromService);
    }
    
    @Test
    public void testAddGetAnomaliesByLocationId() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        
        Ability abilityOne = new Ability();
        abilityOne.setAbilityName("Giggling");
        abilityOne.setAbilityDescription("The power of laughter");
        abilityService.addAbility(abilityOne);
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
        organizationService.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationService.addLocation(location);
        
        anomalyService.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingService.addSighting(sighting);
        
        List<Anomaly> fromService;
        fromService = anomalyService.getAnomaliesByLocationId(location.getLocationId());
        assertEquals(anomalies, fromService);
    }
    
    @Test
    public void testAddGetAnomaliesByOrganizationId() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        
        Ability abilityOne = new Ability();
        abilityOne.setAbilityName("Giggling");
        abilityOne.setAbilityDescription("The power of laughter");
        abilityService.addAbility(abilityOne);
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
        organizationService.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationService.addLocation(location);
        
        anomalyService.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingService.addSighting(sighting);
        
        List<Anomaly> fromDao;
        fromDao = anomalyService.getAnomaliesByOrganizationId(org.getOrganizationId());
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
        abilityService.addAbility(abilityOne);
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
        organizationService.addOrganization(org);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        
        anomaly.setOrganizations(orgs);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationService.addLocation(location);
        
        anomalyService.addAnomaly(anomaly);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        sighting.setAnomalies(anomalies);
        sightingService.addSighting(sighting);
        
        List<Anomaly> fromService;
        fromService = anomalyService.getAnomaliesByAbilityId(abilityOne.getAbilityId());
        assertEquals(anomalies, fromService);
    }
    
    @Test
    public void testAddGetAllAnomalies() {
        
        Ability a = new Ability();
        a.setAbilityName("Ability");
        a.setAbilityDescription("Amazing");
        abilityService.addAbility(a);
        
        Organization o = new Organization();
        o.setOrganizationName("test");
        o.setOrganizationDescription("test");
        o.setOrganizationStreet("test street");
        o.setOrganizationCity("test city");
        o.setOrganizationState("test state");
        o.setOrganizationZip("11111");
        o.setOrganizationPhone("1112223333");
        o.setOrganizationEmail("test@test.com");
        organizationService.addOrganization(o);
        
        Anomaly an = new Anomaly();
        an.setAnomalyName("Test Man");
        an.setAnomalyDescription("Test description");
        List<Ability> abilities = new ArrayList<>();
        abilities.add(a);
        an.setAbilities(abilities);
        List<Organization> orgs = new ArrayList<>();
        orgs.add(o);
        an.setOrganizations(orgs);
        
        anomalyService.addAnomaly(an);
        
        Anomaly an2 = new Anomaly();
        an2.setAnomalyName("Test Man Two");
        an2.setAnomalyDescription("Test Description");
        abilities.add(a);
        an2.setAbilities(abilities);
        orgs.add(o);
        an2.setOrganizations(orgs);
        
        anomalyService.addAnomaly(an2);
        
        assertEquals(2, anomalyService.getAllAnomalies().size());
        
    }
    
}
