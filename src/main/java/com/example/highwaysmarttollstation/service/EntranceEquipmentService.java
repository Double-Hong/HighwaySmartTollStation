package com.example.highwaysmarttollstation.service;

import com.example.highwaysmarttollstation.entity.EntranceEquipmentEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 入口自助发卡设备 服务类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
public interface EntranceEquipmentService extends IService<EntranceEquipmentEntity> {
    void setEquipmentState(String deviceId, String state);
}
