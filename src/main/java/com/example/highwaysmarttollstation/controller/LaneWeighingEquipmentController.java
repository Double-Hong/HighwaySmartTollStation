package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.LaneInfrastructureEntity;
import com.example.highwaysmarttollstation.entity.LaneWeighingEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.LaneInfrastructureMapper;
import com.example.highwaysmarttollstation.mapper.LaneWeighingEquipmentMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 车道称重设备 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/lane-weighing-equipment-entity")
public class LaneWeighingEquipmentController {

    @Resource
    LaneWeighingEquipmentMapper laneWeighingEquipmentMapper;

    @Resource
    LaneInfrastructureMapper laneInfrastructureMapper;

    @PostMapping("/updateLaneWeighingEquipment")
    public int updateLaneWeighingEquipment(@RequestBody LaneWeighingEquipmentEntity laneWeighingEquipmentEntity){
        return laneWeighingEquipmentMapper.updateById(laneWeighingEquipmentEntity);
    }

    /**
     * 添加车道称重设备
     *
     * @param laneWeighingEquipmentEntity 车道称重设备实体
     * @return LaneWeighingEquipmentEntity
     */
    @PostMapping("/addLaneWeighingEquipment")
    public LaneWeighingEquipmentEntity addLaneWeighingEquipment(@RequestBody LaneWeighingEquipmentEntity laneWeighingEquipmentEntity){
        LaneInfrastructureEntity laneInfrastructureEntity = laneInfrastructureMapper.selectById(laneWeighingEquipmentEntity.getLaneInfrastructureId());
        laneInfrastructureEntity.setCurrentNumber(laneInfrastructureEntity.getCurrentNumber() + 1);
        laneInfrastructureEntity.setChildrenNumber(laneInfrastructureEntity.getChildrenNumber() + 1);
        if (laneInfrastructureEntity.getCurrentNumber() == laneInfrastructureEntity.getChildrenNumber()){
            laneInfrastructureEntity.setState("连接");
        }else {
            laneInfrastructureEntity.setState("未连接");
        }
        laneInfrastructureMapper.updateById(laneInfrastructureEntity);
        laneWeighingEquipmentEntity.setLaneWeighingId(UUID.randomUUID().toString());
        laneWeighingEquipmentMapper.insert(laneWeighingEquipmentEntity);
        return laneWeighingEquipmentEntity;
    }

    /**
     * 删除车道称重设备
     *
     * @param laneWeighingId 车道称重设备ID
     * @return int
     */
    @GetMapping("/deleteLaneWeighingEquipment/{laneWeighingId}")
    public int deleteLaneWeighingEquipment(@PathVariable String laneWeighingId){
        LaneInfrastructureEntity laneInfrastructureEntity = laneInfrastructureMapper.selectById(laneWeighingEquipmentMapper.selectById(laneWeighingId).getLaneInfrastructureId());
        laneInfrastructureEntity.setCurrentNumber(laneInfrastructureEntity.getCurrentNumber() - 1);
        laneInfrastructureEntity.setChildrenNumber(laneInfrastructureEntity.getChildrenNumber() - 1);
        if (laneInfrastructureEntity.getCurrentNumber() == laneInfrastructureEntity.getChildrenNumber()){
            laneInfrastructureEntity.setState("连接");
        }else {
            laneInfrastructureEntity.setState("未连接");
        }
        laneInfrastructureMapper.updateById(laneInfrastructureEntity);
        return laneWeighingEquipmentMapper.deleteById(laneWeighingId);
    }

    /**
     * 获取所有车道称重设备
     * @return List<LaneWeighingEquipmentEntity>
     */
    @GetMapping("/getAllLaneWeighingEquipment")
    public List<LaneWeighingEquipmentEntity> getAllLaneWeighingEquipment(){
        return laneWeighingEquipmentMapper.getAllLaneWeighingEquipment();
    }
}
