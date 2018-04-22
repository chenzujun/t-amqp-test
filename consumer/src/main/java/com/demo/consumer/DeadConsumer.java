/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.demo.consumer;

import com.demo.config.Config;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
public class DeadConsumer {

    @RabbitListener(queues = Config.DEAD_QUEUE)
    public void receiver(Message msg, Channel channel) throws IOException{
        System.out.println("DeadConsumer Receiver: " + new Date() + "->" + new String(msg.getBody()));
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(),true);
    }

    @RabbitListener(queues = Config.DEAD_MSG_QUEUE)
    public void receiver2(Message msg, Channel channel) throws IOException {
        System.out.println("Dead Receiver: " + new Date() + "->" + new String(msg.getBody()));
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
    }
}
