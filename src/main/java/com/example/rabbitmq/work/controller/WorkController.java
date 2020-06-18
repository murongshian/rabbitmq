package com.example.rabbitmq.work.controller;

import com.example.rabbitmq.simple.sender.SimpleSender;
import com.example.rabbitmq.work.sender.WorkSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WorkController {
    private WorkSender workSender;
    @Autowired
    public WorkController(WorkSender workSender){
        this.workSender = workSender;
    }
    @GetMapping("work")
    @ResponseBody
    public void simple(){
        workSender.send("work message");
    }
}
