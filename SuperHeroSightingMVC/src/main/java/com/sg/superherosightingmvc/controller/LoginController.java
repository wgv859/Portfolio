/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.controller;

import com.sg.superherosightingmvc.service.AbilityServiceLayer;
import com.sg.superherosightingmvc.service.AnomalyServiceLayer;
import com.sg.superherosightingmvc.service.LocationServiceLayer;
import com.sg.superherosightingmvc.service.OrganizationServiceLayer;
import com.sg.superherosightingmvc.service.SightingServiceLayer;
import com.sg.superherosightingmvc.service.UserServiceLayer;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author wgv85
 */
@Controller
public class LoginController {
    
    private AnomalyServiceLayer anomalyService;
    private AbilityServiceLayer abilityService;
    private LocationServiceLayer locationService;
    private OrganizationServiceLayer organizationService;
    private SightingServiceLayer sightingService;
    private UserServiceLayer userService;
    
    
    
    @Inject
    public LoginController(AnomalyServiceLayer anomalyService, AbilityServiceLayer abilityService, LocationServiceLayer locationService, OrganizationServiceLayer organizationService, SightingServiceLayer sightingService, UserServiceLayer userService) {
        this.anomalyService = anomalyService;
        this.abilityService = abilityService;
        this.locationService = locationService;
        this.organizationService = organizationService;
        this.sightingService = sightingService;
        this.userService = userService;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }
    
}
