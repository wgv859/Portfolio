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
public class LocationDaoTest {
    
    AbilityDao abilityDao;
    AnomalyDao anomalyDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;
    
    public LocationDaoTest() {
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
        locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocationId());
        
        assertEquals(location.getLocationName(), fromDao.getLocationName());
        assertEquals(location.getLocationDescription(), fromDao.getLocationDescription());
        assertEquals(location.getLocationStreet(), fromDao.getLocationStreet());
        assertEquals(location.getLocationCity(), fromDao.getLocationCity());
        assertEquals(location.getLocationState(), fromDao.getLocationState());
        assertEquals(location.getLocationZip(), fromDao.getLocationZip());
        assertEquals(location.getLocationLatitude(), fromDao.getLocationLatitude());
        assertEquals(location.getLocationLongitude(), fromDao.getLocationLongitude());
        assertEquals(location.getLocationId(), fromDao.getLocationId());
        
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
        locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocationId());
        
        assertEquals(location.getLocationName(), fromDao.getLocationName());
        assertEquals(location.getLocationDescription(), fromDao.getLocationDescription());
        assertEquals(location.getLocationStreet(), fromDao.getLocationStreet());
        assertEquals(location.getLocationCity(), fromDao.getLocationCity());
        assertEquals(location.getLocationState(), fromDao.getLocationState());
        assertEquals(location.getLocationZip(), fromDao.getLocationZip());
        assertEquals(location.getLocationLatitude(), fromDao.getLocationLatitude());
        assertEquals(location.getLocationLongitude(), fromDao.getLocationLongitude());
        assertEquals(location.getLocationId(), fromDao.getLocationId());
        
        locationDao.deleteLocation(location.getLocationId());
        assertNull(locationDao.getLocationById(location.getLocationId()));
        
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
        locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocationId());
        
        assertEquals(location.getLocationName(), fromDao.getLocationName());
        assertEquals(location.getLocationDescription(), fromDao.getLocationDescription());
        assertEquals(location.getLocationStreet(), fromDao.getLocationStreet());
        assertEquals(location.getLocationCity(), fromDao.getLocationCity());
        assertEquals(location.getLocationState(), fromDao.getLocationState());
        assertEquals(location.getLocationZip(), fromDao.getLocationZip());
        assertEquals(location.getLocationLatitude(), fromDao.getLocationLatitude());
        assertEquals(location.getLocationLongitude(), fromDao.getLocationLongitude());
        assertEquals(location.getLocationId(), fromDao.getLocationId());
        
        fromDao.setLocationName("The Software Guild");
        locationDao.updateLocation(fromDao);
        
        Location update = locationDao.getLocationById(location.getLocationId());
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
        locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocationId());
        
        Location location2 = new Location();
        location2.setLocationName("The YMCA");
        location2.setLocationDescription("In the weight room");
        location2.setLocationStreet("Beale");
        location2.setLocationCity("Memphis");
        location2.setLocationState("TN");
        location2.setLocationZip("11111");
        location2.setLocationLatitude(latitude);
        location2.setLocationLongitude(longitude);
        locationDao.addLocation(location2);
        
        assertEquals(location.getLocationName(), fromDao.getLocationName());
        assertEquals(location.getLocationDescription(), fromDao.getLocationDescription());
        assertEquals(location.getLocationStreet(), fromDao.getLocationStreet());
        assertEquals(location.getLocationCity(), fromDao.getLocationCity());
        assertEquals(location.getLocationState(), fromDao.getLocationState());
        assertEquals(location.getLocationZip(), fromDao.getLocationZip());
        assertEquals(location.getLocationLatitude(), fromDao.getLocationLatitude());
        assertEquals(location.getLocationLongitude(), fromDao.getLocationLongitude());
        assertEquals(location.getLocationId(), fromDao.getLocationId());
        
        List<Location> locs = locationDao.getAllLocations();
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
        
        List<Location> locationsDao = locationDao.getLocationsByAnomalyId(anomaly.getAnomalyId());
        
        assertEquals(1, locationsDao.size());
        
    }
    
    @Test
    public void testGetLocationBySightingId() {
        
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
        
        List<Location> testLoc = locationDao.GetLocationsBySightingId(sighting.getSightingId());
        
        assertEquals(1, testLoc.size());
        
    }
    
}
