package com.demo.controller;

import com.demo.config.Config;
import com.demo.config.TopicsConfig;
import com.demo.producer.RoutingProducer;
import com.demo.producer.TopicsProducer;
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
public class TopicsController {

    @Autowired
    private TopicsProducer producer;

    private String[] arrs = {
            "info.spring.core",
            "org.com.spring",
            "spring.data",
            "spring",
            "com.rabbit",
            "a.b.rabbit",
            "rabbit.host",
            "com.example",
            "com.example.springboot.desc",
            "a.com.example"
    };

    @RequestMapping(TopicsConfig.PRODUCER_TOPICS)
    public void send() {
        for (int i = 0; i < 10; i++) {
            producer.send(arrs[i]);
        }
    }

}
