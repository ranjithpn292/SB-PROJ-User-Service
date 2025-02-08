package com.ran.user.service.services;

import com.ran.user.service.entities.User;

import java.util.List;

public interface UserService {

    // user operations

    //save user
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(String userId);
}
