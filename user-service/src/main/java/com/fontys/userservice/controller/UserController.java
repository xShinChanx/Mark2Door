package com.fontys.userservice.controller;

import com.fontys.userservice.model.Request.CreateAccountRequest;
import com.fontys.userservice.model.Request.CreateShopRequest;
import com.fontys.userservice.model.Request.LogInRequest;
import com.fontys.userservice.model.User;
import com.fontys.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/createShop")
    public ResponseEntity<?> createShop(@RequestBody CreateShopRequest createShopRequest) {
        ResponseEntity<?> responseEntity = restTemplate.postForObject("http://localhost:8887/shop", createShopRequest, ResponseEntity.class);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/createAccount")
    public ResponseEntity<?> saveUser(@RequestBody CreateAccountRequest createAccount) {
        try {
            User createdUser = userService.createUser(createAccount);
            if (createdUser == null) {
                throw new RuntimeException("Failed to create user.");
            }
            return ResponseEntity.ok("User '" + createdUser.getName() + "' created successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ID is already in use.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LogInRequest logInRequest) {
        String response = userService.LogIn(logInRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUserData(){
        return new ResponseEntity<>(userService.getListOfUsers(), HttpStatusCode.valueOf(200));
    }
}
