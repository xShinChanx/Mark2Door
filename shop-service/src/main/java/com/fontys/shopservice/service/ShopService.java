package com.fontys.shopservice.service;

import com.fontys.shopservice.model.Shop;

import java.util.List;

public interface ShopService {
    Shop saveShopData(Shop shop);
    List<Shop> getListOfShops();

}
