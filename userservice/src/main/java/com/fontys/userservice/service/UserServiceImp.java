package com.fontys.userservice.service;

import com.fontys.userservice.model.User;
import com.fontys.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class UserServiceImp implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUserData (User user){return userRepository.save(user);}

    @Override
    public List<User> getListOfUsers() {return  userRepository.findAll();}
}
