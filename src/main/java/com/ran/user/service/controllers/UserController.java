package com.ran.user.service.controllers;

import com.ran.user.service.entities.User;
import com.ran.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


  // create user
    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user){
        User user1 =  userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    ResponseEntity<User> getSingleUser(@PathVariable String userId){
     User user = userService.getUserById(userId);
     return ResponseEntity.ok(user);
    }

    @GetMapping
    ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
