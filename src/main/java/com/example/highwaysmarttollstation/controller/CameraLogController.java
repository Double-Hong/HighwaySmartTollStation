package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.CameraEntity;
import com.example.highwaysmarttollstation.entity.CameraLogEntity;
import com.example.highwaysmarttollstation.entity.FaultLogEntity;
import com.example.highwaysmarttollstation.entity.InspectorLogEntity;
import com.example.highwaysmarttollstation.mapper.CameraLogMapper;
import com.example.highwaysmarttollstation.mapper.CameraMapper;
import com.example.highwaysmarttollstation.mapper.FaultLogMapper;
import com.example.highwaysmarttollstation.mapper.InspectorLogMapper;
import com.example.highwaysmarttollstation.service.CameraService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    @Resource
    private FaultLogMapper faultLogMapper;

    @Resource
    private InspectorLogMapper inspectorLogMapper;

    @Resource
    private CameraMapper cameraMapper;

    @Resource
    CameraService cameraService;

    @GetMapping("/getCameraLogById/{cameraId}")
    public List<CameraLogEntity> getCameraLogById(@PathVariable String cameraId) {
        return cameraLogMapper.getCameraLogByCameraId(cameraId);
    }

    @PostMapping("/addCameraLog")
    public CameraEntity addCameraLog(@RequestBody CameraLogEntity cameraLogEntity) {
        System.out.println(cameraLogEntity.getLogTime());
        cameraLogEntity.setLogId(UUID.randomUUID().toString());
        cameraLogMapper.insert(cameraLogEntity);

        // 如果是故障日志，添加故障日志
        if (Objects.equals(cameraLogEntity.getLogType(), "故障日志")){
            FaultLogEntity faultLogEntity = new FaultLogEntity();
            faultLogEntity.setLogId(UUID.randomUUID().toString());
            faultLogEntity.setFaultTime(cameraLogEntity.getLogTime());
            faultLogEntity.setDescription(cameraLogEntity.getDescription());
            faultLogEntity.setWriterId(cameraLogEntity.getWriterId());
            faultLogEntity.setMaintenanceState("未维修");
            faultLogEntity.setDeviceName(cameraLogEntity.getEquipmentName());
            faultLogMapper.insert(faultLogEntity);

            InspectorLogEntity inspectorLogEntity = new InspectorLogEntity();
            inspectorLogEntity.setInspectorLogId(UUID.randomUUID().toString());
            inspectorLogEntity.setUid(cameraLogEntity.getWriterId());
            inspectorLogEntity.setLogTime(cameraLogEntity.getLogTime());
            inspectorLogEntity.setLogDescription(cameraLogEntity.getDescription());
            inspectorLogEntity.setDeviceName(cameraLogEntity.getEquipmentName());
            inspectorLogEntity.setDeviceType("摄像机");
            inspectorLogMapper.insert(inspectorLogEntity);

            cameraService.setEquipmentState(cameraLogEntity.getCameraId(), "未连接");

        }
        else if (Objects.equals(cameraLogEntity.getLogType(), "维修日志")){
            System.out.println();
        }

        return cameraMapper.selectById(cameraLogEntity.getCameraId());
    }
}
