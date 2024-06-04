package com.example.highwaysmarttollstation.service.impl;

import com.example.highwaysmarttollstation.entity.CameraEntity;
import com.example.highwaysmarttollstation.entity.PreTransactionGantryEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.CameraMapper;
import com.example.highwaysmarttollstation.mapper.PreTransactionGantryEquipmentMapper;
import com.example.highwaysmarttollstation.service.CameraService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 摄像头 服务实现类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Service
public class CameraServiceImpl extends ServiceImpl<CameraMapper, CameraEntity> implements CameraService {

    @Resource
    CameraMapper cameraMapper;

    @Resource
    PreTransactionGantryEquipmentMapper preTransactionGantryEquipmentMapper;

    /**
     * 设置设备状态
     * @param cameraId 摄像头id
     * @param state 状态
     */
    public void setEquipmentState(String cameraId, String state) {
        CameraEntity cameraEntity = cameraMapper.selectById(cameraId);

        cameraEntity.setState(state);
        PreTransactionGantryEquipmentEntity preTransactionGantryEquipmentEntity = preTransactionGantryEquipmentMapper.selectById(cameraEntity.getTransactionId());
        int currentNumber = preTransactionGantryEquipmentEntity.getCurrentNumber();
        if ("连接".equals(state)) {
            preTransactionGantryEquipmentEntity.setCurrentNumber(currentNumber + 1);
        } else {
            preTransactionGantryEquipmentEntity.setCurrentNumber(currentNumber - 1);
        }

        if (preTransactionGantryEquipmentEntity.getCurrentNumber() == preTransactionGantryEquipmentEntity.getChildrenNumber()) {
            preTransactionGantryEquipmentEntity.setState("连接");
        } else {
            preTransactionGantryEquipmentEntity.setState("未连接");
        }

        preTransactionGantryEquipmentMapper.updateById(preTransactionGantryEquipmentEntity);
        cameraMapper.updateById(cameraEntity);
    }
}
