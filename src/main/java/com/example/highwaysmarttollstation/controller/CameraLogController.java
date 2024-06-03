package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.CameraLogEntity;
import com.example.highwaysmarttollstation.mapper.CameraLogMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 摄像头日志 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-03 17:31:30
 */
@RestController
@RequestMapping("/camera-log-entity")
public class CameraLogController {

    @Resource
    private CameraLogMapper cameraLogMapper;

    @GetMapping("/getCameraLogById/{cameraId}")
    public List<CameraLogEntity> getCameraLogById(@PathVariable String cameraId) {
        return cameraLogMapper.getCameraLogByCameraId(cameraId);
    }
}
