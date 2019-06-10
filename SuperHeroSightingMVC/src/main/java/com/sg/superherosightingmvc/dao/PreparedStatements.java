/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.dao;

/**
 *
 * @author wgv85
 */
public class PreparedStatements {
    
    // SQL prepared statement to query the database for the id that was last assigned to a row in the database.
    // Useful in DAO implementation methods found after SQL prepared statements.
    protected static final String SQL_SELECT_LAST_INSERT_ID
            = "select LAST_INSERT_ID()";
    

        // ====================================
        // === DAO SQL PREPARED STATEMENTS  ===
        // ====================================

// ================================================    
// Anomaly CRUD SQL prepared statements
// ================================================

    
    protected static final String SQL_INSERT_ANOMALY
            = "insert into Anomaly (AnomalyName, AnomalyDescription) "
            + "values (?, ?)";
    
    protected static final String SQL_DELETE_ANOMALY
            = "delete from Anomaly where AnomalyId = ?";
    
    protected static final String SQL_UPDATE_ANOMALY
            = "update Anomaly set AnomalyName = ?, AnomalyDescription = ? where AnomalyId = ?";
    
    protected static final String SQL_SELECT_ANOMALY_BY_ID
            = "select * from Anomaly where AnomalyId = ?";
    
    protected static final String SQL_SELECT_ALL_ANOMALIES
            = "select * from Anomaly";
    

// ================================================    
// Ability CRUD SQL prepared statements
// ================================================
    
    protected static final String SQL_INSERT_ABILITY
            = "insert into Ability (AbilityName, AbilityDescription) "
            + "values (?, ?)";
    
    protected static final String SQL_DELETE_ABILITY
            = "delete from Ability where AbilityId = ?";
    
    protected static final String SQL_UPDATE_ABILITY
            = "update Ability set AbilityName = ?, AbilityDescription = ? where AbilityId = ?";
    
    protected static final String SQL_SELECT_ABILITY_BY_ID
            = "select * from Ability where AbilityId = ?";
    
    protected static final String SQL_SELECT_ALL_ABILITIES
            = "select * from Ability";
    

// ================================================    
// Organization CRUD SQL prepared statements
// ================================================
    
    
    protected static final String SQL_INSERT_ORGANIZATION
            = "insert into Organization (OrganizationName, OrganizationDescription, OrganizationStreet, "
            + "OrganizationCity, OrganizationState, OrganizationZip, OrganizationPhone, OrganizationEmail) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?)";
    
    protected static final String SQL_DELETE_ORGANIZATION
            = "delete from Organization where OrganizationId = ?";
    
    protected static final String SQL_UPDATE_ORGANIZATION
            = "update Organization set OrganizationName = ?, OrganizationDescription = ?, OrganizationStreet = ?, "
            + "OrganizationCity = ?, OrganizationState = ?, OrganizationZip = ?, OrganizationPhone = ?, OrganizationEmail = ? "
            + "where OrganizationId = ?";
    
    protected static final String SQL_SELECT_ORGANIZATION_BY_ID
            = "select * from Organization where OrganizationId = ?";
    
    protected static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from Organization";

    
// ================================================
// Sighting CRUD SQL prepared statements
// ================================================
    
    
    protected static final String SQL_INSERT_SIGHTING
            = "insert into Sighting (SightingDate, LocationId) "
            + "values (?, ?)";
    
    protected static final String SQL_DELETE_SIGHTING
            = "delete from Sighting where SightingId = ?";
    
    protected static final String SQL_DELETE_SIGHTING_BY_LOCATION_ID
            = "delete from Sighting where Sighting.LocationId = ?";
    
    protected static final String SQL_UPDATE_SIGHTING
            = "update Sighting set SightingDate = ?, LocationId = ? where SightingId = ?";
    
    protected static final String SQL_SELECT_SIGHTING_BY_ID
            = "select * from Sighting "
            + "inner join AnomalySighting on Sighting.SightingId = AnomalySighting.SightingId "
            + "inner join Anomaly on AnomalySighting.AnomalyId = Anomaly.AnomalyId "
            + "inner join Location on Sighting.LocationId = Location.LocationId "
            + "where Sighting.SightingId = ?";
    
