package com.example.highwaysmarttollstation.service.impl;

import com.example.highwaysmarttollstation.entity.InductionScreenEntity;
import com.example.highwaysmarttollstation.entity.PreTransactionGantryEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.InductionScreenMapper;
import com.example.highwaysmarttollstation.mapper.PreTransactionGantryEquipmentMapper;
import com.example.highwaysmarttollstation.service.InductionScreenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 诱导屏 服务实现类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Service
public class InductionScreenServiceImpl extends ServiceImpl<InductionScreenMapper, InductionScreenEntity> implements InductionScreenService {

    @Resource
    InductionScreenMapper inductionScreenMapper;

    @Resource
    PreTransactionGantryEquipmentMapper preTransactionGantryEquipmentMapper;

    @Override
    public void setEquipmentState(String deviceId, String state) {
        InductionScreenEntity inductionScreenEntity = inductionScreenMapper.selectById(deviceId);

        inductionScreenEntity.setState(state);
        PreTransactionGantryEquipmentEntity preTransactionGantryEquipmentEntity = preTransactionGantryEquipmentMapper.selectById(inductionScreenEntity.getTransactionId());
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
        inductionScreenMapper.updateById(inductionScreenEntity);
    }
}
