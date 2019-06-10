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
import java.math.BigDecimal;
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
public class LocationServiceLayerTest {
    
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
    
    public LocationServiceLayerTest() {
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
    public void testAddGetLocation() {
        
        BigDecimal latitude = new BigDecimal("11.111111");
        BigDecimal longitude = new BigDecimal("222.222222");
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationStreet("Beale");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        location.setLocationZip("11111");
        location.setLocationLatitude(latitude);
        location.setLocationLongitude(longitude);
        locationService.addLocation(location);
        
        Location fromService = locationService.getLocationById(location.getLocationId());
        
        assertEquals(location.getLocationName(), fromService.getLocationName());
        assertEquals(location.getLocationDescription(), fromService.getLocationDescription());
        assertEquals(location.getLocationStreet(), fromService.getLocationStreet());
        assertEquals(location.getLocationCity(), fromService.getLocationCity());
        assertEquals(location.getLocationState(), fromService.getLocationState());
        assertEquals(location.getLocationZip(), fromService.getLocationZip());
        assertEquals(location.getLocationLatitude(), fromService.getLocationLatitude());
        assertEquals(location.getLocationLongitude(), fromService.getLocationLongitude());
        assertEquals(location.getLocationId(), fromService.getLocationId());
        
    }
    
    @Test
    public void testAddGetDeleteLocation() {
        
        BigDecimal latitude = new BigDecimal("11.111111");
        BigDecimal longitude = new BigDecimal("222.222222");
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationStreet("Beale");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        location.setLocationZip("11111");
        location.setLocationLatitude(latitude);
        location.setLocationLongitude(longitude);
        locationService.addLocation(location);
        
        Location fromService = locationService.getLocationById(location.getLocationId());
        
        assertEquals(location.getLocationName(), fromService.getLocationName());
        assertEquals(location.getLocationDescription(), fromService.getLocationDescription());
        assertEquals(location.getLocationStreet(), fromService.getLocationStreet());
        assertEquals(location.getLocationCity(), fromService.getLocationCity());
        assertEquals(location.getLocationState(), fromService.getLocationState());
        assertEquals(location.getLocationZip(), fromService.getLocationZip());
        assertEquals(location.getLocationLatitude(), fromService.getLocationLatitude());
        assertEquals(location.getLocationLongitude(), fromService.getLocationLongitude());
        assertEquals(location.getLocationId(), fromService.getLocationId());
        
        locationService.deleteLocation(location.getLocationId());
        assertNull(locationService.getLocationById(location.getLocationId()));
        
    }
    
    @Test
    public void testAddGetUpdateLocation() {
        
        BigDecimal latitude = new BigDecimal("11.111111");
        BigDecimal longitude = new BigDecimal("222.222222");
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationStreet("Beale");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        location.setLocationZip("11111");
        location.setLocationLatitude(latitude);
        location.setLocationLongitude(longitude);
        locationService.addLocation(location);
        
        Location fromService = locationService.getLocationById(location.getLocationId());
        
        assertEquals(location.getLocationName(), fromService.getLocationName());
        assertEquals(location.getLocationDescription(), fromService.getLocationDescription());
        assertEquals(location.getLocationStreet(), fromService.getLocationStreet());
        assertEquals(location.getLocationCity(), fromService.getLocationCity());
        assertEquals(location.getLocationState(), fromService.getLocationState());
        assertEquals(location.getLocationZip(), fromService.getLocationZip());
        assertEquals(location.getLocationLatitude(), fromService.getLocationLatitude());
        assertEquals(location.getLocationLongitude(), fromService.getLocationLongitude());
        assertEquals(location.getLocationId(), fromService.getLocationId());
        
        fromService.setLocationName("The Software Guild");
        locationService.updateLocation(fromService);
        
        Location update = locationService.getLocationById(location.getLocationId());
        assertEquals(update.getLocationName(), "The Software Guild");
        
        
    }
    
    @Test
    public void testGetAllLocations() {
        
        BigDecimal latitude = new BigDecimal("11.111111");
        BigDecimal longitude = new BigDecimal("222.222222");
        
        Location location = new Location();
        location.setLocationName("The YMCA");
        location.setLocationDescription("In the weight room");
        location.setLocationStreet("Beale");
        location.setLocationCity("Memphis");
        location.setLocationState("TN");
        location.setLocationZip("11111");
        location.setLocationLatitude(latitude);
        location.setLocationLongitude(longitude);
        locationService.addLocation(location);
        
        Location fromService = locationService.getLocationById(location.getLocationId());
        
        Location location2 = new Location();
        location2.setLocationName("The YMCA");
        location2.setLocationDescription("In the weight room");
        location2.setLocationStreet("Beale");
        location2.setLocationCity("Memphis");
        location2.setLocationState("TN");
        location2.setLocationZip("11111");
        location2.setLocationLatitude(latitude);
        location2.setLocationLongitude(longitude);
        locationService.addLocation(location2);
        
        assertEquals(location.getLocationName(), fromService.getLocationName());
        assertEquals(location.getLocationDescription(), fromService.getLocationDescription());
        assertEquals(location.getLocationStreet(), fromService.getLocationStreet());
        assertEquals(location.getLocationCity(), fromService.getLocationCity());
        assertEquals(location.getLocationState(), fromService.getLocationState());
        assertEquals(location.getLocationZip(), fromService.getLocationZip());
        assertEquals(location.getLocationLatitude(), fromService.getLocationLatitude());
        assertEquals(location.getLocationLongitude(), fromService.getLocationLongitude());
        assertEquals(location.getLocationId(), fromService.getLocationId());
        
        List<Location> locs = locationService.getAllLocations();
        assertEquals(2, locs.size());
        
    }
    
    @Test
    public void testAddGetLocationsByAnomalyId() {
        
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
        
        List<Location> locationsService = locationService.getLocationsByAnomalyId(anomaly.getAnomalyId());
        
        assertEquals(1, locationsService.size());
        
    }
    
    @Test
    public void testGetLocationBySightingId() {
        
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
        
        List<Location> testLoc = locationService.GetLocationsBySightingId(sighting.getSightingId());
        
        assertEquals(1, testLoc.size());
        
    }
    
    
}
