package com.fontys.userservice.controller;

import com.fontys.userservice.model.User;
import com.fontys.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            if (createdUser == null) {
                throw new RuntimeException("Failed to create user.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUserData(){
        return new ResponseEntity<>(userService.getListOfUsers(), HttpStatusCode.valueOf(200));
    }
}
