package com.fontys.shopservice.rabbit;

import com.fontys.shopservice.model.Shop;
import com.fontys.shopservice.service.ShopService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class consumer {

    @Autowired
    ShopService shopService;

    @RabbitListener(queues = "shop-queue")
    public void consumerMessageFromQueue(Shop shop){
        shopService.saveShopData(shop);
        System.out.println(shop);
    }
}
