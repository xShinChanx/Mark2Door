package com.fontys.shopservice.service.ServiceImp;

import com.fontys.shopservice.model.Shop;
import com.fontys.shopservice.repository.ShopRepository;
import com.fontys.shopservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ShopServiceImp implements ShopService {
    @Autowired
    ShopRepository shopRepository;

    @Override
    public String saveShopData(Shop shop) {
        Optional<Shop> existingShop = shopRepository.findByOwnerId(shop.getOwnerId());
        if (existingShop.isPresent()) {
            return "Owner already has a shop";
        } else {
            shopRepository.save(shop);
            return "Shop created successfully";
        }
    }

    @Override
    public Optional<Long> getShopIdByOwnerId(Long ownerId) {
        Optional<Shop> shop = shopRepository.findByOwnerId(ownerId);
        return shop.map(Shop::getId);
    }

    @Override
    public List<Shop> getListOfShops() {
        return shopRepository.findAll();
    }
}
