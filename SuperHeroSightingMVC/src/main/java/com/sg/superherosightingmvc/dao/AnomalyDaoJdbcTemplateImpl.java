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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wgv85
 */
public class AnomalyDaoJdbcTemplateImpl implements AnomalyDao {
    
    // Instantiate JdbcTemplate Object instance from bean in spring-persistence.xml
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addAnomaly(Anomaly anomaly) {
        
        //the update method inserts the data from the Anomaly Object into into a row in the Anomaly database table.
        jdbcTemplate.update(PreparedStatements.SQL_INSERT_ANOMALY, anomaly.getAnomalyName(), anomaly.getAnomalyDescription());
        
        //this queryForObject method asks the database for the value we just inserted bc of the first parameter SQL_SELECT_LAST_INSERT_ID
        //the second parameter indicates what type of Object should be returned.
        int anomalyId = jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_LAST_INSERT_ID, Integer.class);
        
        //use the anomalyId generated by the database to set the Id for anomalyId.
        anomaly.setAnomalyId(anomalyId);
        
        // Update the AnomalyOrganization table
        insertAnomalyOrganizations(anomaly);
        
        // Update the AnomalyAbility table
        insertAnomalyAbilities(anomaly);
    
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteAnomaly(int anomalyId) {
        
        // First delete all of the bridge table relationships for this Anomaly
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_ANOMALYABILITY_BY_ANOMALY_ID, anomalyId);
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_ANOMALYORGANIZATION_BY_ANOMALY_ID, anomalyId);
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_ANOMALYSIGHTING_BY_ANOMALY_ID, anomalyId);
        
        // Then delete the Anomaly
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_ANOMALY, anomalyId);
    
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateAnomaly(Anomaly anomaly) {
        
        // Update the Anomaly Object
        jdbcTemplate.update(PreparedStatements.SQL_UPDATE_ANOMALY,
                anomaly.getAnomalyName(),
                anomaly.getAnomalyDescription(),
                anomaly.getAnomalyId());
        
        // Delete AnomalyAbility relationships
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_ANOMALYABILITY_BY_ANOMALY_ID, anomaly.getAnomalyId());
        //reset the AnomalyAbility relationships
        insertAnomalyAbilities(anomaly);
        
        // Delete AnomalyOrganization relationships
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_ANOMALYORGANIZATION_BY_ANOMALY_ID, anomaly.getAnomalyId());
        //reset the AnomalyOrganizations relationships
        insertAnomalyOrganizations(anomaly);
    
    }

    @Override
    public Anomaly getAnomalyById(int anomalyId) {
        
        try {
            // get the properties from the Anomaly table
            Anomaly anomaly = jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_ANOMALY_BY_ID, new AnomalyDaoJdbcTemplateImpl.AnomalyMapper(), anomalyId);
            
            // get the Abilities for the Anomaly and set list on the Anomaly
            anomaly.setAbilities(findAbilitiesForAnomaly(anomaly));
            
            // Get the Organizations for this Anomaly and set list on the Anomaly
            anomaly.setOrganizations(findOrganizationsForAnomaly(anomaly));
            
            return anomaly;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    
    }
    
    @Override
    public List<Anomaly> getAllAnomalies() {
        
        // Get all of the Anomalies from the Anomaly DB table, convert to Java Objects, and populate List with Anomalies
        List<Anomaly> anomalies = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_ANOMALIES, new AnomalyDaoJdbcTemplateImpl.AnomalyMapper());
        
        // Set the Abilities for each Anomaly in the List
        anomalies = findAbilitiesForAllAnomalies(anomalies);
        
        // Set the Organizations for each Anomaly in the List
        anomalies = findOrganizationsForAllAnomalies(anomalies);
        
        return anomalies;

    }
    
    @Override
    public List<Anomaly> getAnomaliesByLocationId(int locationId) {
        
        //Create a List<Anomaly> associated with the supplied locationId
        List<Anomaly> anomalies = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ANOMALIES_BY_LOCATION_ID, new AnomalyDaoJdbcTemplateImpl.AnomalyMapper(), locationId);
        
        //use AnomalyAbility helper method to set the [List<Ability> abilities] field for each Anomaly Model Object in the List<Anomaly> anomalies
        anomalies = findAbilitiesForAllAnomalies(anomalies);
        
        //use AnomalyOrganization helper method to set the [List<Organization> organizations] field for each Anomaliy Model Object
        //in the list<Anomaly> anomalies
        anomalies = findOrganizationsForAllAnomalies(anomalies);
        
        // return the List<Anomaly> anomalies
        return anomalies;
    }
    
    @Override
    public List<Anomaly> getAnomaliesByOrganizationId(int organizationId) {
        
        //Create a List<Anomaly> associated with the supplied organizationId
        List<Anomaly> anomalies = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ANOMALIES_BY_ORGANIZATION_ID, new AnomalyDaoJdbcTemplateImpl.AnomalyMapper(), organizationId);
        
        //use AnomalyAbility helper method to populate [List<Ability> abilities] field for each 
        //Anomaly Model Object in the List<Anomaly> associated with the incremented Sighting Model Object 
        anomalies = findAbilitiesForAllAnomalies(anomalies);
        
        //use AnomalyOrganization helper method to populate [List<Organization> organizations] field for each
        //Anomaly Model Object in the List<Anomaly> associated with the incremented Sighting Model Object
        anomalies = findOrganizationsForAllAnomalies(anomalies);
        
        return anomalies;
        
    }
    
    @Override
    public List<Anomaly> getAnomaliesByAbilityId(int abilityId) {
        
        //Create a List<Anomaly> associated with the supplied abilityId
        List<Anomaly> anomalies = jdbcTemplate.query(PreparedStatements.SQL_SELECT_ANOMALIES_BY_ABILITY_ID, new AnomalyDaoJdbcTemplateImpl.AnomalyMapper(), abilityId);
        
        //use AnomalyAbility helper method to populate [List<Ability> abilities] field for each 
        //Anomaly Model Object in the List<Anomaly> associated with the incremented Sighting Model Object
        anomalies = findAbilitiesForAllAnomalies(anomalies);
        
        //use AnomalyOrganization helper method to populate [List<Organization> organizations] field for each
        //Anomaly Model Object in the List<Anomaly> associated with the incremented Sighting Model Object
        anomalies = findOrganizationsForAllAnomalies(anomalies);
        
        return anomalies;
    }
    
