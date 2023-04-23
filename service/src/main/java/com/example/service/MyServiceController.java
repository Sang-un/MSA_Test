package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyServiceController {
    @Value("${service.instance.name}")
    private String instance;

    @RequestMapping("/")
    public String message() {
        return "Hello, from  " + instance;
    }
}
