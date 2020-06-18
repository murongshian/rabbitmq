package com.example.rabbitmq.work.sender;

import com.example.rabbitmq.common.MessageCallBack;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WorkSender {
    private MessageCallBack messageCallBack;
    @Autowired
    public WorkSender(MessageCallBack messageCallBack){
        this.messageCallBack = messageCallBack;
    }

    public void send(String test) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.messageCallBack.send(null,"work",test,correlationData);
    }
}