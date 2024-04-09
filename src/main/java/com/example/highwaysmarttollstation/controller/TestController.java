package com.example.highwaysmarttollstation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :Double-Hong
 * @name :HighwaySmartTollStation
 * @date :2024/4/9
 * @time :23:15
 **/


@RestController("TestController")
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

}
