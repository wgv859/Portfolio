/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.controller;
 
import com.sg.superherosightingmvc.model.Anomaly;
import com.sg.superherosightingmvc.model.Location;
import com.sg.superherosightingmvc.service.AbilityServiceLayer;
import com.sg.superherosightingmvc.service.AnomalyServiceLayer;
import com.sg.superherosightingmvc.service.LocationServiceLayer;
import com.sg.superherosightingmvc.service.OrganizationServiceLayer;
import com.sg.superherosightingmvc.service.SightingServiceLayer;
import com.sg.superherosightingmvc.service.UserServiceLayer;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author wgv85
 */

@Controller
public class LocationController {
    
    private AnomalyServiceLayer anomalyService;
    private AbilityServiceLayer abilityService;
    private LocationServiceLayer locationService;
    private OrganizationServiceLayer organizationService;
    private SightingServiceLayer sightingService;
    private UserServiceLayer userService;
    
    
    
    @Inject
    public LocationController(AnomalyServiceLayer anomalyService, AbilityServiceLayer abilityService, LocationServiceLayer locationService, OrganizationServiceLayer organizationService, SightingServiceLayer sightingService, UserServiceLayer userService) {
        this.anomalyService = anomalyService;
        this.abilityService = abilityService;
        this.locationService = locationService;
        this.organizationService = organizationService;
        this.sightingService = sightingService;
        this.userService = userService;
    }
    
    @RequestMapping(value = "/locationsPage", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        model.addAttribute("locationsList", locationService.getAllLocations());
        return "locationsPage";
    }
    
    @RequestMapping(value = "/addLocationPage", method = RequestMethod.GET)
    public String addLocationPage() {
        return "addLocationPage";
    }
    
    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request, Model model) {
        Location location = new Location();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String message = "";
        
        try {
            BigDecimal lat = new BigDecimal(latitude);
            BigDecimal lon = new BigDecimal(longitude);
            location.setLocationName(name);
            location.setLocationDescription(description);
            location.setLocationStreet(street);
            location.setLocationCity(city);
            location.setLocationState(state);
            location.setLocationZip(zip);
            location.setLocationLatitude(lat);
            location.setLocationLongitude(lon);
            locationService.addLocation(location);
            model.addAttribute("locationsList", locationService.getAllLocations());
            return "locationsPage";
        } catch (NumberFormatException ex) {
            message = "latitude and Longitude must be in proper format. (lat = 00.000000)(long = 000.000000)";
        }
        model.addAttribute("message", message);
        return "addLocationPage";
    }
    
    @RequestMapping(value = "/updateLocationPage", method = RequestMethod.GET)
    public String updateLocationPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("updateLocation");
        int locationId = parseInt(input);
        model.addAttribute("location", locationService.getLocationById(locationId));
        return "updateLocationPage";
    }
    
    @RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
    public String updateLocation(HttpServletRequest request, Model model) {
        String message = "";
        String input = request.getParameter("locationId");
        Location loc = locationService.getLocationById(parseInt(input));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        
        if (name.length() != 0) {
            loc.setLocationName(name);
        }
        if (description.length() != 0) {
            loc.setLocationDescription(description);
        }
        if (street.length() != 0) {
            loc.setLocationStreet(street);
        }
        if (city.length() != 0) {
            loc.setLocationCity(city);
        }
        if (state.length() != 0) {
            loc.setLocationState(state);
        }
        if (zip.length() != 0) {
            loc.setLocationZip(zip);
        }
        if (latitude.length() !=0) {
            try {
                loc.setLocationLatitude(new BigDecimal(latitude));
                //pretty sure I can use either of these exceptions.
            } catch (NumberFormatException | EmptyResultDataAccessException ex) {
                message = "latitude and Longitude must be in proper format. (lat = 00.000000)(long = 000.000000)";
                model.addAttribute("message", message);
                model.addAttribute("location", locationService.getLocationById(parseInt(input)));
                return "editLocationPage";
            }
        }
        if (longitude.length() !=0) {
            try {
                loc.setLocationLongitude(new BigDecimal(longitude));
            } catch(NumberFormatException | EmptyResultDataAccessException ex) {
                message = "latitude and Longitude must be in proper format. (lat = 00.000000)(long = 000.000000)";
                model.addAttribute("message", message);
                model.addAttribute("location", locationService.getLocationById(parseInt(input)));
                return "editLocationPage";
            }
        }
        locationService.updateLocation(loc);
        model.addAttribute("locationsList", locationService.getAllLocations());
        
        return "locationsPage";
    }
    
    @RequestMapping(value = "/detailsLocationPage", method = RequestMethod.GET)
    public String detailsLocation(HttpServletRequest request, Model model) {
        String input = request.getParameter("detailsLocationId");
        int locationId = parseInt(input);
        List<Anomaly> anomalies = anomalyService.getAnomaliesByLocationId(locationId);
        model.addAttribute("anomalies", anomalies);
        model.addAttribute("location", locationService.getLocationById(locationId));
        return "detailsLocationPage";
    }
    
    @RequestMapping(value = "/deleteLocation", method = RequestMethod.POST)
    public String deleteLocation(HttpServletRequest request, Model model) {
        String input = request.getParameter("deleteLocation");
        int locationId = parseInt(input);
        locationService.deleteLocation(locationId);
        model.addAttribute("locationsList", locationService.getAllLocations());
        return "locationsPage";
    }
     
}
