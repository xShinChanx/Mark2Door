package com.fontys.userservice.service;

import com.fontys.userservice.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getListOfUsers();
}
