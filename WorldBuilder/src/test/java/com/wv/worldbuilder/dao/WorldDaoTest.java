/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wv.worldbuilder.dao;

import com.wv.worldbuilder.model.World;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author wgv85
 */
public class WorldDaoTest {
    
    private WorldDao dao;
    
    public WorldDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("worldDao", WorldDao.class);
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteWorld() {
        World world = new World();
        world.setName("Pangea");
        world.setDescription("Pangea Description");
        dao.addWorld(world);
        assertEquals(1, dao.getAllWorlds().size());
        
    }
    
    @Test
    public void addUpdateWorld() {
        
        World world = new World();
        dao.addWorld(world);
        World worldTwo = dao.getWorldById(world.getId());
        assertEquals(world, worldTwo);
        
    }
    
    @Test
    public void getAllWorlds() {
        World world = new World();
        World worldTwo = new World();
        dao.addWorld(world);
        dao.addWorld(worldTwo);
        List<World> worlds = new ArrayList<>();
        worlds = dao.getAllWorlds();
        assertEquals(2, worlds.size());
        
    }
    
    @Test
    public void searchWorlds() {
        World world = new World();
        world.setName("test");
        dao.addWorld(world);
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.WORLD_NAME, "test");
        List<World> worldList = dao.searchWorlds(criteria);
        assertEquals(1, worldList.size());
    }
    
}
