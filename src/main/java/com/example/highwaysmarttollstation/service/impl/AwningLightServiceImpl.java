package com.example.highwaysmarttollstation.service.impl;

import com.example.highwaysmarttollstation.entity.AwningLightEntity;
import com.example.highwaysmarttollstation.entity.CameraEntity;
import com.example.highwaysmarttollstation.entity.LaneInfrastructureEntity;
import com.example.highwaysmarttollstation.entity.PreTransactionGantryEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.AwningLightMapper;
import com.example.highwaysmarttollstation.mapper.LaneInfrastructureMapper;
import com.example.highwaysmarttollstation.service.AwningLightService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雨棚灯 服务实现类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Service
public class AwningLightServiceImpl extends ServiceImpl<AwningLightMapper, AwningLightEntity> implements AwningLightService {

    @Resource
    AwningLightMapper awningLightMapper;

    @Resource
    LaneInfrastructureMapper laneInfrastructureMapper;

    /**
     * 设置设备状态
     *
     * @param deviceId 设备id
     * @param state    状态
     */
    public void setEquipmentState(String deviceId, String state) {
        AwningLightEntity cameraEntity = awningLightMapper.selectById(deviceId);

        cameraEntity.setState(state);
        LaneInfrastructureEntity laneInfrastructureEntity = laneInfrastructureMapper.selectById(cameraEntity.getLaneInfrastructureId());
        int currentNumber = laneInfrastructureEntity.getCurrentNumber();
        if ("连接".equals(state)) {
            laneInfrastructureEntity.setCurrentNumber(currentNumber + 1);
        } else {
            laneInfrastructureEntity.setCurrentNumber(currentNumber - 1);
        }

        if (laneInfrastructureEntity.getCurrentNumber() == laneInfrastructureEntity.getChildrenNumber()) {
            laneInfrastructureEntity.setState("连接");
        } else {
            laneInfrastructureEntity.setState("未连接");
        }

        laneInfrastructureMapper.updateById(laneInfrastructureEntity);
        awningLightMapper.updateById(cameraEntity);
    }
}
