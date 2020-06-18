package com.example.rabbitmq.direct.receiver;

import com.example.rabbitmq.direct.domain.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class DirectReceiver1 {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "user-direct", durable = "true"),
            exchange = @Exchange(name = "direct-exchange", durable = "true", type = "direct"),
            key = "direct.receiver"))
    @RabbitHandler
    public void directProcess1(@Payload User user, @Headers Map<String, Object> headers, Channel channel) throws IOException, InterruptedException {
        //出现错误拒绝消息
        if (headers.get("error") != null) {
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        }
        //模拟业务代码
        Thread.sleep(200);
        System.out.println(user + " direct消息被消费");
        //正确处理业务后进行确认消息(ACK)
        Long deli = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deli, false);
    }
}