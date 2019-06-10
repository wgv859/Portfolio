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
public class OrganizationDaoJdbcTemplateImpl implements OrganizationDao {
    
    // Instantiate JdbcTemplate Object instance from bean in spring-persistence.xml
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        
        //the update method inserts the data from the Organization Object into into a row in the Organization database table
        jdbcTemplate.update(PreparedStatements.SQL_INSERT_ORGANIZATION, 
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationStreet(),
                organization.getOrganizationCity(),
                organization.getOrganizationState(),
                organization.getOrganizationZip(),
                organization.getOrganizationPhone(),
                organization.getOrganizationEmail());
        
        //this queryForObject method asks the database for the Id int value we just inserted bc of the first parameter "SQL_SELECT_LAST_INSERT_ID"
        //the second parameter indicates what type of Object should be returned
        int organizationId = jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_LAST_INSERT_ID, Integer.class);
        
        // Use the OrganizationId generated from the database to set the OrganizationId
        organization.setOrganizationId(organizationId);
        
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int organizationId) {
        
        // first delete the bridge table relationship for this Organization
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_ANOMALYORGANIZATION_BY_ORGANIZATION_ID, organizationId);
        
        // then delete the Organization
        jdbcTemplate.update(PreparedStatements.SQL_DELETE_ORGANIZATION, organizationId);
        
    }

    @Override
    public void updateOrganization(Organization organization) {
        
        //Update the Organization Object
        jdbcTemplate.update(PreparedStatements.SQL_UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationStreet(),
                organization.getOrganizationCity(),
                organization.getOrganizationState(),
                organization.getOrganizationZip(),
                organization.getOrganizationPhone(),
                organization.getOrganizationEmail(),
                organization.getOrganizationId());
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        
        try {
            // return properties from the Organization table for specified Organization
            return jdbcTemplate.queryForObject(PreparedStatements.SQL_SELECT_ORGANIZATION_BY_ID, new OrganizationDaoJdbcTemplateImpl.OrganizationMapper(), organizationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        
        // Return List of Organization after using OrganizationMapper and prepared statements to gather each Organization 
        // and its properties from the Database 
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationDaoJdbcTemplateImpl.OrganizationMapper());
    }
    
    @Override
    public List<Organization> getOrganizationsByAnomalyId(int anomalyId) {
        // Use the prepared statement to return a List of every Organization associated with the supplied Anomaly Object
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ORGANIZATIONS_BY_ANOMALY_ID, new OrganizationDaoJdbcTemplateImpl.OrganizationMapper(), anomalyId);
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
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ABILITIES_BY_ANOMALY_ID, new OrganizationDaoJdbcTemplateImpl.AbilityMapper(), anomaly.getAnomalyId());
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
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ORGANIZATIONS_BY_ANOMALY_ID, new OrganizationDaoJdbcTemplateImpl.OrganizationMapper(), anomaly.getAnomalyId());
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
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ANOMALYSIGHTING_BY_SIGHTING_ID, new OrganizationDaoJdbcTemplateImpl.AnomalyMapper(), sighting.getSightingId());
    }
    
    //Retrieves all of the Sightings for the Anomaly by joining the Anomaly and AnomalySighting tables.
    private List<Sighting> findSightingsForAnomaly(Anomaly anomaly) {
        
        //Use the prepared statement to return a List of every Sighting associated with the supplied Sighting Object
        return jdbcTemplate.query(PreparedStatements.SQL_SELECT_ANOMALYSIGHTING_BY_ANOMALY_ID, new OrganizationDaoJdbcTemplateImpl.SightingMapper(), anomaly.getAnomalyId());
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