    protected static final String SQL_SELECT_SIGHTINGS_BY_ANOMALY_ID
            = "select * from Sighting "
            + "inner join AnomalySighting on Sighting.SightingId = AnomalySighting.SightingId "
            + "inner join Anomaly on AnomalySighting.AnomalyId = Anomaly.AnomalyId "
            + "inner join Location on Sighting.LocationId = Location.LocationId "
            + "where Anomaly.AnomalyId = ?";
    
    protected static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID
            = "select * from Sighting "
            + "inner join AnomalySighting on Sighting.SightingId = AnomalySighting.SightingId "
            + "inner join Anomaly on AnomalySighting.AnomalyId = Anomaly.AnomalyId "
            + "inner join Location on Sighting.LocationId = Location.LocationId "
            + "where Location.LocationId = ?";
    
    protected static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from Sighting "
            + "inner join AnomalySighting on Sighting.SightingId = AnomalySighting.SightingId "
            + "inner join Anomaly on AnomalySighting.AnomalyId = Anomaly.AnomalyId "
            + "inner join Location on Sighting.LocationId = Location.LocationId ";

    
// ================================================    
// Location CRUD SQL prepared statements
// ================================================

    
    protected static final String SQL_INSERT_LOCATION
            = "insert into Location (LocationName, LocationDescription, LocationStreet, LocationCity, LocationState, "
            + "LocationZip, LocationLatitude, LocationLongitude) values (?, ?, ?, ?, ?, ?, ?, ?)";
    
    protected static final String SQL_DELETE_LOCATION
            = "delete from Location where LocationId = ?";
    
    protected static final String SQL_UPDATE_LOCATION
            = "update Location set LocationName = ?, LocationDescription = ?, LocationStreet = ?, LocationCity = ?, "
            + "LocationState = ?, LocationZip = ?, LocationLatitude = ?, LocationLongitude = ? "
            + "where LocationId = ?";
    
    protected static final String SQL_SELECT_LOCATION_BY_ID
            = "select * from Location where LocationId = ?";
    
    protected static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from Location";

    
        // =====================================    
        // BRIDGE TABLE SQL prepared statements
        // =====================================

    
// ================================================    
// AnomalyAbility bridge CRUD prepared statements
// ================================================
    protected static final String SQL_INSERT_ANOMALYABILITY
            = "insert into AnomalyAbility(AnomalyId, AbilityId) "
            + "values (?, ?)";
    
    protected static final String SQL_DELETE_ANOMALYABILITY_BY_ANOMALY_ID
            = "delete from AnomalyAbility "
            + "where AnomalyId = ?";
    
    protected static final String SQL_DELETE_ANOMALYABILITY_BY_ABILITY_ID
            = "delete from AnomalyAbility "
            + "where AbilityId = ?";
    
    protected static final String SQL_SELECT_ABILITIES_BY_ANOMALY_ID
            = "select * from Ability "
            + "inner join AnomalyAbility on Ability.AbilityId = AnomalyAbility.AbilityId "
            + "where AnomalyAbility.AnomalyId = ?";
    
    protected static final String SQL_SELECT_ANOMALIES_BY_ABILITY_ID
            = "select * from Anomaly "
            + "inner join AnomalyAbility on Anomaly.AnomalyId = AnomalyAbility.AnomalyId "
            + "where AnomalyAbility.AbilityId = ?";
    
    
// ================================================    
// AnomalyOrganization bridge CRUD prepared statements
// ================================================
    
    
    protected static final String SQL_INSERT_ANOMALYORGANIZATION
            = "insert into AnomalyOrganization(AnomalyId, OrganizationId) "
            + "values (?, ?)";
    
    protected static final String SQL_DELETE_ANOMALYORGANIZATION_BY_ANOMALY_ID
            = "delete from AnomalyOrganization "
            + "where AnomalyId = ?";
    
    protected static final String SQL_DELETE_ANOMALYORGANIZATION_BY_ORGANIZATION_ID
            = "delete from AnomalyOrganization where OrganizationId = ?";
    
    protected static final String SQL_SELECT_ORGANIZATIONS_BY_ANOMALY_ID
            = "select * from Organization "
            + "inner join AnomalyOrganization on Organization.OrganizationId = AnomalyOrganization.OrganizationId "
            + "where AnomalyOrganization.AnomalyId = ?";
    
