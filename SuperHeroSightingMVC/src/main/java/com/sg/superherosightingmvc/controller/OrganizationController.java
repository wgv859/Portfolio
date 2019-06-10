/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.controller;
 
import com.sg.superherosightingmvc.model.Anomaly;
import com.sg.superherosightingmvc.model.Organization;
import com.sg.superherosightingmvc.service.AbilityServiceLayer;
import com.sg.superherosightingmvc.service.AnomalyServiceLayer;
import com.sg.superherosightingmvc.service.LocationServiceLayer;
import com.sg.superherosightingmvc.service.OrganizationServiceLayer;
import com.sg.superherosightingmvc.service.SightingServiceLayer;
import com.sg.superherosightingmvc.service.UserServiceLayer;
import static java.lang.Integer.parseInt;
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
public class OrganizationController {
    
    private AnomalyServiceLayer anomalyService;
    private AbilityServiceLayer abilityService;
    private LocationServiceLayer locationService;
    private OrganizationServiceLayer organizationService;
    private SightingServiceLayer sightingService;
    private UserServiceLayer userService;
    
    
    
    @Inject
    public OrganizationController(AnomalyServiceLayer anomalyService, AbilityServiceLayer abilityService, LocationServiceLayer locationService, OrganizationServiceLayer organizationService, SightingServiceLayer sightingService, UserServiceLayer userService) {
        this.anomalyService = anomalyService;
        this.abilityService = abilityService;
        this.locationService = locationService;
        this.organizationService = organizationService;
        this.sightingService = sightingService;
        this.userService = userService;
    }
    
    @RequestMapping(value = "/organizationsPage", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {
        model.addAttribute("organizationsList", organizationService.getAllOrganizations());
        return "organizationsPage";
    }
    
    @RequestMapping(value = "/addOrganizationPage", method = RequestMethod.GET)
    public String addOrganizationPage() {
        return "addOrganizationPage";
    }
    
    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        
        Organization org = new Organization();
        
        org.setOrganizationName(name);
        org.setOrganizationDescription(description);
        org.setOrganizationStreet(street);
        org.setOrganizationCity(city);
        org.setOrganizationState(state);
        org.setOrganizationZip(zip);
        org.setOrganizationPhone(phone);
        org.setOrganizationEmail(email);
        organizationService.addOrganization(org);
        model.addAttribute("organizationsList", organizationService.getAllOrganizations());
        return "organizationsPage";
    }
    
    @RequestMapping(value = "/detailsOrganizationPage", method = RequestMethod.GET)
    public String detailsOrganizationPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("detailsOrganizationId");
        int organizationId = parseInt(input);
        List<Anomaly> anomalies = anomalyService.getAnomaliesByOrganizationId(organizationId);
        model.addAttribute("anomalies", anomalies);
        model.addAttribute("organization", organizationService.getOrganizationById(organizationId));
        return "detailsOrganizationPage";
    }
    
    @RequestMapping(value = "/updateOrganizationPage", method = RequestMethod.GET)
    public String editOrganizationPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("updateOrganizationId");
        int orgId = parseInt(input);
        model.addAttribute("organization", organizationService.getOrganizationById(orgId));
        return "updateOrganizationPage";
    }
    
    @RequestMapping(value = "/updateOrganization", method = RequestMethod.POST)
    public String editOrganization(HttpServletRequest request, Model model) {
        String organizationId = request.getParameter("organizationId");
        Organization org = organizationService.getOrganizationById(parseInt(organizationId));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String  street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        if(name.length() != 0) {
            org.setOrganizationName(name);
        }
        if(description.length() != 0) {
            org.setOrganizationDescription(description);
        }
        if(street.length() != 0) {
            org.setOrganizationStreet(street);
        }
        if(city.length() != 0) {
            org.setOrganizationCity(city);
        }
        if(state.length() != 0) {
            org.setOrganizationState(state);
        }
        if(zip.length() != 0) {
            org.setOrganizationZip(zip);
        }
        if(phone.length() != 0) {
            org.setOrganizationPhone(phone);
        }
        if(email.length() != 0) {
            org.setOrganizationEmail(email);
        }
        organizationService.updateOrganization(org);
        model.addAttribute("organizationsList", organizationService.getAllOrganizations());
        return "organizationsPage";
    }
    
    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.POST)
    public String deleteOrganization(HttpServletRequest request, Model model) {
        String input = request.getParameter("deleteOrganization");
        int orgId = parseInt(input);
        organizationService.deleteOrganization(orgId);
        model.addAttribute("organizationsList", organizationService.getAllOrganizations());
        return "organizationsPage";
    }
    
}
