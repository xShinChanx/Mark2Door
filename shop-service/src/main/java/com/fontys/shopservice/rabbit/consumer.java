package com.fontys.shopservice.rabbit;

import com.fontys.shopservice.model.Shop;
import com.fontys.shopservice.service.ItemService;
import com.fontys.shopservice.service.ShopService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class consumer {

    @Autowired
    ShopService shopService;

    @Autowired
    ItemService itemService;

    @RabbitListener(queues = "shop-queue")
    public void consumerMessageFromQueue(Long id){
        System.out.println(id);
        shopService.deleteShopByOwnerId(id);
    }
}