    protected static final String SQL_SELECT_ANOMALIES_BY_ORGANIZATION_ID
            = "select * from Anomaly "
            + "inner join AnomalyOrganization on Anomaly.AnomalyId = AnomalyOrganization.AnomalyId "
            + "where AnomalyOrganization.OrganizationId = ?";
    
    
// ================================================    
// AnomalySighting bridge CRUD prepared statements
// ================================================
    
    
    protected static final String SQL_INSERT_ANOMALYSIGHTING
            = "insert into AnomalySighting(AnomalyId, SightingId)"
            + "values (?, ?)";
    
    protected static final String SQL_DELETE_ANOMALYSIGHTING_BY_ANOMALY_ID
            = "delete from AnomalySighting where AnomalyId = ?";
    
    protected static final String SQL_DELETE_ANOMALYSIGHTING_BY_SIGHTING_ID
            = "delete from AnomalySighting where SightingId = ?";
    
    protected static final String SQL_DELETE_ANOMALYSIGHTING_BY_LOCATION_ID
            = "delete from AnomalySighting where SightingId = "
            + "(select SightingId from Sighting where SightingLocationId = ?)";
    
    protected static final String SQL_SELECT_ANOMALYSIGHTING_BY_ANOMALY_ID
            = "select * from Anomaly inner join on AnomalySighting "
            + "where Anomaly.AnomalyId = AnomalySighting.AnomalyId "
            + "inner join on Sighting where Sighting.SightingId = AnomalySighting.SightingId "
            + "inner join on Location where Sighting.SightingId where "
            + "Location.LocationId = Sighting.LocationId";
    
    protected static final String SQL_SELECT_ANOMALYSIGHTING_BY_SIGHTING_ID
            = "select * from Anomaly "
            + "inner join AnomalySighting on Anomaly.AnomalyId = AnomalySighting.AnomalyId "
            + "where AnomalySighting.SightingId = ?";
    
    
        // ================================================    
        // DELIVERABLE REQUIREMENTS SQL prepared statements
        // ================================================
    
    
    protected static final String SQL_SELECT_ANOMALIES_BY_LOCATION_ID
            = "select * from Anomaly "
            + "inner join AnomalySighting on Anomaly.AnomalyId = AnomalySighting.AnomalyId "
            + "inner join Sighting on AnomalySighting.SightingId = Sighting.SightingId "
            + "inner join Location on Sighting.LocationId = Location.LocationId "
            + "where Location.LocationId = ?";
    
    protected static final String SQL_SELECT_LOCATIONS_BY_ANOMALY_ID
            = "select * from Location "
            + "inner join Sighting on Location.LocationId = Sighting.LocationId "
            + "inner join AnomalySighting on Sighting.SightingId = AnomalySighting.SightingId "
            + "inner join Anomaly on AnomalySighting.AnomalyId = Anomaly.AnomalyId "
            + "where Anomaly.AnomalyId = ?";
    
    protected static final String SQL_SELECT_LOCATIONS_BY_SIGHTING_ID
            = "select * from Location "
            + "inner join Sighting on Location.LocationId = Sighting.LocationId "
            + "where Sighting.SightingId = ?";
    
    protected static final String SQL_SELECT_ALL_SIGHTINGS_BY_DATE
            = "select * from Sighting "
            + "inner join AnomalySighting on Sighting.SightingId = AnomalySighting.SightingId "
            + "inner join Anomaly on AnomalySighting.AnomalyId = Anomaly.AnomalyId "
            + "inner join Location on Sighting.LocationId = Location.LocationId "
            + "where Sighting.SightingDate = ?";
    
    protected static final String SQL_SELECT_LAST_10_SIGHTINGS
            = "select * from Sighting "
            + "inner join AnomalySighting on Sighting.SightingId = AnomalySighting.SightingId "
            + "inner join Anomaly on AnomalySighting.AnomalyId = Anomaly.AnomalyId "
            + "inner join Location on Sighting.LocationId = Location.LocationId "
            + "order by Sighting.SightingDate desc limit 10";
    
}
