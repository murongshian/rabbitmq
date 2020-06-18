package com.example.rabbitmq.common;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    private RabbitTemplate rabbitTemplate;
    @Autowired
    public MessageCallBack(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }
    //这是发送的方法，封装在这里面了，外面我们需要发送信息给Exchange直接调用这个方法
    public void send(String exchange, String routingKey, Object object, CorrelationData correlationData) {
        this.rabbitTemplate.convertAndSend(exchange, routingKey, object, correlationData);
    }
 
    /**
     * 发送后的回调函数
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            System.out.println("消息发送exchange成功,id:{}" + correlationData.getId());
        }else{
            System.out.println("消息发送exchange失败,id:{},原因:{}" + correlationData.getId() + cause);
        }
    }
 
    /**
     * 消息发送失败的回调函数
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String correlationId = message.getMessageProperties().getCorrelationId();
        System.out.println("消息:{}发送失败，应答码:{} 原因:{} 交换机:{} 路由键:{}"+ correlationId + replyCode + replyText + exchange + routingKey);
    }
}