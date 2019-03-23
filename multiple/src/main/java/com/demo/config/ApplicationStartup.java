package com.demo.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.IOException;

/**
 * @Description
 * @Author chenjun
 * @Date 2018/4/23
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("ApplicationStartup------");
        ConnectionFactory connectionFactory1 =
                contextRefreshedEvent.getApplicationContext().getBean("firstConnectionFactory", ConnectionFactory.class);
        ConnectionFactory connectionFactory2 =
                contextRefreshedEvent.getApplicationContext().getBean("secondConnectionFactory", ConnectionFactory.class);

        Channel channel1 = connectionFactory1.createConnection().createChannel(true);
        Channel channel2 = connectionFactory2.createConnection().createChannel(true);
        try {
            channel1.queueDeclare(RabbitConfig.MULTIPLE_QUEUE_1, true, false, false, null);
            channel2.queueDeclare(RabbitConfig.MULTIPLE_QUEUE_3, true, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
