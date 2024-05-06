package com.fontys.userservice.service;

import com.fontys.userservice.model.User;

import java.util.List;

public interface UserService {
    User saveUserData(User user);
    List<User> getListOfUsers();
}
