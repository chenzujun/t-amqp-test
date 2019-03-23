package com.demo.producer;

import com.demo.config.Config;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: Publish
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
public class PublishProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

//    @Scheduled(fixedDelay = 10000, initialDelay = 500)
    public void send(){
        String context = "PublishProducer : "+ new Date();
        System.out.println(context);
        amqpTemplate.convertAndSend(Config.FANOUT_EXCHANGE,"",context);
    }
}
