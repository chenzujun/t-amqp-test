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
public class TtlmsgConfig {

    public static final String TTL_MSG_QUEUE = "ttl_msg_queue";
    public static final String DEAD_MSG_QUEUE = "dead_msg_queue";

    public static final String DEAD_MSG_EXCHANGE="dl_msg_exchange";
    public static final String DEAD_EXCHANGE_ROUTING_KEY="DL_R_KEY";
    public static final String EXCHANGE_ROUTING_KEY="TTL_MSG_KEY";

    /**
     * 死信队列交换机标识符
     */
    private static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    /**
     * 死信队列交换机绑定键标识符
     */
    private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    @Bean
    public Queue queue() {
        Map<String, Object> args = new HashMap<>(3);
        //x-dead-letter-exchange    死信交换机
        args.put(X_DEAD_LETTER_EXCHANGE, DEAD_MSG_EXCHANGE);
        //x-dead-letter-routing-key    死信路由键
        args.put(X_DEAD_LETTER_ROUTING_KEY, DEAD_EXCHANGE_ROUTING_KEY);
        return QueueBuilder.durable(TTL_MSG_QUEUE).withArguments(args).build();
    }

    @Bean("deadMsgQueue")
    public Queue deadMsgQueue() {
        return new Queue(DEAD_MSG_QUEUE);
    }

    /**
     * 死信队列交换机
     *
     * @return the exchange
     */
    @Bean
    public DirectExchange deadMsgExchange() {
        return new DirectExchange(DEAD_MSG_EXCHANGE);
    }

    @Bean
    public Binding bindingQueue(Queue queue, DirectExchange deadMsgExchange) {
        return BindingBuilder.bind(queue).to(deadMsgExchange).with(EXCHANGE_ROUTING_KEY);
    }

    @Bean
    public Binding deadMsgBinding(Queue deadMsgQueue,DirectExchange deadMsgExchange){
        return BindingBuilder.bind(deadMsgQueue).to(deadMsgExchange).with(DEAD_EXCHANGE_ROUTING_KEY);
    }

}
