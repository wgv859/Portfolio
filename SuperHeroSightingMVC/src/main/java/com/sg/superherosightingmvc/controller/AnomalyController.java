/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.controller;
 
import com.sg.superherosightingmvc.model.Ability;
import com.sg.superherosightingmvc.model.Anomaly;
import com.sg.superherosightingmvc.model.Location;
import com.sg.superherosightingmvc.model.Organization;
import com.sg.superherosightingmvc.service.AbilityServiceLayer;
import com.sg.superherosightingmvc.service.AnomalyServiceLayer;
import com.sg.superherosightingmvc.service.LocationServiceLayer;
import com.sg.superherosightingmvc.service.OrganizationServiceLayer;
import com.sg.superherosightingmvc.service.SightingServiceLayer;
import com.sg.superherosightingmvc.service.UserServiceLayer;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
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
public class AnomalyController {
    
    private AnomalyServiceLayer anomalyService;
    private AbilityServiceLayer abilityService;
    private LocationServiceLayer locationService;
    private OrganizationServiceLayer organizationService;
    private SightingServiceLayer sightingService;
    private UserServiceLayer userService;
    
    
    
    @Inject
    public AnomalyController(AnomalyServiceLayer anomalyService, AbilityServiceLayer abilityService, LocationServiceLayer locationService, OrganizationServiceLayer organizationService, SightingServiceLayer sightingService, UserServiceLayer userService) {
        this.anomalyService = anomalyService;
        this.abilityService = abilityService;
        this.locationService = locationService;
        this.organizationService = organizationService;
        this.sightingService = sightingService;
        this.userService = userService;
    }
                                                                //Only retrieving info from the DAO
    @RequestMapping(value = "/anomaliesPage", method = RequestMethod.GET)
    public String displayAnomaliesPage(Map<String, Object> model) {
        model.put("anomaliesList", anomalyService.getAllAnomalies());
        return "anomaliesPage";
    }

    @RequestMapping(value = "/addAnomalyPage", method = RequestMethod.GET)
    public String addAnomalyPage(Model model) {
        model.addAttribute("organizations", organizationService.getAllOrganizations());
        model.addAttribute("abilities", abilityService.getAllAbilities());
        return "addAnomalyPage";
    }
    //handles the requests for the /addAnomaly url                POSTing info to the dao
    @RequestMapping(value = "/addAnomaly", method = RequestMethod.POST)
    public String addAnomaly(HttpServletRequest request, Model model) {
        
        //declare a new anomaly Object
        Anomaly anomaly = new Anomaly();
        
        //get the user inputed anomaly name from the textbox designated 'name' in the addAnomolyPage form.
        String name = request.getParameter("name");
        
        //get the user inputed anomaly description
        String description = request.getParameter("description");
        
        //get the abilities parameters (abilityId) that the user has chosen from selectpicker box/list. 
        //Put those AbilityName strings in a String[].
        String[] abilities = request.getParameterValues("abilities");
        
        //get the organizations parameters that the user chose.......
        String[] organizations = request.getParameterValues("organizations");
        
        //set the name on our Anomaly Object
        anomaly.setAnomalyName(name);
        
        //set the description on our Anomaly Object
        anomaly.setAnomalyDescription(description);
        
        //delcare ability list and populate using the anomalyId in the String[] supplied by user, set to our anomaly
        List<Ability> anomalyAbilities = new ArrayList();
        for (int i = 0; i < abilities.length; i++) {
            //convert the String number representations into ints and use the abilityService to retrieve the associated ability object
            //from the dao and add them to our Anomaly object's ability Array list.
            anomalyAbilities.add(abilityService.getAbilityById(parseInt(abilities[i])));
        }
        //set the abilityList parameter of out Anomaly Object using the map we filled in the last step.
        anomaly.setAbilities(anomalyAbilities);
        //delcare org list and populate using the anomalyId in the String[] supplied by user, set to our anomaly
        
        //complete the same steps as above but with Organization
        List<Organization> anomalyOrganizations = new ArrayList();
        for (int i = 0; i < organizations.length; i++) {
            anomalyOrganizations.add(organizationService.getOrganizationById(parseInt(organizations[i])));
        }
        anomaly.setOrganizations(anomalyOrganizations);
        
        //add our anomaly to the database
        anomalyService.addAnomaly(anomaly);
        
        //return to the anomalies page armed with an updated anomaliesList
        model.addAttribute("anomaliesList", anomalyService.getAllAnomalies());
        return "anomaliesPage";
    }

