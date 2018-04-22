/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc: 主题模式 --->发送端不只按固定的routing key发送消息，而是按字符串匹配发送，接收端同样如此
 * 符号#匹配一个或多个词，符号*匹配一个词。
 * @author: mustang
 * @create: 2018-04-22
 */
@Configuration
public class TopicsConfig {

    public static final String PRODUCER_TOPICS = "topics";

    public static final String TOPIC_A = "topic_A";

    public static final String TOPIC_B = "topic_B";

    public static final String TOPIC_EXCHANGE = "topic.exchange";

    private static final String TOPIC_SPRING = "#.spring.#";
    private static final String TOPIC_RABBIT = "*.*.rabbit";
    private static final String TOPIC_DEMO = "com.example.#";

    @Bean
    public Queue topicA() {
        return new Queue(TOPIC_A);
    }

    @Bean
    public Queue topicB() {
        return new Queue(TOPIC_B);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindingTopicA(Queue topicA, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicA).to(topicExchange).with(TOPIC_SPRING);
    }

    @Bean
    public Binding bindingTopicB(Queue topicB, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicB).to(topicExchange).with(TOPIC_RABBIT);
    }

    @Bean
    public Binding bindingTopicB2(Queue topicB, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicB).to(topicExchange).with(TOPIC_DEMO);
    }
}
