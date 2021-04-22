package com.shenjies88.practice.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import static com.shenjies88.practice.rocketmq.Constant.TEST_TOPIC_2;

@Slf4j
@Service
    @RocketMQMessageListener(topic = TEST_TOPIC_2, consumerGroup = "my-consumer_test-topic-2")
    public class MyConsumer2 implements RocketMQListener<OrderPaidEvent> {
        @Override
        public void onMessage(OrderPaidEvent orderPaidEvent) {
            log.info("received orderPaidEvent: {}", orderPaidEvent);
        }
    }