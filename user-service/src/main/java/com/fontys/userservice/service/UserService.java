package com.fontys.userservice.service;

import com.fontys.userservice.model.Request.CreateAccountRequest;
import com.fontys.userservice.model.Request.LogInRequest;
import com.fontys.userservice.model.User;

import java.util.List;

public interface UserService {
    User createUser(CreateAccountRequest createAccount);
    List<User> getListOfUsers();
    String LogIn (LogInRequest logInRequest);
    }
