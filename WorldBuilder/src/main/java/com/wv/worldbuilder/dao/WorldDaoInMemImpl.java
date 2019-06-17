/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wv.worldbuilder.dao;

import com.wv.worldbuilder.model.World;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author wgv85
 */

// This is an in memory implementation of a dao for this project that I can use to set up the basic design and flow.
public class WorldDaoInMemImpl implements WorldDao {
    
    private Map<Integer, World> worldMap = new HashMap<>();
    
    private static int worldIdCounter = 0;

    @Override
    public World addWorld(World world) {
        
        world.setId(worldIdCounter);
        
        worldIdCounter++;
        worldMap.put(world.getId(), world);
        return world;
    }

    @Override
    public void removeWorld(int id) {
        worldMap.remove(id);
    }

    @Override
    public void updateWorld(World world) {
        worldMap.put(world.getId(), world);
    }

    @Override
    public List<World> getAllWorlds() {
        Collection<World> w = worldMap.values();
        return new ArrayList(w);
    }

    @Override
    public World getWorldById(int id) {
        return worldMap.get(id);
    }

    @Override
    public List<World> searchWorlds(Map<SearchTerm, String> criteria) {
        String worldNameSearchCriteria = criteria.get(SearchTerm.WORLD_NAME);
        
        Predicate<World> worldNameMatchPredicate;
        
        Predicate<World> truePredicate = (w) -> {
            return true;
        };
        
        if (worldNameSearchCriteria == null || worldNameSearchCriteria.isEmpty()) {
            worldNameMatchPredicate = truePredicate;
        } else {
            worldNameMatchPredicate = (w) -> w.getName().equals(worldNameSearchCriteria);
        }
        
        return worldMap.values().stream().filter(worldNameMatchPredicate).collect(Collectors.toList());
    }
    
    
    
}
