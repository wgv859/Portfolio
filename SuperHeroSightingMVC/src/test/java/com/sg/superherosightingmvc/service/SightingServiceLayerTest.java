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
public class SightingServiceLayerTest {
    
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
    
    public SightingServiceLayerTest() {
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
    public void testAddGetSighting() {
        
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
        
        Sighting fromService = sightingService.getSightingById(sighting.getSightingId());
        
        assertEquals(fromService, sighting);
        
    }
    
    @Test
    public void testAddGetDeleteSighting() {
        
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
        
        Sighting fromService = sightingService.getSightingById(sighting.getSightingId());
        
        assertEquals(fromService, sighting);
        
        sightingService.deleteSighting(sighting.getSightingId());
        assertNull(sightingService.getSightingById(sighting.getSightingId()));
        
    }
    
    @Test
    public void testGetAddUpdateSighting() {
        
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
        
        sighting.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingService.updateSighting(sighting);
        
        Sighting fromService = sightingService.getSightingById(sighting.getSightingId());
        
        assertEquals(fromService.getSightingDate(), sighting.getSightingDate());
        
    }
    
    @Test
    public void testAddGetAllSightings() {
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        
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
        
        Sighting sightingOne  = new Sighting();
        sightingOne.setLocation(location);
        sightingOne.setAnomalies(anomalies);
        sightingOne.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sightingService.addSighting(sightingOne);
        
        Sighting sightingTwo = new Sighting();
        sightingTwo.setLocation(location);
        sightingTwo.setAnomalies(anomalies);
        sightingTwo.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingService.addSighting(sightingTwo);
        
        assertEquals(2, sightingService.getAllSightings().size());
        
    }
    
    @Test
    public void testGetAllSightingsByLocation() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        
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
        
        Location locationOne = new Location();
        locationOne.setLocationName("LocationOne");
        locationOne.setLocationDescription("TEST");
        locationOne.setLocationStreet("TEST");
        locationOne.setLocationCity("CITY");
        locationOne.setLocationState("STATE");
        locationOne.setLocationZip("11111");
        locationService.addLocation(locationOne);
        
        Location locationTwo = new Location();
        locationTwo.setLocationName("LocationTwo");
        locationTwo.setLocationDescription("Test");
        locationTwo.setLocationStreet("test");
        locationTwo.setLocationCity("CITY");
        locationTwo.setLocationState("STATE");
        locationTwo.setLocationZip("11111");
        locationService.addLocation(locationTwo);
        
        Sighting sightingOne  = new Sighting();
        sightingOne.setLocation(locationOne);
        sightingOne.setAnomalies(anomalies);
        sightingOne.setSightingDate(LocalDate.parse("2030-01-01", DateTimeFormatter.ISO_DATE));
        sightingService.addSighting(sightingOne);
        
        Sighting sightingTwo = new Sighting();
        sightingTwo.setLocation(locationTwo);
        sightingTwo.setAnomalies(anomalies);
        sightingTwo.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingService.addSighting(sightingTwo);
        
        Sighting sightingThree = new Sighting();
        sightingThree.setLocation(locationTwo);
        sightingThree.setAnomalies(anomalies);
        sightingThree.setSightingDate(LocalDate.parse("2020-03-01", DateTimeFormatter.ISO_DATE));
        sightingService.addSighting(sightingThree);
        
        assertEquals(2, sightingService.getSightingsByLocationId(locationTwo.getLocationId()).size());
        
    }
    
    @Test
    public void getAllSightingByAnomalyId() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        
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
        
        anomalyService.addAnomaly(anomaly);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationService.addLocation(location);
        
        Location locationTwo = new Location();
        locationTwo.setLocationName("The Lake");
        locationTwo.setLocationDescription("water water");
        locationTwo.setLocationCity("City");
        locationTwo.setLocationState("KY");
        locationService.addLocation(locationTwo);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sighting.setAnomalies(anomalies);
        sightingService.addSighting(sighting);
        
        Sighting sightingTwo = new Sighting();
        sightingTwo.setSightingDate(LocalDate.parse("2020-02-01", DateTimeFormatter.ISO_DATE));
        sightingTwo.setLocation(locationTwo);
        sightingTwo.setAnomalies(anomalies);
        sightingService.addSighting(sightingTwo);
        
