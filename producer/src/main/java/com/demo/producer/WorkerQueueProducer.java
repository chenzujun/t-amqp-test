package com.demo.producer;

import com.demo.config.Config;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: Work queues
 * @author: mustang
 * @create: 2018-04-22
 **/
@Component
public class WorkerQueueProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Scheduled(fixedDelay = 5000, initialDelay = 500)
    public void send() {
        String sendMsg = "worker queue " + new Date();
        System.out.println("WorkerQueueProducer : " + sendMsg);

        this.amqpTemplate.convertAndSend(Config.WORKER_QUEUE, sendMsg);
    }

}
