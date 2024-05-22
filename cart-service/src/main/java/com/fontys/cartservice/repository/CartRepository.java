package com.fontys.cartservice.repository;

import com.fontys.cartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserIDAndItemID(Long userID, Long itemID);
}
