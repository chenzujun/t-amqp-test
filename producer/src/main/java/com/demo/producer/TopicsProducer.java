package com.demo.producer;

import com.demo.config.Config;
import com.demo.config.TopicsConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: topics
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
public class TopicsProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String key) {
        String context = key + " " + new Date();
        System.out.println(context);
        amqpTemplate.convertAndSend(TopicsConfig.TOPIC_EXCHANGE, key, context);
    }
}
