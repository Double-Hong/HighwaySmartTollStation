package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.CarDetectorEntity;
import com.example.highwaysmarttollstation.mapper.CarDetectorMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public int updateCarDetector(@RequestBody CarDetectorEntity carDetectorEntity) {
        return carDetectorMapper.updateById(carDetectorEntity);
    }

    /**
     * 添加车检器
     *
     * @param carDetectorEntity 车检器实体
     * @return CarDetectorEntity
     */
    @PostMapping("/addCarDetector")
    public CarDetectorEntity addCarDetector(@RequestBody CarDetectorEntity carDetectorEntity) {
        carDetectorEntity.setCarDetectorId(UUID.randomUUID().toString());
        carDetectorMapper.insert(carDetectorEntity);
        return carDetectorEntity;
    }

    /**
     * 删除车检器
     *
     * @param carDetectorId 车检器ID
     * @return int
     */
    @GetMapping("/deleteCarDetector/{carDetectorId}")
    public int deleteCarDetector(@PathVariable String carDetectorId) {
        return carDetectorMapper.deleteById(carDetectorId);
    }
}
