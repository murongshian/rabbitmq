package com.example.rabbitmq.direct.sender;

import com.example.rabbitmq.direct.domain.User;
import com.example.rabbitmq.common.MessageCallBack;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DirectSender {
    private MessageCallBack messageCallBack;
    @Autowired
    public DirectSender(MessageCallBack messageCallBack){
        this.messageCallBack = messageCallBack;
    }

    public void directSender(User user){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.messageCallBack.send("direct-exchange", "direct.receiver", user,correlationData);
    }
}