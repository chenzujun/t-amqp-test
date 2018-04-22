package com.demo.controller;

import com.demo.config.RabbitConfig;
import com.demo.producer.Sender;
import com.demo.producer.Sender2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mustang
 * @create: 2018-04-22
 **/
@RestController
@RequestMapping(RabbitConfig.ROOT_NAMESPACE)
public class Controller {

    @RequestMapping("/multiple")
    public void send() {
    }

}
