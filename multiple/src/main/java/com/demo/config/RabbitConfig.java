package com.demo.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@Configuration
public class RabbitConfig {

    public static final String ROOT_NAMESPACE = "amqp";

    public static final String MULTIPLE_QUEUE_1 = "multiple_queue_1";
    public static final String MULTIPLE_QUEUE_2 = "multiple_queue_2";
    public static final String MULTIPLE_QUEUE_3 = "multiple_queue_3";

    public static final String MULTIPLE_EXCHANGE_1 = "multiple_exchange_1";
    public static final String MULTIPLE_KEY_1 = "multiple_key_1";

    @Bean(name="firstConnectionFactory")
    @Primary
    public ConnectionFactory firstConnectionFactory(
            @Value("${spring.rabbitmq.first.host}") String host,
            @Value("${spring.rabbitmq.first.port}") int port,
            @Value("${spring.rabbitmq.first.username}") String username,
            @Value("${spring.rabbitmq.first.password}") String password,
            @Value("${spring.rabbitmq.first.virtual-host}") String virtualHost
    ){
        return getConnectionFactory(host, port, username, password, virtualHost);
    }

    @Bean(name="secondConnectionFactory")
    public ConnectionFactory secondConnectionFactory(
            @Value("${spring.rabbitmq.second.host}") String host,
            @Value("${spring.rabbitmq.second.port}") int port,
            @Value("${spring.rabbitmq.second.username}") String username,
            @Value("${spring.rabbitmq.second.password}") String password,
            @Value("${spring.rabbitmq.second.virtual-host}") String virtualHost
    ){
        return getConnectionFactory(host, port, username, password, virtualHost);
    }

    private ConnectionFactory getConnectionFactory(String host, int port, String username, String password, String virtualHost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    @Bean(name="firstRabbitTemplate")
    @Primary
    public RabbitTemplate firstRabbitTemplate(
            @Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory
    ){
        RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory);
        return firstRabbitTemplate;
    }

    @Bean(name="secondRabbitTemplate")
    public RabbitTemplate secondRabbitTemplate(
            @Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory
    ){
        RabbitTemplate secondRabbitTemplate = new RabbitTemplate(connectionFactory);
        return secondRabbitTemplate;
    }

//    @Bean(name="firstFactory")
//    public SimpleRabbitListenerContainerFactory firstFactory(
//            SimpleRabbitListenerContainerFactoryConfigurer configurer,
//            @Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory
//    ) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        configurer.configure(factory, connectionFactory);
//        return factory;
//    }
//
//    @Bean(name="secondFactory")
//    public SimpleRabbitListenerContainerFactory secondFactory(
//            SimpleRabbitListenerContainerFactoryConfigurer configurer,
//            @Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory
//    ) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        configurer.configure(factory, connectionFactory);
//        return factory;
//    }


    @Bean(name="firstQueue")
    public Queue firstQueue() {
        System.out.println("configuration firstQueue ........................");
        return new Queue(MULTIPLE_QUEUE_1);
    }

    @Bean(name="secondQueue")
    public Queue secondQueue() {
        System.out.println("configuration secondQueue ........................");
        return new Queue(MULTIPLE_QUEUE_2);
    }

    @Bean(name="defaultExchange")
    public DirectExchange defaultExchange() {
        /**
         * DirectExchange:按照routingkey分发到指定队列
         * TopicExchange:多关键字匹配
         * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
         * HeadersExchange ：通过添加属性key-value匹配
         */
        return new DirectExchange(RabbitConfig.MULTIPLE_EXCHANGE_1);
    }

    @Bean
    public Binding binding(Queue firstQueue, DirectExchange defaultExchange) {
        /** 将队列绑定到交换机 */
        return BindingBuilder.bind(firstQueue).to(defaultExchange).with(RabbitConfig.MULTIPLE_KEY_1);
    }
}
