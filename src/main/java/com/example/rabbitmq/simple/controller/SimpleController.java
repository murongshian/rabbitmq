package com.example.rabbitmq.simple.controller;

import com.example.rabbitmq.simple.sender.SimpleSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
    private SimpleSender simpleSender;
    @Autowired
    public SimpleController(SimpleSender simpleSender){
        this.simpleSender = simpleSender;
    }
    @GetMapping("simple")
    @ResponseBody
    public void simple(){
        simpleSender.send("simple message");
    }
}