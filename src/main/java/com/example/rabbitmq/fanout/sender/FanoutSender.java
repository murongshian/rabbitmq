package com.example.rabbitmq.fanout.sender;

import com.example.rabbitmq.common.MessageCallBack;
import com.example.rabbitmq.fanout.domain.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FanoutSender {
    private MessageCallBack messageCallBack;
    @Autowired
    public FanoutSender(MessageCallBack messageCallBack){
        this.messageCallBack = messageCallBack;
    }
    /**
     * 1.指定的交换机
     * 2.指定routingkey(广播模式不需要绑定routingkey，绑定了也不会生效)
     * 3.发送的消息
     */
    public void fanoutSender(User user) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        messageCallBack.send("fanout-exchange", "", user,correlationData);
    }
}