package com.shenjies88.practice.rocketmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.math.BigDecimal;

import static com.shenjies88.practice.rocketmq.Constant.*;

/**
 * @author shenjies88
 * @since 2021/4/22-10:04 AM
 */
@Slf4j
@RequestMapping("/mq")
@RestController
public class MqController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @PostMapping("/send")
    public void sendMsg() {
        //send message synchronously
        rocketMQTemplate.convertAndSend(TEST_TOPIC_1, "Hello, World!");
        //send spring message
        rocketMQTemplate.send(TEST_TOPIC_1, MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        //send messgae asynchronously
        rocketMQTemplate.asyncSend(TEST_TOPIC_2, new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }
            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }
        });
        //Send messages orderly
        rocketMQTemplate.syncSendOrderly(ORDERLY_TOPIC,MessageBuilder.withPayload("Hello, World").build(),"hashkey");
    }
}
