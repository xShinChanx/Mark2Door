package com.fontys.userservice.repository;

import com.fontys.userservice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUserID(Long userID);
    void deleteByUserID(Long userID);
}
