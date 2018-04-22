package com.demo.producer;

import com.demo.config.TtlConfig;
import com.demo.config.TtlmsgConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
public class TtlmsgProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String expiration) {
        String context = "TtlmsgProducer producer : "+ new Date();
        System.out.println(context);
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setExpiration(expiration);
            return message;
        } ;
        amqpTemplate.convertAndSend(TtlmsgConfig.DEAD_MSG_EXCHANGE, TtlmsgConfig.EXCHANGE_ROUTING_KEY,context,messagePostProcessor);
    }
}
