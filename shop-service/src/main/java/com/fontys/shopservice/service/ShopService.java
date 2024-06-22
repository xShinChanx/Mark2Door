package com.fontys.shopservice.service;

import com.fontys.shopservice.model.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopService {
    String saveShopData(Shop shop);
    List<Shop> getListOfShops();
    Optional<Long> getShopIdByOwnerId(Long ownerId);
    String deleteShopByOwnerId(Long ownerId);

    }
