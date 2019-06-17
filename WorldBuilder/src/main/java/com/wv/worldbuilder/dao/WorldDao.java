/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wv.worldbuilder.dao;

import com.wv.worldbuilder.model.World;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wgv85
 */
public interface WorldDao {
    
    public World addWorld(World world);
    
    public void removeWorld(int id);
    
    public void updateWorld(World world);
    
    public List<World> getAllWorlds();
    
    public World getWorldById(int id);
    
    public List<World> searchWorlds(Map<SearchTerm, String> criteria);
    
}