//***********************************************************************************
// AnomalyAbility HELPER METHODS
//***********************************************************************************
    
    
    private void insertAnomalyAbilities(Anomaly anomaly) {
        
        // declare and set a "helper" int from the supplied Anomaly Model Object
        final int anomalyId = anomaly.getAnomalyId();
        
        // declare and populate a List of Ability Objects using the List<Ability>
        // associated with the supplied Anomaly Model Object
        final List<Ability> abilities = anomaly.getAbilities();
        
        // Update the AnomalyAbility bridge table with the incremented ablityId and "helper" anomalyId
        for (Ability currentAbility : abilities) {
            jdbcTemplate.update(PreparedStatements.SQL_INSERT_ANOMALYABILITY, anomalyId, currentAbility.getAbilityId());
        }
    }
    
    // Retrieves all of the Abilities for the Anomaly by joinging the Anomaly and AnomalyAbility tables.
    private List<Ability> findAbilitiesForAnomaly(Anomaly anomaly) {
        
        // Use the prepared statement to return a List of every Ability associated with the supplied Anomaly Object
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ABILITIES_BY_ANOMALY_ID, new AnomalyDaoJdbcTemplateImpl.AbilityMapper(), anomaly.getAnomalyId());
    }
    
    // Retrieves and adds all of the Abilities for every Anomaly in the supplied Anomaly List, returns Anomaly List
    private List<Anomaly> findAbilitiesForAllAnomalies(List<Anomaly> anomalies) {
        
        // set the [List<Ability> Abilities] field for each Anomaly in the anomalies List
        for (Anomaly currentAnomaly : anomalies) {
            currentAnomaly.setAbilities(findAbilitiesForAnomaly(currentAnomaly));
        }
        return anomalies;
    }
    

