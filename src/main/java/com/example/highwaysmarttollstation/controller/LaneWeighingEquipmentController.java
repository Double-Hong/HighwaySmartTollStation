package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.LaneWeighingEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.LaneWeighingEquipmentMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
        return laneWeighingEquipmentMapper.deleteById(laneWeighingId);
    }
}
