package com.example.rabbitmq.topic.controller;

import com.example.rabbitmq.topic.domain.User;
import com.example.rabbitmq.topic.sender.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TopicController {
    private TopicSender topicSender;
    @Autowired
    public TopicController(TopicSender topicSender){
        this.topicSender = topicSender;
    }
    @GetMapping("topic")
    @ResponseBody
    public void topic(){
        User user = new User();
        user.setName("三毛");
        user.setAge(15);
        user.setHobby("流浪");
        topicSender.topicSend(user);
    }
}