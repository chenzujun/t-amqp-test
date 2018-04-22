package com.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
public class Config {
    public static final String SIMPLE_QUEUE="simple_queue";

    public static final String WORKER_QUEUE="worker_queue";

    public static final String FANOUT_A = "fanout_A";
    public static final String FANOUT_B = "fanout_B";
    public static final String FANOUT_C = "fanout_C";

    public static final String TOPIC_A = "topic_A";
    public static final String TOPIC_B = "topic_B";

    public static final String ROUTING_ERROR_QUEUE = "routing_error";
    public static final String ROUTING_INFO_QUEUE = "routing_info";

    public static final String DEAD_QUEUE = "dead_queue";
    public static final String DEAD_MSG_QUEUE = "dead_msg_queue";

}
