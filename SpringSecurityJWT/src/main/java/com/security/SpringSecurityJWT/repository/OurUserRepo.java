package com.security.SpringSecurityJWT.repository;

import com.security.SpringSecurityJWT.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {
    Optional<OurUsers> findByEmail(String email);

    Optional<OurUsers> findById(Long userId);
}
