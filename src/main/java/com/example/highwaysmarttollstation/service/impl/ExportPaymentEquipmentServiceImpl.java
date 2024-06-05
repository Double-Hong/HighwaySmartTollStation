package com.example.highwaysmarttollstation.service.impl;

import com.example.highwaysmarttollstation.entity.ExportPaymentEquipmentEntity;
import com.example.highwaysmarttollstation.entity.LaneSmartDeviceEntity;
import com.example.highwaysmarttollstation.mapper.ExportPaymentEquipmentMapper;
import com.example.highwaysmarttollstation.mapper.LaneSmartDeviceMapper;
import com.example.highwaysmarttollstation.service.ExportPaymentEquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出口自助缴费设备 服务实现类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Service
public class ExportPaymentEquipmentServiceImpl extends ServiceImpl<ExportPaymentEquipmentMapper, ExportPaymentEquipmentEntity> implements ExportPaymentEquipmentService {

    @Resource
    ExportPaymentEquipmentMapper exportPaymentEquipmentMapper;

    @Resource
    LaneSmartDeviceMapper laneSmartDeviceMapper;

    @Override
    public void setEquipmentState(String deviceId, String state) {
        ExportPaymentEquipmentEntity exportPaymentEquipmentEntity = exportPaymentEquipmentMapper.selectById(deviceId);

        exportPaymentEquipmentEntity.setState(state);
        LaneSmartDeviceEntity laneSmartDeviceEntity = laneSmartDeviceMapper.selectById(exportPaymentEquipmentEntity.getLaneSmartDeviceId());
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

        laneSmartDeviceMapper.updateById(laneSmartDeviceEntity);
        exportPaymentEquipmentMapper.updateById(exportPaymentEquipmentEntity);
    }
}
