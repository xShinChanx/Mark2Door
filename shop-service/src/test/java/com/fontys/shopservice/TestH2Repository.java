package com.fontys.shopservice;

import com.fontys.shopservice.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestH2Repository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByOwnerId(Long ownerId);
}