//************************************************************************************
// AnomalyOrganization HELPER METHODS
//***********************************************************************************
    
    
    private void insertAnomalyOrganizations(Anomaly anomaly) {
        
        // declare and set a "helper" int from the supplied Anomaly Model Object
        final int anomalyId  = anomaly.getAnomalyId();
        
        // declare and populate a List of Organization Objects using the List<Organization>
        // associated with the supplied Anomaly Model Object
        final List<Organization> organizations = anomaly.getOrganizations();
        
        // Update the AnomalyOrganization bridge table with the incremented organizationId and "helper" anomalyId
        for (Organization currentOrganization : organizations) {
            
            // Update AnomalyOrganization relationships
            jdbcTemplate.update(PreparedStatements.SQL_INSERT_ANOMALYORGANIZATION, anomalyId, currentOrganization.getOrganizationId());
        }
    }
    
    // Retrieves all of the Organizations for the Anomaly by joinging the Anomaly and AnomalyOrganization tables.
    private List<Organization> findOrganizationsForAnomaly(Anomaly anomaly) {
        
        // Use the prepared statement to return a List of every Organization associated with the supplied Anomaly Object
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ORGANIZATIONS_BY_ANOMALY_ID, new AnomalyDaoJdbcTemplateImpl.OrganizationMapper(), anomaly.getAnomalyId());
    }
    
    // Retrieves and adds all of the Organizations for every Anomaly in the supplied Anomaly List, retrurn Anomaly List
    private List<Anomaly> findOrganizationsForAllAnomalies(List<Anomaly> anomalies) {
        
        // set the [List<Organization> Organizations] field for each Anomaly in the anomalies List
        for (Anomaly currentAnomaly : anomalies) {
            currentAnomaly.setOrganizations(findOrganizationsForAnomaly(currentAnomaly));
        }
        return anomalies;
    }

