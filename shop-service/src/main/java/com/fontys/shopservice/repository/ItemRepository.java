package com.fontys.shopservice.repository;

import com.fontys.shopservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>  {
}
