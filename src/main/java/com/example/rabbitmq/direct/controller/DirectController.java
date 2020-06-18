package com.example.rabbitmq.direct.controller;

import com.example.rabbitmq.direct.domain.User;
import com.example.rabbitmq.direct.sender.DirectSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DirectController {
    private DirectSender directSender;
    @Autowired
    public DirectController(DirectSender directSender){
        this.directSender = directSender;
    }

    @GetMapping("direct")
    @ResponseBody
    public void direct(){
        User user = new User();
        user.setName("三毛");
        user.setAge(15);
        user.setHobby("流浪");
        directSender.directSender(user);
    }
}
