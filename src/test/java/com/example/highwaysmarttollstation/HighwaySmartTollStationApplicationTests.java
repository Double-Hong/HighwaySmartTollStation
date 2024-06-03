package com.example.highwaysmarttollstation;

import com.example.highwaysmarttollstation.mapper.AwningLightMapper;
import com.example.highwaysmarttollstation.mapper.CameraMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HighwaySmartTollStationApplicationTests {

    @Autowired
    private AwningLightMapper awningLightMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        awningLightMapper.getAllAwningLight().forEach(System.out::println);
    }

}
