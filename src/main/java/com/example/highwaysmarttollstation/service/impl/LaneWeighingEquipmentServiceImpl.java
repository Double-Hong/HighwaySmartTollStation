package com.example.highwaysmarttollstation.service.impl;

import com.example.highwaysmarttollstation.entity.LaneInfrastructureEntity;
import com.example.highwaysmarttollstation.entity.LaneWeighingEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.LaneInfrastructureMapper;
import com.example.highwaysmarttollstation.mapper.LaneWeighingEquipmentMapper;
import com.example.highwaysmarttollstation.service.LaneWeighingEquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车道称重设备 服务实现类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Service
public class LaneWeighingEquipmentServiceImpl extends ServiceImpl<LaneWeighingEquipmentMapper, LaneWeighingEquipmentEntity> implements LaneWeighingEquipmentService {

    @Resource
    private LaneWeighingEquipmentMapper laneWeighingEquipmentMapper;

    @Resource
    private LaneInfrastructureMapper laneInfrastructureMapper;

    @Override
    public void setEquipmentState(String deviceId, String state) {
        LaneWeighingEquipmentEntity laneWeighingEquipmentEntity = laneWeighingEquipmentMapper.selectById(deviceId);

        laneWeighingEquipmentEntity.setState(state);
        LaneInfrastructureEntity laneInfrastructureEntity = laneInfrastructureMapper.selectById(laneWeighingEquipmentEntity.getLaneInfrastructureId());
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
        laneWeighingEquipmentMapper.updateById(laneWeighingEquipmentEntity);
    }
}
