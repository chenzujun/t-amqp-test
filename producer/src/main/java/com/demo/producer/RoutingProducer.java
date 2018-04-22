package com.demo.producer;

import com.demo.config.Config;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: Routing
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
public class RoutingProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String key) {
        String context = "hello routing msg " + key + " " + new Date();
        System.out.println(context);

        amqpTemplate.convertAndSend(Config.ROUTING_EXCHANGE, key, context);
    }
}
