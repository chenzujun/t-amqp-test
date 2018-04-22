package com.demo.controller;

import com.demo.config.Config;
import com.demo.producer.SimpleQueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@RestController
@RequestMapping(Config.ROOT_NAMESPACE)
public class SimpleQueueController {

    @Autowired
    private SimpleQueueProducer producer;

    @RequestMapping(Config.PRODUCER_HELLO_QUEUE)
    public void hello() {
        producer.send();
    }
}
