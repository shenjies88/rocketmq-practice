package com.shenjies88.practice.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import static com.shenjies88.practice.rocketmq.Constant.TEST_TOPIC_1;

@Slf4j
@Service
    @RocketMQMessageListener(topic = TEST_TOPIC_1, consumerGroup = "my-consumer_test-topic-1")
    public class MyConsumer1 implements RocketMQListener<String> {
        @Override
        public void onMessage(String message) {
            log.info("received message: {}", message);
        }
    }