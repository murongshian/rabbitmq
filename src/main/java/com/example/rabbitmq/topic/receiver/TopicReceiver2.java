package com.example.rabbitmq.topic.receiver;

import com.example.rabbitmq.topic.domain.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class TopicReceiver2 {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "user-topics", durable = "true"),
            exchange = @Exchange(name = "topic-exchange", durable = "true", type = "topic"),
            key = "topic.*"))
    @RabbitHandler
    public void topicProcess2(@Payload User user, @Headers Map<String, Object> headers, Channel channel) throws IOException, InterruptedException {
        //出现错误拒绝消息
        if (headers.get("error") != null) {
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
            return;
        }
        //模拟业务代码
        Thread.sleep(200);
        System.out.println(user + " topics消息被消费");
        //确认消息
        Long deli = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deli, false);
    }
}