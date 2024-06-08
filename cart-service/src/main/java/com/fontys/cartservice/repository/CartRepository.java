package com.fontys.cartservice.repository;

import com.fontys.cartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserIDAndItemID(Long userID, Long itemID);

    @Query("SELECT c.itemID FROM Cart c WHERE c.userID = :userID")
    List<Long> findItemIDsByUserID(@Param("userID") Long userID);
}
