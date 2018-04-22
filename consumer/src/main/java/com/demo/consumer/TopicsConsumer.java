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
public class TopicsConsumer {

    @RabbitListener(queues = Config.TOPIC_A)
    public void receive1(String msg) {
        System.out.println("TopicsConsumer  TOPIC_A receive : " + msg);
    }

    @RabbitListener(queues = Config.TOPIC_B)
    public void receive2(String msg) {
        System.out.println("TopicsConsumer  TOPIC_B receive : " + msg);
    }
}
