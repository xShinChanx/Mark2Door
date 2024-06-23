package com.fontys.userservice.service;

import com.fontys.userservice.model.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDetails createOrUpdateAddress(UserDetails userDetails);
    void deleteAddressByUserID(Long userID);
    List<UserDetails> getAllAddresses();
    Optional<UserDetails> getUserDetailsByUserID(Long userID);
    }
