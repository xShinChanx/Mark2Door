package com.fontys.userservice.service.ServiceImp;

import com.fontys.userservice.model.UserDetails;
import com.fontys.userservice.repository.UserRepository;
import com.fontys.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;


    // Method to create or update an address
    public UserDetails createOrUpdateAddress(UserDetails userDetails) {
        Optional<UserDetails> existingAddress = userRepository.findByUserID(userDetails.getUserID());
        if (existingAddress.isPresent()) {
            UserDetails addressToUpdate = existingAddress.get();
            addressToUpdate.setAddress(userDetails.getAddress());
            addressToUpdate.setCity(userDetails.getCity());
            addressToUpdate.setHouseNo(userDetails.getHouseNo());
            return userRepository.save(addressToUpdate);
        } else {
            return userRepository.save(userDetails);
        }
    }

    public void deleteAddressByUserID(Long userID) {
        Optional<UserDetails> userDetails = userRepository.findByUserID(userID);
        userRepository.delete(userDetails.get());
    }

    // Method to get all addresses
    public List<UserDetails> getAllAddresses() {
        return userRepository.findAll();
    }

    // Method to retrieve details by userID
    public Optional<UserDetails> getUserDetailsByUserID(Long userID) {
        return userRepository.findByUserID(userID);
    }
}
