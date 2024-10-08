package com.fontys.shopservice.repository;

import com.fontys.shopservice.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByOwnerId(Long ownerId);
    void deleteByOwnerId(Long ownerId);
}