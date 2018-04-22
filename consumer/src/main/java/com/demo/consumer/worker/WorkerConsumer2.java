package com.demo.consumer.worker;

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
@RabbitListener(queues = Config.WORKER_QUEUE)
public class WorkerConsumer2 {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("WorkerConsumer2  : " + msg);
    }
}
