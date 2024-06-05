package com.example.highwaysmarttollstation.service.impl;

import com.example.highwaysmarttollstation.entity.CameraEntity;
import com.example.highwaysmarttollstation.entity.EtcAntennaEntity;
import com.example.highwaysmarttollstation.entity.PreTransactionGantryEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.EtcAntennaMapper;
import com.example.highwaysmarttollstation.mapper.PreTransactionGantryEquipmentMapper;
import com.example.highwaysmarttollstation.service.EtcAntennaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ETC天线 服务实现类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Service
public class EtcAntennaServiceImpl extends ServiceImpl<EtcAntennaMapper, EtcAntennaEntity> implements EtcAntennaService {

    @Resource
    EtcAntennaMapper etcAntennaMapper;

    @Resource
    PreTransactionGantryEquipmentMapper preTransactionGantryEquipmentMapper;

    @Override
    public void setEquipmentState(String deviceId, String state) {
        EtcAntennaEntity etcAntennaEntity = etcAntennaMapper.selectById(deviceId);

        etcAntennaEntity.setState(state);
        PreTransactionGantryEquipmentEntity preTransactionGantryEquipmentEntity = preTransactionGantryEquipmentMapper.selectById(etcAntennaEntity.getTransactionId());
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
        etcAntennaMapper.updateById(etcAntennaEntity);
    }
}
