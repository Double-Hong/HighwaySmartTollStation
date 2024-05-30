package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.highwaysmarttollstation.Result;
import com.example.highwaysmarttollstation.entity.DTO.LaneSmartDeviceDTO;
import com.example.highwaysmarttollstation.entity.EntranceEquipmentEntity;
import com.example.highwaysmarttollstation.entity.ExportPaymentEquipmentEntity;
import com.example.highwaysmarttollstation.entity.LaneSmartDeviceEntity;
import com.example.highwaysmarttollstation.mapper.EntranceEquipmentMapper;
import com.example.highwaysmarttollstation.mapper.ExportPaymentEquipmentMapper;
import com.example.highwaysmarttollstation.mapper.LaneSmartDeviceMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 车道智能自助设备 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/lane-smart-device-entity")
public class LaneSmartDeviceController {

    @Resource
    LaneSmartDeviceMapper laneSmartDeviceMapper;

    @Resource
    ExportPaymentEquipmentMapper exportPaymentEquipmentMapper;

    @Resource
    EntranceEquipmentMapper entranceEquipmentMapper;

    /**
     * 获取所有车道智能自助设备基础信息
     *
     * @return 所有车道智能自助设备基础信息
     */
    @GetMapping("/getAllLaneSmartDevice")
    public List<LaneSmartDeviceEntity> getAllLaneSmartDevice() {
        return laneSmartDeviceMapper.selectList(null);
    }

    /**
     * 根据id获取车道智能自助设备基础信息
     * @param id 车道id
     * @return LaneSmartDeviceEntity
     */
    @GetMapping("/getSmartDeviceDetailById/{id}")
    public Result<?> getSmartDeviceDetailById(@PathVariable String id) {
        LaneSmartDeviceDTO laneSmartDeviceDTO = new LaneSmartDeviceDTO();
        laneSmartDeviceDTO.entranceEquipmentEntity = entranceEquipmentMapper.selectOne(new QueryWrapper<EntranceEquipmentEntity>().eq("lane_smart_device_id", id));
        laneSmartDeviceDTO.exportPaymentEquipmentEntity = exportPaymentEquipmentMapper.selectOne(new QueryWrapper<ExportPaymentEquipmentEntity>().eq("lane_smart_device_id",id));
        return Result.success(laneSmartDeviceDTO);
    }

    /**
     * 更新车道智能自助设备基础信息
     * @param laneSmartDeviceEntity 车道智能自助设备实体
     * @return List<LaneSmartDeviceEntity>
     */
    @PostMapping("/updateLaneSmartDevice")
    public List<LaneSmartDeviceEntity> updateLaneSmartDevice(@RequestBody LaneSmartDeviceEntity laneSmartDeviceEntity){
        laneSmartDeviceMapper.updateById(laneSmartDeviceEntity);
        return laneSmartDeviceMapper.selectList(null);
    }

    /**
     * 添加车道智能自助设备
     * @param laneSmartDeviceEntity 车道智能自助设备实体
     * @return List<LaneSmartDeviceEntity>
     */
    @PostMapping("/addLaneSmartDevice")
    public List<LaneSmartDeviceEntity> addLaneSmartDevice(@RequestBody LaneSmartDeviceEntity laneSmartDeviceEntity){
        laneSmartDeviceEntity.setLaneSmartDeviceId(UUID.randomUUID().toString());
        laneSmartDeviceEntity.setState("未连接");
        laneSmartDeviceMapper.insert(laneSmartDeviceEntity);
        return laneSmartDeviceMapper.selectList(null);
    }

    @GetMapping("/deleteLaneSmartDevice/{id}")
    public List<LaneSmartDeviceEntity> deleteLaneSmartDevice(@PathVariable String id){
        laneSmartDeviceMapper.deleteById(id);
        return laneSmartDeviceMapper.selectList(null);
    }
}
