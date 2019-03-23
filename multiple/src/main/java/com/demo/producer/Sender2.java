package com.demo.producer;

import com.demo.config.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
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
public class Sender2 {

    @Resource(name="secondRabbitTemplate")
    private RabbitTemplate secondRabbitTemplate;

    public void send1() {
        String context = "hello1 " + new Date();
        System.out.println("Sender : " + context);
        this.secondRabbitTemplate.convertAndSend(RabbitConfig.MULTIPLE_QUEUE_1, context);
    }

    public void send2() {
        String context = "hello2 " + new Date();
        System.out.println("Sender : " + context);
        this.secondRabbitTemplate.convertAndSend(RabbitConfig.MULTIPLE_QUEUE_2, context);
    }
}
