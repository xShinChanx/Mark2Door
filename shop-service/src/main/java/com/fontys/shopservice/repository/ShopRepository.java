package com.fontys.shopservice.repository;

import com.fontys.shopservice.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
