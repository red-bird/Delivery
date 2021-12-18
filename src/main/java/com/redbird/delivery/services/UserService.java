package com.redbird.delivery.services;

import com.redbird.delivery.models.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findByUsername(String username);
    public User saveUser(User user);
    public User updateUser(User user);
    boolean activateUser(String code);
    boolean updateProfile(User user, String password, String email);
}
