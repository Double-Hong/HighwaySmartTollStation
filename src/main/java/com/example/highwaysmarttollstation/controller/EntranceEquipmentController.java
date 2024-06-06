package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.EntranceEquipmentEntity;
import com.example.highwaysmarttollstation.entity.LaneSmartDeviceEntity;
import com.example.highwaysmarttollstation.mapper.EntranceEquipmentMapper;
import com.example.highwaysmarttollstation.mapper.LaneSmartDeviceMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 入口自助发卡设备 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/entrance-equipment-entity")
public class EntranceEquipmentController {

    @Resource
    EntranceEquipmentMapper entranceEquipmentMapper;

    @Resource
    LaneSmartDeviceMapper laneSmartDeviceMapper;

    /**
     * 更新入口自助发卡设备信息
     *
     * @param entranceEquipmentEntity 入口自助发卡设备实体
     * @return EntranceEquipmentEntity
     */
    @PostMapping("/updateEntranceEquipment")
    public EntranceEquipmentEntity updateEntranceEquipment(@RequestBody EntranceEquipmentEntity entranceEquipmentEntity) {
        entranceEquipmentMapper.updateById(entranceEquipmentEntity);
        return entranceEquipmentMapper.selectById(entranceEquipmentEntity.getEntranceEquipmentId());
    }

    /**
     * 添加入口自助发卡设备
     *
     * @param entranceEquipmentEntity 入口自助发卡设备实体
     * @param smartDeviceId           车道智能设备id
     * @return EntranceEquipmentEntity
     */
    @PostMapping("/addEntranceEquipment/{smartDeviceId}")
    public EntranceEquipmentEntity addEntranceEquipment(@RequestBody EntranceEquipmentEntity entranceEquipmentEntity, @PathVariable String smartDeviceId) {
        entranceEquipmentEntity.setEntranceEquipmentId(UUID.randomUUID().toString());
        entranceEquipmentEntity.setLaneSmartDeviceId(smartDeviceId);
        entranceEquipmentMapper.insert(entranceEquipmentEntity);
        LaneSmartDeviceEntity laneSmartDeviceEntity = laneSmartDeviceMapper.selectById(smartDeviceId);
        laneSmartDeviceEntity.setEntranceEquipmentId(entranceEquipmentEntity.getEntranceEquipmentId());
        laneSmartDeviceEntity.setCurrentNumber(laneSmartDeviceEntity.getCurrentNumber() + 1);
        laneSmartDeviceEntity.setChildrenNumber(laneSmartDeviceEntity.getChildrenNumber() + 1);
        if (laneSmartDeviceEntity.getCurrentNumber() == laneSmartDeviceEntity.getChildrenNumber()){
            laneSmartDeviceEntity.setState("连接");
        }else {
            laneSmartDeviceEntity.setState("未连接");
        }
        laneSmartDeviceMapper.updateById(laneSmartDeviceEntity);
        return entranceEquipmentMapper.selectById(entranceEquipmentEntity.getEntranceEquipmentId());
    }

    /**
     * 删除入口自助发卡设备
     *
     * @param id 入口自助发卡设备id
     * @return int
     */
    @GetMapping("/deleteEntranceEquipment/{id}")
    public int deleteEntranceEquipment(@PathVariable String id) {
        LaneSmartDeviceEntity laneSmartDeviceEntity = laneSmartDeviceMapper.selectById(entranceEquipmentMapper.selectById(id).getLaneSmartDeviceId());
        laneSmartDeviceEntity.setCurrentNumber(laneSmartDeviceEntity.getCurrentNumber() - 1);
        laneSmartDeviceEntity.setChildrenNumber(laneSmartDeviceEntity.getChildrenNumber() - 1);
        if (laneSmartDeviceEntity.getCurrentNumber() == laneSmartDeviceEntity.getChildrenNumber()){
            laneSmartDeviceEntity.setState("连接");
        }else {
            laneSmartDeviceEntity.setState("未连接");
        }
        laneSmartDeviceMapper.updateById(laneSmartDeviceEntity);
        return entranceEquipmentMapper.deleteById(id);
    }

    /**
     * 获取所有入口自助发卡设备
     * @return List<EntranceEquipmentEntity>
     */
    @GetMapping("/getAllEntranceEquipment")
    public List<EntranceEquipmentEntity> getAllEntranceEquipment(){
        return entranceEquipmentMapper.getAllEntranceEquipment();
    }

}
