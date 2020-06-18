package com.example.rabbitmq.work.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RabbitListener(queues = "work")
public class WorkReceiver2 {
    @RabbitHandler
    public void process(String test, @Headers Map<String, Object> headers, Channel channel) throws IOException, InterruptedException {
        //出现错误拒绝消息
        if (headers.get("error") != null) {
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        }
        //模拟业务代码
        Thread.sleep(200);
        System.out.println(test + " work2消息被消费");
        //正确处理业务后进行确认消息(ACK)
        Long deli = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deli, false);
    }
}