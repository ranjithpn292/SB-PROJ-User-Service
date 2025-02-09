package com.ran.user.service.controllers;

import com.ran.user.service.entities.User;
import com.ran.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


  // create user
    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user){
        User user1 =  userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    ResponseEntity<User> getSingleUser(@PathVariable String userId){
     User user = userService.getUserById(userId);
     return ResponseEntity.ok(user);
    }

    // creatingf fallack methods for ratinngHotelService circuit
    public ResponseEntity<User> ratingHotelFallback(String UserId, Exception ex){
        log.info("Fallback is executed because service is down:", ex.getMessage());
        User user = new User();
        user.setName("dummy name");
        user.setEmail("dummy@gmai.com");
        user.setAbout("dummy about");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
