/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.service;

import com.sg.superherosightingmvc.dao.UserDao;
import com.sg.superherosightingmvc.model.User;
import java.util.List;

/**
 *
 * @author wgv85
 */
public class UserServiceLayerImpl implements UserServiceLayer {
    
    private UserDao dao;

    public UserServiceLayerImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User addUser(User newUser) {
        return dao.addUser(newUser);
    }

    @Override
    public void deleteUser(String username) {
        dao.deleteUser(username);
    }

    @Override
    public void deleteAuthority(String username) {
        dao.deleteAuthority(username);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public User getUserById(int id) {
        return dao.getUserById(id);
} 
    
}
