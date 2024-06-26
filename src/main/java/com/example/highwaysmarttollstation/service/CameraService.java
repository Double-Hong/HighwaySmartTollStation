package com.example.highwaysmarttollstation.service;

import com.example.highwaysmarttollstation.entity.CameraEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 摄像头 服务类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
public interface CameraService extends IService<CameraEntity> {

    void setEquipmentState(String cameraId, String state);
}
