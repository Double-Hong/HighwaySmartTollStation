package com.example.highwaysmarttollstation.service.impl;

import com.example.highwaysmarttollstation.entity.EntranceEquipmentEntity;
import com.example.highwaysmarttollstation.entity.LaneSmartDeviceEntity;
import com.example.highwaysmarttollstation.mapper.EntranceEquipmentMapper;
import com.example.highwaysmarttollstation.mapper.LaneSmartDeviceMapper;
import com.example.highwaysmarttollstation.service.EntranceEquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 入口自助发卡设备 服务实现类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Service
public class EntranceEquipmentServiceImpl extends ServiceImpl<EntranceEquipmentMapper, EntranceEquipmentEntity> implements EntranceEquipmentService {
    @Resource
    private EntranceEquipmentMapper entranceEquipmentMapper;

    @Resource
    private LaneSmartDeviceMapper laneSmartDeviceMapper;

    @Override
    public void setEquipmentState(String deviceId, String state) {
        EntranceEquipmentEntity entranceEquipmentEntity = entranceEquipmentMapper.selectById(deviceId);

        entranceEquipmentEntity.setState(state);
        LaneSmartDeviceEntity laneSmartDeviceEntity = laneSmartDeviceMapper.selectById(entranceEquipmentEntity.getLaneSmartDeviceId());
        int currentNumber = laneSmartDeviceEntity.getCurrentNumber();
        if ("连接".equals(state)) {
            laneSmartDeviceEntity.setCurrentNumber(currentNumber + 1);
        } else {
            laneSmartDeviceEntity.setCurrentNumber(currentNumber - 1);
        }

        if (laneSmartDeviceEntity.getCurrentNumber() == laneSmartDeviceEntity.getChildrenNumber()) {
            laneSmartDeviceEntity.setState("连接");
        } else {
            laneSmartDeviceEntity.setState("未连接");
        }

        System.out.println(11111111);
        System.out.println(entranceEquipmentEntity);
        System.out.println(entranceEquipmentEntity.getState());
        laneSmartDeviceMapper.updateById(laneSmartDeviceEntity);
        entranceEquipmentMapper.updateById(entranceEquipmentEntity);
    }
}
