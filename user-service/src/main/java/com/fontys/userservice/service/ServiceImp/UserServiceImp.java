package com.fontys.userservice.service.ServiceImp;

import com.fontys.userservice.model.User;
import com.fontys.userservice.repository.UserRepository;
import com.fontys.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (user.getRoles().equals("Customer") || user.getRoles().equals("ShopOwner")) {
            // Check if the email ID is already in use
            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new DataIntegrityViolationException("Email ID is already in use.");
            }
            return userRepository.save(user);
        }
        return null;
    }
    @Override
    public List<User> getListOfUsers() {return  userRepository.findAll();}

}
