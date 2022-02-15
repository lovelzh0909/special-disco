package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controlHello {

    @RequestMapping(value="/",method = RequestMethod.POST)
    public String handler_01(){
        return "Hello World!";
    }
}
