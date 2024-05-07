package com.fontys.userservice.service.ServiceImp;

import com.fontys.userservice.model.Request.CreateAccountRequest;
import com.fontys.userservice.model.Request.LogInRequest;
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
    public User createUser(CreateAccountRequest createAccount) {
        if (createAccount != null && (isRoleValid(createAccount.getRole()))) {
            if (userRepository.findByEmail(createAccount.getEmail()) != null) {
                throw new DataIntegrityViolationException("Email ID is already in use.");
            }
            User user = new User();
            user.setEmail(createAccount.getEmail());
            user.setRole(createAccount.getRole());
            user.setName(createAccount.getName());
            user.setPassword(createAccount.getPassword());
            return userRepository.save(user);
        }
        return null;
    }

    private boolean isRoleValid(String role) {
        return role != null && (role.equals("Customer") || role.equals("ShopOwner"));
    }

    @Override
    public List<User> getListOfUsers() {return  userRepository.findAll();}

    @Override
    public String LogIn (LogInRequest logInCredentials){
        User userCheck = userRepository.findByEmail(logInCredentials.getEmail());
        if (logInCredentials.getPassword().equals(userCheck.getPassword())){
            if (userCheck.getRole().equals("Customer")){
                return "Welcome Customer";
            }
            else{
                return "Welcome ShopOwner";
            }
        }
        return "Login Failed";
    }
}
