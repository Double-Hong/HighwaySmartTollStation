package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.CarDetectorEntity;
import com.example.highwaysmarttollstation.entity.LaneInfrastructureEntity;
import com.example.highwaysmarttollstation.mapper.CarDetectorMapper;
import com.example.highwaysmarttollstation.mapper.LaneInfrastructureMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Resource
    LaneInfrastructureMapper laneInfrastructureMapper;

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
        LaneInfrastructureEntity laneInfrastructureEntity = laneInfrastructureMapper.selectById(carDetectorEntity.getLaneInfrastructureId());
        laneInfrastructureEntity.setCurrentNumber(laneInfrastructureEntity.getCurrentNumber() + 1);
        laneInfrastructureEntity.setChildrenNumber(laneInfrastructureEntity.getChildrenNumber() + 1);
        if (laneInfrastructureEntity.getCurrentNumber() == laneInfrastructureEntity.getChildrenNumber()){
            laneInfrastructureEntity.setState("连接");
        }else {
            laneInfrastructureEntity.setState("未连接");
        }
        laneInfrastructureMapper.updateById(laneInfrastructureEntity);
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
        LaneInfrastructureEntity laneInfrastructureEntity = laneInfrastructureMapper.selectById(carDetectorMapper.selectById(carDetectorId).getLaneInfrastructureId());
        laneInfrastructureEntity.setCurrentNumber(laneInfrastructureEntity.getCurrentNumber() - 1);
        laneInfrastructureEntity.setChildrenNumber(laneInfrastructureEntity.getChildrenNumber() - 1);
        if (laneInfrastructureEntity.getCurrentNumber() == laneInfrastructureEntity.getChildrenNumber()){
            laneInfrastructureEntity.setState("连接");
        }else {
            laneInfrastructureEntity.setState("未连接");
        }
        laneInfrastructureMapper.updateById(laneInfrastructureEntity);
        return carDetectorMapper.deleteById(carDetectorId);
    }

    /**
     * 获取所有车检器
     * @return List<CarDetectorEntity>
     */
    @GetMapping("/getAllCarDetector")
    public List<CarDetectorEntity> getAllCarDetector(){
        return carDetectorMapper.getAllCarDetector();
    }
}
