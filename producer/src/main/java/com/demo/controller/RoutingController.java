package com.demo.controller;

import com.demo.config.Config;
import com.demo.producer.RoutingProducer;
import com.demo.producer.SimpleQueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@RestController
@RequestMapping(Config.ROOT_NAMESPACE)
public class RoutingController {

    @Autowired
    private RoutingProducer producer;

    @RequestMapping(Config.PRODUCER_ROUTING)
    public void send() throws Exception {
        for (int i = 0; i < 100; i++) {
            producer.send(getRoutingKey());
        }
    }

    private static String getRoutingKey() {
        String[] arrs = {"info", "error", "debug"};
        Random random = new Random();
        return arrs[random.nextInt(3)];
    }
}
