/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.controller;
 
import com.sg.superherosightingmvc.model.Ability;
import com.sg.superherosightingmvc.model.Anomaly;
import com.sg.superherosightingmvc.service.AbilityServiceLayer;
import com.sg.superherosightingmvc.service.AnomalyServiceLayer;
import com.sg.superherosightingmvc.service.LocationServiceLayer;
import com.sg.superherosightingmvc.service.OrganizationServiceLayer;
import com.sg.superherosightingmvc.service.SightingServiceLayer;
import com.sg.superherosightingmvc.service.UserServiceLayer;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.Map;
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
public class AbilityController {
    
    private AnomalyServiceLayer anomalyService;
    private AbilityServiceLayer abilityService;
    private LocationServiceLayer locationService;
    private OrganizationServiceLayer organizationService;
    private SightingServiceLayer sightingService;
    private UserServiceLayer userService;
    
    
    
    @Inject
    public AbilityController(AnomalyServiceLayer anomalyService, AbilityServiceLayer abilityService, LocationServiceLayer locationService, OrganizationServiceLayer organizationService, SightingServiceLayer sightingService, UserServiceLayer userService) {
        this.anomalyService = anomalyService;
        this.abilityService = abilityService;
        this.locationService = locationService;
        this.organizationService = organizationService;
        this.sightingService = sightingService;
        this.userService = userService;
    }
    
    @RequestMapping(value = "/abilitiesPage", method = RequestMethod.GET)
    public String displayAbilitiesPage(Map<String, Object> model) {
        model.put("abilitiesList", abilityService.getAllAbilities());
        return "abilitiesPage";
    }
    
    @RequestMapping(value = "/addAbilityPage", method = RequestMethod.GET)
    public String addAbilityPage() {
        return "addAbilityPage";
    }
    
    @RequestMapping(value = "/addAbility", method = RequestMethod.POST)
    public String addAbility(HttpServletRequest request, Model model) {
        String input = request.getParameter("abilityName");
        String input2 = request.getParameter("abilityDescription");
        Ability ability = new Ability();
        ability.setAbilityName(input);
        ability.setAbilityDescription(input2);
        abilityService.addAbility(ability);
        model.addAttribute("abilitiesList", abilityService.getAllAbilities());
        return "abilitiesPage";
    }
    
    @RequestMapping(value = "/detailsAbilityPage", method = RequestMethod.GET)
    public String detailsAbilityPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("detailsAbilityId");
        int abilityId = parseInt(input);
        Ability ability = abilityService.getAbilityById(abilityId);
        List<Anomaly> anomalies = anomalyService.getAnomaliesByAbilityId(abilityId);
        model.addAttribute("anomalies", anomalies);
        model.addAttribute("ability", ability);
        return "detailsAbilityPage";
    }
    
    @RequestMapping(value = "/updateAbilityPage", method = RequestMethod.POST)
    public String updateAbilityPage(HttpServletRequest request, Model model) {
        String input = request.getParameter("updateAbilityId");
        int abilityId = parseInt(input);
        model.addAttribute("ability", abilityService.getAbilityById(abilityId));
        return "updateAbilityPage";
    }
    
    @RequestMapping(value = "/updateAbility", method = RequestMethod.POST)
    public String updateAbility(HttpServletRequest request, Model model) {
        String input = request.getParameter("abilityId");
        int abilityId = parseInt(input);
        Ability ability = abilityService.getAbilityById(abilityId);
        String name = request.getParameter("abilityName");
        String description = request.getParameter("abilityDescription");
        if(name.length() != 0 && description.length() != 0) {
            ability.setAbilityName(name);
            ability.setAbilityDescription(description);
            abilityService.updateAbility(ability);
            abilityService.getAbilityById(abilityId);
        }
        model.addAttribute("abilitiesList", abilityService.getAllAbilities());
        return "abilitiesPage";
    }
    
    @RequestMapping(value = "/deleteAbility", method = RequestMethod.POST)
    public String deleteAbility(HttpServletRequest request, Model model) {
        String input = request.getParameter("deleteAbility");
        int abilityId = parseInt(input);
        abilityService.deleteAbility(abilityId);
        model.addAttribute("abilitiesList", abilityService.getAllAbilities());
        return "abilitiesPage";
    }
}
