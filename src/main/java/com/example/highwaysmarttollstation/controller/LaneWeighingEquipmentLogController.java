package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.*;
import com.example.highwaysmarttollstation.mapper.*;
import com.example.highwaysmarttollstation.service.LaneWeighingEquipmentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 车道称重设备日志 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@RestController
@RequestMapping("/lane-weighing-equipment-log-entity")
public class LaneWeighingEquipmentLogController {

    @Resource
    private LaneWeighingEquipmentLogMapper laneWeighingEquipmentLogMapper;

    @Resource
    private FaultLogMapper faultLogMapper;

    @Resource
    private InspectorLogMapper inspectorLogMapper;

    @Resource
    private MaintenanceLogMapper maintenanceLogMapper;

    @Resource
    private AccendantLogMapper accendantLogMapper;

    @Resource
    private LaneWeighingEquipmentMapper laneWeighingEquipmentMapper;

    @Resource
    private LaneWeighingEquipmentService laneWeighingEquipmentService;

    @GetMapping("/getLaneWeighingEquipmentLogById/{laneWeighingEquipmentId}")
    public List<LaneWeighingEquipmentLogEntity> getLaneWeighingEquipmentLogById(@PathVariable String laneWeighingEquipmentId) {
        return laneWeighingEquipmentLogMapper.getLaneWeighingEquipmentLogByLaneWeighingEquipmentId(laneWeighingEquipmentId);
    }

    @PostMapping("/addLaneWeighingEquipmentLog")
    public LaneWeighingEquipmentEntity addLaneWeighingEquipmentLog(@RequestBody LaneWeighingEquipmentLogEntity laneWeighingEquipmentLogEntity) {
        laneWeighingEquipmentLogEntity.setLogId(UUID.randomUUID().toString());
        laneWeighingEquipmentLogMapper.insert(laneWeighingEquipmentLogEntity);

        // 如果是故障日志，添加故障日志
        if (Objects.equals(laneWeighingEquipmentLogEntity.getLogType(), "故障日志")) {
            FaultLogEntity faultLogEntity = new FaultLogEntity();
            faultLogEntity.setLogId(UUID.randomUUID().toString());
            faultLogEntity.setFaultTime(laneWeighingEquipmentLogEntity.getLogTime());
            faultLogEntity.setDescription(laneWeighingEquipmentLogEntity.getDescription());
            faultLogEntity.setWriterId(laneWeighingEquipmentLogEntity.getWriterId());
            faultLogEntity.setMaintenanceState("未维修");
            faultLogEntity.setDeviceName(laneWeighingEquipmentLogEntity.getEquipmentName());
            faultLogMapper.insert(faultLogEntity);

            InspectorLogEntity inspectorLogEntity = new InspectorLogEntity();
            inspectorLogEntity.setInspectorLogId(UUID.randomUUID().toString());
            inspectorLogEntity.setUid(laneWeighingEquipmentLogEntity.getWriterId());
            inspectorLogEntity.setLogTime(laneWeighingEquipmentLogEntity.getLogTime());
            inspectorLogEntity.setLogDescription(laneWeighingEquipmentLogEntity.getDescription());
            inspectorLogEntity.setDeviceName(laneWeighingEquipmentLogEntity.getEquipmentName());
            inspectorLogEntity.setDeviceType("车检器");
            inspectorLogMapper.insert(inspectorLogEntity);

            laneWeighingEquipmentService.setEquipmentState(laneWeighingEquipmentLogEntity.getLaneWeighingId(), "未连接");
        } else if (Objects.equals(laneWeighingEquipmentLogEntity.getLogType(), "维修日志")) {
            MaintenanceLogEntity maintenanceLogEntity = new MaintenanceLogEntity();
            maintenanceLogEntity.setMaintenanceId(UUID.randomUUID().toString());
            maintenanceLogEntity.setMaintainTime(laneWeighingEquipmentLogEntity.getLogTime());
            maintenanceLogEntity.setMaintainDescription(laneWeighingEquipmentLogEntity.getDescription());
            maintenanceLogEntity.setMaintainPeople(laneWeighingEquipmentLogEntity.getWriterId());
            maintenanceLogEntity.setDeviceName(laneWeighingEquipmentLogEntity.getEquipmentName());
            maintenanceLogEntity.setMaintainResult("已维修");
            maintenanceLogMapper.insert(maintenanceLogEntity);

            AccendantLogEntity accendantLogEntity = new AccendantLogEntity();
            accendantLogEntity.setAccendantId(UUID.randomUUID().toString());
            accendantLogEntity.setUid(laneWeighingEquipmentLogEntity.getWriterId());
            accendantLogEntity.setLogTime(laneWeighingEquipmentLogEntity.getLogTime());
            accendantLogEntity.setLogDescription(laneWeighingEquipmentLogEntity.getDescription());
            accendantLogEntity.setDeviceName(laneWeighingEquipmentLogEntity.getEquipmentName());
            accendantLogEntity.setDeviceType("称重设备");
            accendantLogEntity.setDeviceId(laneWeighingEquipmentLogEntity.getLaneWeighingId());
            accendantLogMapper.insert(accendantLogEntity);

            laneWeighingEquipmentService.setEquipmentState(laneWeighingEquipmentLogEntity.getLaneWeighingId(), "连接");
            String faultLogId = laneWeighingEquipmentLogEntity.getErrorCode();

            LaneWeighingEquipmentLogEntity faultLog = laneWeighingEquipmentLogMapper.selectById(faultLogId);
            faultLog.setState("连接");
            laneWeighingEquipmentLogMapper.updateById(faultLog);
        }
        return null;


    }
}
