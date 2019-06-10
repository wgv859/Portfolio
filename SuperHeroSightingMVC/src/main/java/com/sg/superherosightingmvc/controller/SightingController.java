/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.controller;
 
import com.sg.superherosightingmvc.model.Anomaly;
import com.sg.superherosightingmvc.model.Location;
import com.sg.superherosightingmvc.model.Sighting;
import com.sg.superherosightingmvc.service.AbilityServiceLayer;
import com.sg.superherosightingmvc.service.AnomalyServiceLayer;
import com.sg.superherosightingmvc.service.LocationServiceLayer;
import com.sg.superherosightingmvc.service.OrganizationServiceLayer;
import com.sg.superherosightingmvc.service.SightingServiceLayer;
import com.sg.superherosightingmvc.service.UserServiceLayer;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author wgv85
 */

@Controller
public class SightingController {
    
    private AnomalyServiceLayer anomalyService;
    private AbilityServiceLayer abilityService;
    private LocationServiceLayer locationService;
    private OrganizationServiceLayer organizationService;
    private SightingServiceLayer sightingService;
    private UserServiceLayer userService;
    
    
    
    @Inject
    public SightingController(AnomalyServiceLayer anomalyService, AbilityServiceLayer abilityService, LocationServiceLayer locationService, OrganizationServiceLayer organizationService, SightingServiceLayer sightingService, UserServiceLayer userService) {
        this.anomalyService = anomalyService;
        this.abilityService = abilityService;
        this.locationService = locationService;
        this.organizationService = organizationService;
        this.sightingService = sightingService;
        this.userService = userService;
    }
    
    @RequestMapping(value = "/sightingsPage", method = RequestMethod.GET)
    public String displaySightingsPage(HttpServletRequest request, Model model) {
        model.addAttribute("sightingsList", sightingService.getAllSightings());
        return "sightingsPage";
    }
    
    @RequestMapping(value = "/addSightingPage", method = RequestMethod.GET)
    public String addSightingPage(Model model) {
        model.addAttribute("anomalies", anomalyService.getAllAnomalies());
        model.addAttribute("locations", locationService.getAllLocations());
        return "addSightingPage";
    }
    
    @RequestMapping(value = "addSighting", method = RequestMethod.POST)
    public String addSighting(HttpServletRequest request, Model model) {
        Sighting sighting = new Sighting();
        String date = request.getParameter("date");
        String[] anomalies = request.getParameterValues("anomalies");
        String location = request.getParameter("location");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate sightingDate = LocalDate.parse(date, formatter);
        sighting.setSightingDate(sightingDate);
        Location loc = locationService.getLocationById(parseInt(location));
        sighting.setLocation(loc);

        List<Anomaly> sightingAnomalies = new ArrayList();
        for (int i = 0; i < anomalies.length; i++) {
            sightingAnomalies.add(anomalyService.getAnomalyById(parseInt(anomalies[i])));
        }
        sighting.setAnomalies(sightingAnomalies);
        sightingService.addSighting(sighting);
        model.addAttribute("sightingsList", sightingService.getAllSightings());
        return "sightingsPage";
}
    
    @RequestMapping(value = "/updateSightingPage", method = RequestMethod.GET)
    public String updateSightingPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("updateSightingId");
        int sightingId = parseInt(input);
        model.addAttribute("sighting", sightingService.getSightingById(sightingId));
        model.addAttribute("anomalies", anomalyService.getAllAnomalies());
        model.addAttribute("locations", locationService.getAllLocations());
        return "updateSightingPage";
    }
    
    @RequestMapping(value = "/updateSighting", method = RequestMethod.GET)
    public String updateSighting(HttpServletRequest request, Model model) {
        String input = request.getParameter("sightingId");
        int sightingId = parseInt(input);
        Sighting sighting = sightingService.getSightingById(sightingId);
        String date = request.getParameter("date");
        String[] anomalies = request.getParameterValues("anomalies");
        String location = request.getParameter("location");
        List<Anomaly> sightingAnomalies = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        if (date.length() != 0) {
            LocalDate sightingDate = LocalDate.parse(date, formatter);
            sighting.setSightingDate(sightingDate);
        }
        
        if (anomalies != null && anomalies.length != 0) {
            for (int i = 0; i < anomalies.length; i++) {
                sightingAnomalies.add(anomalyService.getAnomalyById(parseInt(anomalies[i])));
            }
            sighting.setAnomalies(sightingAnomalies);
        }
        
        if (location.length() != 0) {
            sighting.setLocation(locationService.getLocationById(parseInt(location)));
        }
        
        sightingService.updateSighting(sighting);
        model.addAttribute("sightingsList", sightingService.getAllSightings());
        return "sightingsPage";
    }
    
    @RequestMapping(value = "/deleteSighting", method = RequestMethod.POST)
    public String deleteSighting(HttpServletRequest request, Model model) {
        String input = request.getParameter("deleteSightingId");
        sightingService.deleteSighting(parseInt(input));
        model.addAttribute("sightingsList", sightingService.getAllSightings());
        return "sightingsPage";
    }
    
    @RequestMapping(value = "/detailsSightingPage", method = RequestMethod.GET)
    public String detailsSightingPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("detailsSightingId");
        int sightingId = parseInt(input);
        model.addAttribute("sighting", sightingService.getSightingById(sightingId));
        return "detailsSightingPage";
    }
    
    @RequestMapping(value = "/sightingsByDatePage", method = RequestMethod.GET)
    public String sightingsByDatePage(HttpServletRequest request, Model model) {
        String date = request.getParameter("date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Sighting> sightings = new ArrayList<>();
        LocalDate sightingDate;
        if (date.length() != 0) {
            sightingDate = LocalDate.parse(date, formatter);
            sightings = sightingService.getAllSightingsByDate(sightingDate);
            if (sightings.isEmpty()) {
                model.addAttribute("message", "No sightings recorded for that date.");
                return "sightingsByDatePage";
            }
            model.addAttribute("sightingsList", sightings);
        }
        return "sightingsByDatePage";
    }
    
}
