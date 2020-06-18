package com.example.rabbitmq.fanout.controller;

import com.example.rabbitmq.fanout.sender.FanoutSender;
import com.example.rabbitmq.fanout.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FanoutController {
    private FanoutSender fanoutSender;
    @Autowired
    public FanoutController(FanoutSender fanoutSender){
        this.fanoutSender = fanoutSender;
    }
    @GetMapping("fanout")
    @ResponseBody
    public void fanout(){
        User user = new User();
        user.setName("三毛");
        user.setAge(15);
        user.setHobby("流浪");
        fanoutSender.fanoutSender(user);
    }
}