package com.example.highwaysmarttollstation.service.impl;

import com.example.highwaysmarttollstation.entity.IntelBoardEntity;
import com.example.highwaysmarttollstation.entity.LaneInfrastructureEntity;
import com.example.highwaysmarttollstation.mapper.IntelBoardMapper;
import com.example.highwaysmarttollstation.mapper.LaneInfrastructureMapper;
import com.example.highwaysmarttollstation.service.IntelBoardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * LED情报板 服务实现类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Service
public class IntelBoardServiceImpl extends ServiceImpl<IntelBoardMapper, IntelBoardEntity> implements IntelBoardService {
    @Resource
    IntelBoardMapper intelBoardMapper;

    @Resource
    LaneInfrastructureMapper laneInfrastructureMapper;

    @Override
    public void setEquipmentState(String deviceId, String state) {
        IntelBoardEntity intelBoardEntity = intelBoardMapper.selectById(deviceId);

        intelBoardEntity.setState(state);
        LaneInfrastructureEntity laneInfrastructureEntity = laneInfrastructureMapper.selectById(intelBoardEntity.getLaneInfrastructureId());
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
        intelBoardMapper.updateById(intelBoardEntity);
    }
}
