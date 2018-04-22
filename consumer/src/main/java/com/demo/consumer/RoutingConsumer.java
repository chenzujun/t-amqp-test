package com.demo.consumer;

import com.demo.config.Config;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
public class RoutingConsumer {

    @RabbitListener(queues = Config.ROUTING_ERROR_QUEUE)
    public void receive1(String msg) {
        System.out.println("RoutingConsumer  ROUTING_ERROR_QUEUE receive : " + msg);
    }

    @RabbitListener(queues = Config.ROUTING_INFO_QUEUE)
    public void receive2(String msg) {
        System.out.println("RoutingConsumer  ROUTING_INFO_QUEUE receive : " + msg);
    }
}
