package com.example.rabbitmq.work.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkConfig {
    @Bean(value = "work")
    public Queue queue(){
        return new Queue("work");
    }
}