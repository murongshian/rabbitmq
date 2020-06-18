package com.example.rabbitmq.topic.sender;

import com.example.rabbitmq.common.MessageCallBack;
import com.example.rabbitmq.topic.domain.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TopicSender{
    MessageCallBack messageCallBack;
    @Autowired
    public TopicSender(MessageCallBack messageCallBack){
        this.messageCallBack = messageCallBack;
    }
 
    public void topicSend(User user) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageCallBack.send("topic-exchange", "topic.receiver", user, correlationData);
        messageCallBack.send("topic-exchange", "topic.receivers", user, correlationData);
    }
}