package com.example.rabbitmq.simple.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfig {
    @Bean("simple")
    public Queue queue(){
        return new Queue("simple");
    }
}