        Sighting sightingThree = new Sighting();
        sightingThree.setSightingDate(LocalDate.parse("2020-03-01", DateTimeFormatter.ISO_DATE));
        sightingThree.setLocation(locationTwo);
        sightingThree.setAnomalies(anomalies);
        sightingService.addSighting(sightingThree);
        
        assertEquals(3,sightingService.getSightingsByAnomalyId(anomaly.getAnomalyId()).size());
    }
    
    @Test
    public void testGetAllSightingsByByDate() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        
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
        
        anomalyService.addAnomaly(anomaly);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationService.addLocation(location);
        
        Location locationTwo = new Location();
        locationTwo.setLocationName("The Lake");
        locationTwo.setLocationDescription("water water");
        locationTwo.setLocationCity("City");
        locationTwo.setLocationState("KY");
        locationService.addLocation(locationTwo);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sighting.setAnomalies(anomalies);
        sightingService.addSighting(sighting);
        
        Sighting sightingTwo = new Sighting();
        sightingTwo.setSightingDate(LocalDate.parse("2020-02-01", DateTimeFormatter.ISO_DATE));
        sightingTwo.setLocation(locationTwo);
        sightingTwo.setAnomalies(anomalies);
        sightingService.addSighting(sightingTwo);
        
        Sighting sightingThree = new Sighting();
        sightingThree.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingThree.setLocation(locationTwo);
        sightingThree.setAnomalies(anomalies);
        sightingService.addSighting(sightingThree);
        
        assertEquals(2, sightingService.getAllSightingsByDate(sightingThree.getSightingDate()).size());
        
    }
    
    @Test
    public void testGetLastTenSighting() {
        
        Anomaly anomaly = new Anomaly();
        anomaly.setAnomalyName("Anomaly Man");
        anomaly.setAnomalyDescription("Dark and foreboding");
        List<Anomaly> anomalies = new ArrayList<>();
        anomalies.add(anomaly);
        
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
        
        anomalyService.addAnomaly(anomaly);
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        locationService.addLocation(location);
        
        Location locationTwo = new Location();
        locationTwo.setLocationName("The Lake");
        locationTwo.setLocationDescription("water water");
        locationTwo.setLocationCity("City");
        locationTwo.setLocationState("KY");
        locationService.addLocation(locationTwo);
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sighting.setAnomalies(anomalies);
        sightingService.addSighting(sighting);
        
        Sighting sightingTwo = new Sighting();
        sightingTwo.setSightingDate(LocalDate.parse("2020-02-01", DateTimeFormatter.ISO_DATE));
        sightingTwo.setLocation(locationTwo);
        sightingTwo.setAnomalies(anomalies);
        sightingService.addSighting(sightingTwo);
        
        Sighting sightingThree = new Sighting();
        sightingThree.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingThree.setLocation(locationTwo);
        sightingThree.setAnomalies(anomalies);
        sightingService.addSighting(sightingThree);
        
        Sighting sightingFour = new Sighting();
        sightingFour.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingFour.setLocation(locationTwo);
        sightingFour.setAnomalies(anomalies);
        sightingService.addSighting(sightingFour);
        
        Sighting sightingFive = new Sighting();
        sightingFive.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingFive.setLocation(locationTwo);
        sightingFive.setAnomalies(anomalies);
        sightingService.addSighting(sightingFive);
        
        Sighting sightingSix = new Sighting();
        sightingSix.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingSix.setLocation(locationTwo);
        sightingSix.setAnomalies(anomalies);
        sightingService.addSighting(sightingSix);
        
        Sighting sightingSeven = new Sighting();
        sightingSeven.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingSeven.setLocation(locationTwo);
        sightingSeven.setAnomalies(anomalies);
        sightingService.addSighting(sightingSeven);
        
        Sighting sightingEight = new Sighting();
        sightingEight.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingEight.setLocation(locationTwo);
        sightingEight.setAnomalies(anomalies);
        sightingService.addSighting(sightingEight);
        
        Sighting sightingNine = new Sighting();
        sightingNine.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingNine.setLocation(locationTwo);
        sightingNine.setAnomalies(anomalies);
        sightingService.addSighting(sightingNine);
        
        Sighting sightingTen = new Sighting();
        sightingTen.setSightingDate(LocalDate.parse("2020-01-01", DateTimeFormatter.ISO_DATE));
        sightingTen.setLocation(locationTwo);
        sightingTen.setAnomalies(anomalies);
        sightingService.addSighting(sightingTen);
        
        assertEquals(10, sightingService.getLastTenSightings().size());
        
    }
    
}
