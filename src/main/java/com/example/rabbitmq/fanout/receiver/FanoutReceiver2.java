package com.example.rabbitmq.fanout.receiver;

import com.example.rabbitmq.fanout.domain.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class FanoutReceiver2 {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "user-fanouts", durable = "true"),
            exchange = @Exchange(name = "fanout-exchange", durable = "true", type = "fanout")))
    @RabbitHandler
    public void fanoutProcess1(@Payload User user, @Headers Map<String, Object> headers, Channel channel) throws IOException, InterruptedException {
        //出现错误拒绝消息
        if (headers.get("error") != null) {
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        }
        //模拟业务代码
        Thread.sleep(200);
        System.out.println(user + " fanouts消息被消费");
        //正确处理业务后进行确认消息(ACK)
        Long deli = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deli, false);
    }
}