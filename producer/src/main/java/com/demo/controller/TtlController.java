package com.demo.controller;

import com.demo.config.Config;
import com.demo.config.TopicsConfig;
import com.demo.config.TtlConfig;
import com.demo.producer.TopicsProducer;
import com.demo.producer.TtlProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@RestController
@RequestMapping(Config.ROOT_NAMESPACE)
public class TtlController {

    @Autowired
    private TtlProducer producer;

    @RequestMapping(TtlConfig.PRODUCER_TTL)
    public void send() {
        producer.send();
    }

}
