package com.demo.consumer.subscribe;

import com.demo.config.Config;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: Subscribe
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
@RabbitListener(queues = Config.FANOUT_A)
public class SubscribeConsumer1 {

    @RabbitHandler
    public void receiver(String context) {
        System.out.println("SubscribeConsumer1 FANOUT_A receiver:" + context);
    }
}
