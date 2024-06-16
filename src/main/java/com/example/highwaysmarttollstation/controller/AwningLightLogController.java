package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.*;
import com.example.highwaysmarttollstation.mapper.*;
import com.example.highwaysmarttollstation.service.AwningLightService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 雨棚灯日志 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-03 17:31:30
 */
@RestController
@RequestMapping("/awning-light-log-entity")
public class AwningLightLogController {

    @Resource
    private AwningLightLogMapper awningLightLogMapper;

    @Resource
    private AwningLightMapper awningLightMapper;

    @Resource
    private FaultLogMapper faultLogMapper;

    @Resource
    private InspectorLogMapper inspectorLogMapper;

    @Resource
    private MaintenanceLogMapper maintenanceLogMapper;

    @Resource
    private AccendantLogMapper accendantLogMapper;

    @Resource
    private AwningLightService awningLightService;

    @GetMapping("/getAwningLogById/{awningLightId}")
    public List<AwningLightLogEntity> getAwningLogById(@PathVariable String awningLightId) {
        return awningLightLogMapper.getAwningLogById(awningLightId);
    }

    @PostMapping("/addAwningLog")
    public AwningLightEntity addAwningLog(@RequestBody AwningLightLogEntity awningLightLogEntity) {
        awningLightLogEntity.setLogId(UUID.randomUUID().toString());
        awningLightLogMapper.insert(awningLightLogEntity);

        //如果是故障日志，添加故障日志
        if (awningLightLogEntity.getLogType().equals("故障日志")) {
            FaultLogEntity faultLogEntity = new FaultLogEntity();
            faultLogEntity.setLogId(UUID.randomUUID().toString());
            faultLogEntity.setFaultTime(awningLightLogEntity.getLogTime());
            faultLogEntity.setDescription(awningLightLogEntity.getDescription());
            faultLogEntity.setWriterId(awningLightLogEntity.getWriterId());
            faultLogEntity.setMaintenanceState("未维修");
            faultLogEntity.setDeviceName(awningLightLogEntity.getEquipmentName());
            faultLogMapper.insert(faultLogEntity);

            InspectorLogEntity inspectorLogEntity = new InspectorLogEntity();
            inspectorLogEntity.setInspectorLogId(UUID.randomUUID().toString());
            inspectorLogEntity.setUid(awningLightLogEntity.getWriterId());
            inspectorLogEntity.setLogTime(awningLightLogEntity.getLogTime());
            inspectorLogEntity.setLogDescription(awningLightLogEntity.getDescription());
            inspectorLogEntity.setDeviceName(awningLightLogEntity.getEquipmentName());
            inspectorLogEntity.setDeviceType("雨棚灯");
            inspectorLogMapper.insert(inspectorLogEntity);

            awningLightService.setEquipmentState(awningLightLogEntity.getAwningLightId(), "未连接");
        } else if (Objects.equals(awningLightLogEntity.getLogType(), "维修日志")) {
            MaintenanceLogEntity maintenanceLogEntity = new MaintenanceLogEntity();
            maintenanceLogEntity.setMaintenanceId(UUID.randomUUID().toString());
            maintenanceLogEntity.setMaintainTime(awningLightLogEntity.getLogTime());
            maintenanceLogEntity.setMaintainDescription(awningLightLogEntity.getDescription());
            maintenanceLogEntity.setMaintainPeople(awningLightLogEntity.getWriterId());
            maintenanceLogEntity.setDeviceName(awningLightLogEntity.getEquipmentName());
            maintenanceLogEntity.setMaintainResult("已维修");
            maintenanceLogMapper.insert(maintenanceLogEntity);

            AccendantLogEntity accendantLogEntity = new AccendantLogEntity();
            accendantLogEntity.setAccendantId(UUID.randomUUID().toString());
            accendantLogEntity.setUid(awningLightLogEntity.getWriterId());
            accendantLogEntity.setLogTime(awningLightLogEntity.getLogTime());
            accendantLogEntity.setLogDescription(awningLightLogEntity.getDescription());
            accendantLogEntity.setDeviceName(awningLightLogEntity.getEquipmentName());
            accendantLogEntity.setDeviceType("雨棚灯");
            accendantLogEntity.setDeviceId(awningLightLogEntity.getAwningLightId());
            accendantLogMapper.insert(accendantLogEntity);

            awningLightService.setEquipmentState(awningLightLogEntity.getAwningLightId(), "连接");
            String faultLogId = awningLightLogEntity.getErrorCode();

            AwningLightLogEntity faultLog = awningLightLogMapper.selectById(faultLogId);
            faultLog.setState("连接");
            awningLightLogMapper.updateById(faultLog);

        }
        AwningLightEntity awningLightEntity = awningLightMapper.selectById(awningLightLogEntity.getAwningLightId());
        awningLightEntity.setBrightness(awningLightLogEntity.getBrightness());
        awningLightMapper.updateById(awningLightEntity);
        return awningLightEntity;
    }

}