//************************************************************************************
// AnomalySighting HELPER METHODS
//***********************************************************************************
    
    
    private void insertAnomalySightings(Sighting sighting) {
        
        // declare and set a "helper" int from the supplied Sighting Model Object's sightingId
        final int sightingId = sighting.getSightingId();
        
        // declare and populate a List of Anomaly Objects using the [List<Anomaly> anomalies] field
        // associated with the supplied Sighting Model Object
        final List<Anomaly> anomalies = sighting.getAnomalies();
        
        // Update the AnomalySighting bridge table with the incremented anomalyId and "helper" sightingId
        for (Anomaly currentAnomaly : anomalies) {
            jdbcTemplate.update(PreparedStatements.SQL_INSERT_ANOMALYSIGHTING, currentAnomaly.getAnomalyId(), sightingId);
        }
    }
    
    // Retrieves all of the Anomalies for the Sighting by joinging the Sighting and AnomalySighting tables.
    private List<Anomaly> findAnomaliesForSighting(Sighting sighting) {
        
        // Use the prepared statement to return a List of every Anomaly associated with the supplied Sighting Object
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ANOMALYSIGHTING_BY_SIGHTING_ID, new AnomalyDaoJdbcTemplateImpl.AnomalyMapper(), sighting.getSightingId());
    }
    
    //Retrieves all of the Sightings for the Anomaly by joining the Anomaly and AnomalySighting tables.
    private List<Sighting> findSightingsForAnomaly(Anomaly anomaly) {
        
        //Use the prepared statement to return a List of every Sighting associated with the supplied Sighting Object
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ANOMALYSIGHTING_BY_ANOMALY_ID, new AnomalyDaoJdbcTemplateImpl.SightingMapper(), anomaly.getAnomalyId());
    }
    
    
    // Retrieves and adds all of the Anomalies for every Sighting in the supplied Sighting List, return Sighting List
    private List<Sighting> findAnomaliesForAllSightings(List<Sighting> sightings) {
        
        // set the [List<Anomaly> anomalies] field for each Sighting in the sightings List
        for (Sighting currentSighting : sightings) {
            currentSighting.setAnomalies(findAnomaliesForSighting(currentSighting));
        }
        
        // return the sightings List
        return sightings;
    }

    
    // ======================================
    // ============ MAPPERS =================
    // ======================================
    //These convert rows from the different tables into equivalent Java Objects (Models).
    //JdbcTemplate allows us to use Mappers in our Java methods using the RowMapper interface.
    
    
    private static final class AnomalyMapper implements RowMapper<Anomaly> {
        
        @Override
        public Anomaly mapRow(ResultSet rs, int i) throws SQLException {
            Anomaly anomaly = new Anomaly();
            anomaly.setAnomalyName(rs.getString("AnomalyName"));
            anomaly.setAnomalyDescription(rs.getString("AnomalyDescription"));
            anomaly.setAnomalyId(rs.getInt("AnomalyId"));
            return anomaly;
        }
        
    }
    
    private static final class AbilityMapper implements RowMapper<Ability> {
        
        @Override
        public Ability mapRow(ResultSet rs, int i) throws SQLException {
            Ability ability = new Ability();
            ability.setAbilityName(rs.getString("AbilityName"));
            ability.setAbilityDescription(rs.getString("AbilityDescription"));
            ability.setAbilityId(rs.getInt("AbilityId"));
            return ability;
        }
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization> {
        
        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            
            Organization org = new Organization();
            org.setOrganizationName(rs.getString("OrganizationName"));
            org.setOrganizationDescription(rs.getString("OrganizationDescription"));
            org.setOrganizationStreet(rs.getString("OrganizationStreet"));
            org.setOrganizationCity(rs.getString("OrganizationCity"));
            org.setOrganizationState(rs.getString("OrganizationState"));
            org.setOrganizationZip(rs.getString("OrganizationZip"));
            org.setOrganizationPhone(rs.getString("OrganizationPhone"));
            org.setOrganizationEmail(rs.getString("OrganizationEmail"));
            org.setOrganizationId(rs.getInt("OrganizationId"));
            return org;
        }
    }
    
    private static final class LocationMapper implements RowMapper<Location> {
        
        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setLocationName(rs.getString("LocationName"));
            loc.setLocationDescription(rs.getString("LocationDescription"));
            loc.setLocationStreet(rs.getString("LocationStreet"));
            loc.setLocationCity(rs.getString("LocationCity"));
            loc.setLocationState(rs.getString("LocationState"));
            loc.setLocationZip(rs.getString("LocationZip"));
            loc.setLocationLatitude(rs.getBigDecimal("LocationLatitude"));
            loc.setLocationLongitude(rs.getBigDecimal("LocationLongitude"));
            loc.setLocationId(rs.getInt("LocationId"));
            return loc;
        }
    }
    
    private static final class SightingMapper implements RowMapper<Sighting> {
        
        
        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingDate(rs.getTimestamp("SightingDate").toLocalDateTime().toLocalDate());
            
            // Get Location Object to use as Sighting foreign key
            Location location = new Location();
            location.setLocationId(rs.getInt("LocationId"));
            location.setLocationName(rs.getString("LocationName"));
            location.setLocationDescription(rs.getString("LocationDescription"));
            location.setLocationStreet(rs.getString("LocationStreet"));
            location.setLocationCity(rs.getString("LocationCity"));
            location.setLocationState(rs.getString("LocationState"));
            location.setLocationZip(rs.getString("LocationZip"));
            location.setLocationLatitude(rs.getBigDecimal("LocationLatitude"));
            
            sighting.setLocation(location);
            sighting.setSightingId(rs.getInt("SightingId"));
            
            return sighting;
        }
    }
    
}
