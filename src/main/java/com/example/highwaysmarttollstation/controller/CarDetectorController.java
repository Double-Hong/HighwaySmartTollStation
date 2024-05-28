package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.CarDetectorEntity;
import com.example.highwaysmarttollstation.mapper.CarDetectorMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 车检器 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/car-detector-entity")
public class CarDetectorController {

    @Resource
    CarDetectorMapper carDetectorMapper;

    @PostMapping("/updateCarDetector")
    public int updateCarDetector(@RequestBody CarDetectorEntity carDetectorEntity){
        return carDetectorMapper.updateById(carDetectorEntity);
    }

}
