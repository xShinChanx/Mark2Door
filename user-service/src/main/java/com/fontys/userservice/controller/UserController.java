package com.fontys.userservice.controller;


import com.fontys.userservice.model.UserDetails;
import com.fontys.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private RabbitTemplate template;

    @GetMapping
    public List<UserDetails> getAllAddresses() {
        return userService.getAllAddresses();
    }

    @DeleteMapping("/{userID}")
    public void deleteAddressByUserID(@PathVariable Long userID) {
        userService.deleteAddressByUserID(userID);
    }


    @PostMapping
    public UserDetails createOrUpdateAddress(@RequestBody UserDetails userDetails) {
        return userService.createOrUpdateAddress(userDetails);
    }

    // GET endpoint to retrieve user details by userID
    @GetMapping("/{userID}")
    public Optional<UserDetails> getUserDetailsByUserID(@PathVariable Long userID) {
        return userService.getUserDetailsByUserID(userID);
    }
}
