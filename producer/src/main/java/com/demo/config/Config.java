package com.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@Configuration
public class Config {

    public static final String ROOT_NAMESPACE = "amqp";
    public static final String PRODUCER_HELLO_QUEUE = "hello";
    public static final String PRODUCER_ROUTING = "routing";

    public static final String SIMPLE_QUEUE = "simple_queue";

    public static final String WORKER_QUEUE = "worker_queue";

    public static final String FANOUT_A = "fanout_A";
    public static final String FANOUT_B = "fanout_B";
    public static final String FANOUT_C = "fanout_C";

    public static final String ROUTING_ERROR_QUEUE = "routing_error";
    public static final String ROUTING_INFO_QUEUE = "routing_info";

    private static final String ROUTING_ERROR_KEY = "error";
    private static final String ROUTING_INFO_KEY = "info";

    public static final String FANOUT_EXCHANGE = "fanout.custom";

    public static final String ROUTING_EXCHANGE = "routing.exchange";


    @Bean(name = "simpleQueue")
    public Queue simpleQueue() {
        return new Queue(SIMPLE_QUEUE);
    }

    @Bean(name = "workerQueue")
    public Queue workerQueue() {
        return new Queue(WORKER_QUEUE);
    }

    @Bean
    public Queue queueA() {
        return new Queue(FANOUT_A);
    }

    @Bean
    public Queue queueB() {
        return new Queue(FANOUT_B);
    }

    @Bean
    public Queue queueC() {
        return new Queue(FANOUT_C);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding bindingExchangeA(Queue queueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeB(Queue queueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeC(Queue queueC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueC).to(fanoutExchange);
    }


    @Bean
    public Queue queueRoutingError() {
        return new Queue(ROUTING_ERROR_QUEUE);
    }

    @Bean
    public Queue queueRoutingInfo() {
        return new Queue(ROUTING_INFO_QUEUE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(ROUTING_EXCHANGE);
    }

    @Bean
    public Binding bindingRoutingError(Queue queueRoutingError, DirectExchange directExchange) {
        return BindingBuilder.bind(queueRoutingError).to(directExchange).with(ROUTING_ERROR_KEY);
    }

    @Bean
    public Binding bindingRoutingInfo(Queue queueRoutingInfo, DirectExchange directExchange) {
        return BindingBuilder.bind(queueRoutingInfo).to(directExchange).with(ROUTING_INFO_KEY);
    }
}
