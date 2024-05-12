package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.EntranceEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.EntranceEquipmentMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 更新入口自助发卡设备信息
     * @param entranceEquipmentEntity 入口自助发卡设备实体
     * @return EntranceEquipmentEntity
     */
    @PostMapping("/updateEntranceEquipment")
    public EntranceEquipmentEntity updateEntranceEquipment(@RequestBody EntranceEquipmentEntity entranceEquipmentEntity){
        entranceEquipmentMapper.updateById(entranceEquipmentEntity);
        return entranceEquipmentMapper.selectById(entranceEquipmentEntity.getEntranceEquipmentId());
    }

}
