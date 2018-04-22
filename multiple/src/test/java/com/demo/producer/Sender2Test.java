package com.demo.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Sender2Test {

    @Autowired
    private Sender2 sender2;


    @Test
    public void send1() {
        sender2.send1();
    }

    @Test
    public void send2() {
        sender2.send2();
    }
}