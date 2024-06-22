package com.fontys.shopservice.repository;

import com.fontys.shopservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByShopId(int shopId);
    void deleteByShopId(int shopId); // Add this method
}