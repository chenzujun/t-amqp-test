package com.demo.producer;

import com.demo.config.Config;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 最简单的模式，HELLO WORLD
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
public class SimpleQueueProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String sendMsg = "hello " + new Date();
        System.out.println("SimpleQueueProducer : " + sendMsg);

        this.amqpTemplate.convertAndSend(Config.SIMPLE_QUEUE, sendMsg);
    }

}
