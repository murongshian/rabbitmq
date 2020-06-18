package com.example.rabbitmq.simple.sender;

import com.example.rabbitmq.common.MessageCallBack;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SimpleSender {
    private MessageCallBack messageCallBack;
    @Autowired
    public SimpleSender(MessageCallBack messageCallBack){
        this.messageCallBack = messageCallBack;
    }

    public void send(String test) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageCallBack.send(null,"simple", test,correlationData);
    }
}