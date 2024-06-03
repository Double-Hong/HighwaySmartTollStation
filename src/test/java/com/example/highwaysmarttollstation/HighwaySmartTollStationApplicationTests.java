package com.example.highwaysmarttollstation;

import com.example.highwaysmarttollstation.mapper.AwningLightMapper;
import com.example.highwaysmarttollstation.mapper.CameraLogMapper;
import com.example.highwaysmarttollstation.mapper.CameraMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HighwaySmartTollStationApplicationTests {

    @Autowired
    private CameraLogMapper cameraLogMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        System.out.println(cameraLogMapper.getCameraLogByCameraId("001"));
    }

}
