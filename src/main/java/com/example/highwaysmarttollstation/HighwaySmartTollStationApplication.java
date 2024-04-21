package com.example.highwaysmarttollstation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.highwaysmarttollstation.mapper")
public class HighwaySmartTollStationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HighwaySmartTollStationApplication.class, args);
    }

}
