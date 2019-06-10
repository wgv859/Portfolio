/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingmvc.service;

import com.sg.superherosightingmvc.model.User;
import java.util.List;

/**
 *
 * @author wgv85
 */
public interface UserServiceLayer {
    
    public User addUser(User newUser);

    public void deleteUser(String username);

    public void deleteAuthority(String username);

    public List<User> getAllUsers();

    public void updateUser(User user);

public User getUserById(int id);
    
}
