/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wv.worldbuilder.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author wgv85
 */

@Controller
public class HomeController {
    
   @RequestMapping(value = {"/", "/index"}, method=RequestMethod.GET)
    public String displayHomePage() {
        return "index";
    }
    
}
