package com.demo.producer;

import com.demo.config.TtlConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
public class TtlProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context = "Send: hello " + new Date();
        System.out.println(context);
        amqpTemplate.convertAndSend(TtlConfig.DEAD_EXCHANGE, TtlConfig.EXCHANGE_ROUTING_KEY, context);
    }
}
