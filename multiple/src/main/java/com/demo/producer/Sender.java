package com.demo.producer;

import com.demo.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
public class Sender {

    @Resource(name="firstRabbitTemplate")
    private RabbitTemplate amqpTemplate;

    public void send1() {
        String context = "hello1 " + new Date();
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend(RabbitConfig.MULTIPLE_QUEUE_1, context);
    }

    public void send2() {
        String context = "hello2 " + new Date();
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend(RabbitConfig.MULTIPLE_QUEUE_2, context);
    }
}
