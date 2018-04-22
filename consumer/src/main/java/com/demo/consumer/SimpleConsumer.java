package com.demo.consumer;

import com.demo.config.Config;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
@RabbitListener(queues = Config.SIMPLE_QUEUE)
public class SimpleConsumer {

    @RabbitHandler
    public void process(String msg) throws Exception {
        System.out.println("SimpleConsumer  : " + msg);
        // 采用Spring amqp默认设置，当接收消息后抛出Exception消息不会从MQ中丢失，
        // 除非明确设置default-requeue-rejected=false，默认为true
//        throw new Exception("接收失败");
    }
}