    @RequestMapping(value = "/updateAnomalyPage", method = RequestMethod.GET)
    public String updateAnomalyPage(HttpServletRequest request, Model model) {
        
        //declare a String named input and get the String representation of the anomalyId the user requests
        //when clicking the associated update button
        String input = request.getParameter("updateAnomalyId");
        
        //parse the String into an int
        int anomalyId = parseInt(input);
        
        //Get an anomaly from memory using the ID
        Anomaly anomaly = anomalyService.getAnomalyById(anomalyId);
        
        //add the requested Anomaly object, a list of all known abilities, and a list of all know organizations
        //to the updateAnomalyPage view.
        model.addAttribute("anomaly", anomaly);
        model.addAttribute("abilities", abilityService.getAllAbilities());
        model.addAttribute("organizations", organizationService.getAllOrganizations());
        
        //return the updateAnomalyPage jsp to the browser along with the three attributed models.
        return "updateAnomalyPage";
    }

    @RequestMapping(value = "/updateAnomaly", method = RequestMethod.POST)
    public String updateAnomaly(HttpServletRequest request, Model model) {
        
        //declare a String named input and get the String representation of the anomalyId the user requests
        //when clicking the associated update button
        String input = request.getParameter("updateAnomalyId");
        
        //parse the String into an int
        int anomalyId = parseInt(input);
        
        //Get an anomaly from memory using the ID
        Anomaly anomaly = anomalyService.getAnomalyById(anomalyId);
        
        //Get the user updated anomalyName associated with the name input in the updateAnomalyPage form 
        String name = request.getParameter("name");
        //same but with description
        String description = request.getParameter("description");
        //pretty much the same process as addAnomaly @requestMapping except using update serviceLayer calls instead of add.
        String[] abilities = request.getParameterValues("abilities");
        String[] organizations = request.getParameterValues("organizations");
        List<Ability> anomalyAbilities = new ArrayList();
        List<Organization> anomalyOrganizations = new ArrayList();
        if (name.length() != 0) {
            anomaly.setAnomalyName(name);
        }
        if (description.length() != 0) {
            anomaly.setAnomalyDescription(description);
        }
        if (abilities != null && abilities.length != 0) {
            for (int i = 0; i < abilities.length; i++) {
                anomalyAbilities.add(abilityService.getAbilityById(parseInt(abilities[i])));
            }
            anomaly.setAbilities(anomalyAbilities);
        }
        if (organizations != null && organizations.length != 0) {
            for (int i = 0; i < organizations.length; i++) {
                anomalyOrganizations.add(organizationService.getOrganizationById(parseInt(organizations[i])));
            }
            anomaly.setOrganizations(anomalyOrganizations);
        }
        anomalyService.updateAnomaly(anomaly);
        model.addAttribute("anomaliesList", anomalyService.getAllAnomalies());
        return "anomaliesPage";

    }

    @RequestMapping(value = "/deleteAnomaly", method = RequestMethod.POST)
    public String deleteAnomaly(HttpServletRequest request, Model model) {
        String input = request.getParameter("deleteAnomaly");
        int anomalyId = parseInt(input);
        anomalyService.deleteAnomaly(anomalyId);
        model.addAttribute("anomaliesList", anomalyService.getAllAnomalies());
        return "anomaliesPage";
    }

    @RequestMapping(value = "/detailsAnomalyPage", method = RequestMethod.GET)
    public String detailsAnomaly(HttpServletRequest request, Model model) {
        String input = request.getParameter("detailAnomalyId");
        int anomalyId = parseInt(input);
        Anomaly anomaly = anomalyService.getAnomalyById(anomalyId);
        List<Location> locations = locationService.getLocationsByAnomalyId(anomalyId);
        model.addAttribute("locations", locations);
        model.addAttribute("anomaly", anomaly);
        return "detailsAnomalyPage";
}
    
}
