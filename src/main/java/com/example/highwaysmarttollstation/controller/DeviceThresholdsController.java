package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.DeviceThresholdsEntity;
import com.example.highwaysmarttollstation.mapper.DeviceThresholdsMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 阈值 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-07 17:21:33
 */
@RestController
@RequestMapping("/device-thresholds-entity")
public class DeviceThresholdsController {

    @Resource
    DeviceThresholdsMapper deviceThresholdsMapper;

    @GetMapping("/get")
    public DeviceThresholdsEntity get(){
        return deviceThresholdsMapper.selectById("001");
    }

    @PostMapping("/update")
    public DeviceThresholdsEntity update(@RequestBody DeviceThresholdsEntity deviceThresholdsEntity){
        deviceThresholdsMapper.updateById(deviceThresholdsEntity);
        return deviceThresholdsEntity;
    }

}
