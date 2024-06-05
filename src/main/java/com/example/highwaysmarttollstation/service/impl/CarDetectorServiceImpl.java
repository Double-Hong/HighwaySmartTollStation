package com.example.highwaysmarttollstation.service.impl;

import com.example.highwaysmarttollstation.entity.CarDetectorEntity;
import com.example.highwaysmarttollstation.entity.LaneInfrastructureEntity;
import com.example.highwaysmarttollstation.mapper.CarDetectorMapper;
import com.example.highwaysmarttollstation.mapper.LaneInfrastructureMapper;
import com.example.highwaysmarttollstation.service.CarDetectorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车检器 服务实现类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Service
public class CarDetectorServiceImpl extends ServiceImpl<CarDetectorMapper, CarDetectorEntity> implements CarDetectorService {
    @Resource
    CarDetectorMapper carDetectorMapper;

    @Resource
    LaneInfrastructureMapper laneInfrastructureMapper;

    @Override
    public void setEquipmentState(String deviceId, String state) {
        CarDetectorEntity carDetectorEntity = carDetectorMapper.selectById(deviceId);

        carDetectorEntity.setState(state);
        LaneInfrastructureEntity laneInfrastructureEntity = laneInfrastructureMapper.selectById(carDetectorEntity.getLaneInfrastructureId());
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
        carDetectorMapper.updateById(carDetectorEntity);
    }
}
