package com.fontys.userservice.rabbit;

import com.fontys.userservice.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class consumer {

    @Autowired
    UserService userService;

    @RabbitListener(queues = "user-queue")
    public void consumerMessageFromQueue(Long longId){
        System.out.println(longId);
        userService.deleteAddressByUserID(longId);
    }
}