/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@Configuration
public class TtlConfig {

    public static final String PRODUCER_TTL = "ttl";

    public static final String TTL_QUEUE = "ttl_queue";
    public static final String DEAD_QUEUE = "dead_queue";

    public static final String DEAD_EXCHANGE="dl_exchange";
    public static final String DEAD_EXCHANGE_ROUTING_KEY="DL_R_KEY";
    public static final String EXCHANGE_ROUTING_KEY="TTL_R_KEY";

    /**
     * 死信队列交换机标识符
     */
    private static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    /**
     * 死信队列交换机绑定键标识符
     */
    private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    /**
     * 队列消息过期时间
     */
    private static final String X_MESSAGE_TTL ="x-message-ttl";

    @Bean
    public Queue queue() {
        Map<String, Object> args = new HashMap<>(3);
        //x-dead-letter-exchange    死信交换机
        args.put(X_DEAD_LETTER_EXCHANGE, DEAD_EXCHANGE);
        //x-dead-letter-routing-key    死信路由键
        args.put(X_DEAD_LETTER_ROUTING_KEY, DEAD_EXCHANGE_ROUTING_KEY);
        //x-message-ttl 队列过期时间
        args.put(X_MESSAGE_TTL,30000);
        return QueueBuilder.durable(TTL_QUEUE).withArguments(args).build();
    }

    @Bean("deadLetterQueue")
    public Queue deadLetterQueue() {
        return new Queue(DEAD_QUEUE);
    }

    /**
     * 死信队列交换机
     *
     * @return the exchange
     */
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_EXCHANGE);
    }

    @Bean
    public Binding bindingQueue(Queue queue, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(queue).to(deadLetterExchange).with(EXCHANGE_ROUTING_KEY);
    }

    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue,DirectExchange deadLetterExchange){
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(DEAD_EXCHANGE_ROUTING_KEY);
    }



}
