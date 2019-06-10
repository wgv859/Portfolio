/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.controller;

import com.sg.superherosightingmvc.model.User;
import com.sg.superherosightingmvc.service.AbilityServiceLayer;
import com.sg.superherosightingmvc.service.AnomalyServiceLayer;
import com.sg.superherosightingmvc.service.LocationServiceLayer;
import com.sg.superherosightingmvc.service.OrganizationServiceLayer;
import com.sg.superherosightingmvc.service.SightingServiceLayer;
import com.sg.superherosightingmvc.service.UserServiceLayer;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author wgv85
 */
@Controller
public class UserController {
    
    private AnomalyServiceLayer anomalyService;
    private AbilityServiceLayer abilityService;
    private LocationServiceLayer locationService;
    private OrganizationServiceLayer organizationService;
    private SightingServiceLayer sightingService;
    private UserServiceLayer userService;
    private PasswordEncoder encoder;
    
    
    @Inject
    public UserController(AnomalyServiceLayer anomalyService, 
            AbilityServiceLayer abilityService, 
            LocationServiceLayer locationService, 
            OrganizationServiceLayer organizationService, 
            SightingServiceLayer sightingService, 
            UserServiceLayer userService, 
            PasswordEncoder encoder) {
        
        this.anomalyService = anomalyService;
        this.abilityService = abilityService;
        this.locationService = locationService;
        this.organizationService = organizationService;
        this.sightingService = sightingService;
        this.userService = userService;
        this.encoder = encoder;
    
    }
    
    @RequestMapping(value = "/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "displayUserList";
    }
    
    @RequestMapping(value = "/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Map<String, Object> model) {
        return "addUserForm";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request) {
        
        User newUser = new User();
        
        
        newUser.setUsername(request.getParameter("username"));
        String clearPw = request.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        newUser.setPassword(hashPw);
        
        
        newUser.addAuthority("ROLE_USER");
        if(null != request.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
        }
        
        userService.addUser(newUser);
        
        return "redirect:displayUserList";
    }
    
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("username") String username,
            Map<String, Object> model) {
        userService.deleteUser(username);
        return "redirect:displayUserList";
    }
    
}
