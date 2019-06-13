/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wv.worldbuilder.model;

import java.util.List;

/**
 *
 * @author wgv85
 */
public class World {
    // unique Id number
    int worldId;

    String name;
    
    String description;
    
    List<Country> countries;
    
    List<City> cities;
    
    List<Race> races;
    
    List<Character> characters;
    
}
