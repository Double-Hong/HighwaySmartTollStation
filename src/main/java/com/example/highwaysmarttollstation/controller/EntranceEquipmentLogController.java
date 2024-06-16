package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.*;
import com.example.highwaysmarttollstation.mapper.*;
import com.example.highwaysmarttollstation.service.EntranceEquipmentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 入口自助发卡设备日志 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@RestController
@RequestMapping("/entrance-equipment-log-entity")
public class EntranceEquipmentLogController {

    @Resource
    private EntranceEquipmentLogMapper entranceEquipmentLogMapper;

    @Resource
    private FaultLogMapper faultLogMapper;

    @Resource
    private InspectorLogMapper inspectorLogMapper;

    @Resource
    private MaintenanceLogMapper maintenanceLogMapper;

    @Resource
    private AccendantLogMapper accendantLogMapper;

    @Resource
    private EntranceEquipmentMapper entranceEquipmentMapper;

    @Resource
    private EntranceEquipmentService entranceEquipmentService;

    @GetMapping("/getEntranceEquipmentLogById/{entranceEquipmentId}")
    public List<EntranceEquipmentLogEntity> getEntranceEquipmentLogById(@PathVariable String entranceEquipmentId) {
        return entranceEquipmentLogMapper.getEntranceEquipmentLogByEntranceEquipmentId(entranceEquipmentId);
    }

    @PostMapping("/addEntranceEquipmentLog")
    public EntranceEquipmentEntity addEntranceEquipmentLog(@RequestBody EntranceEquipmentLogEntity entranceEquipmentLogEntity){
        entranceEquipmentLogEntity.setLogId(UUID.randomUUID().toString());
        entranceEquipmentLogMapper.insert(entranceEquipmentLogEntity);

        // 如果是故障日志，添加故障日志
        if (Objects.equals(entranceEquipmentLogEntity.getLogType(), "故障日志")) {
            FaultLogEntity faultLogEntity = new FaultLogEntity();
            faultLogEntity.setLogId(UUID.randomUUID().toString());
            faultLogEntity.setFaultTime(entranceEquipmentLogEntity.getLogTime());
            faultLogEntity.setDescription(entranceEquipmentLogEntity.getDescription());
            faultLogEntity.setWriterId(entranceEquipmentLogEntity.getWriterId());
            faultLogEntity.setMaintenanceState("未维修");
            faultLogEntity.setDeviceName(entranceEquipmentLogEntity.getEquipmentName());
            faultLogMapper.insert(faultLogEntity);

            InspectorLogEntity inspectorLogEntity = new InspectorLogEntity();
            inspectorLogEntity.setInspectorLogId(UUID.randomUUID().toString());
            inspectorLogEntity.setUid(entranceEquipmentLogEntity.getWriterId());
            inspectorLogEntity.setLogTime(entranceEquipmentLogEntity.getLogTime());
            inspectorLogEntity.setLogDescription(entranceEquipmentLogEntity.getDescription());
            inspectorLogEntity.setDeviceName(entranceEquipmentLogEntity.getEquipmentName());
            inspectorLogEntity.setDeviceType("入口设备");
            inspectorLogMapper.insert(inspectorLogEntity);

            entranceEquipmentService.setEquipmentState(entranceEquipmentLogEntity.getEntranceEquipmentId(), "未连接");
        }else if (Objects.equals(entranceEquipmentLogEntity.getLogType(), "维修日志")) {
            MaintenanceLogEntity maintenanceLogEntity = new MaintenanceLogEntity();
            maintenanceLogEntity.setMaintenanceId(UUID.randomUUID().toString());
            maintenanceLogEntity.setMaintainTime(entranceEquipmentLogEntity.getLogTime());
            maintenanceLogEntity.setMaintainDescription(entranceEquipmentLogEntity.getDescription());
            maintenanceLogEntity.setMaintainPeople(entranceEquipmentLogEntity.getWriterId());
            maintenanceLogEntity.setDeviceName(entranceEquipmentLogEntity.getEquipmentName());
            maintenanceLogEntity.setMaintainResult("已维修");
            maintenanceLogMapper.insert(maintenanceLogEntity);

            AccendantLogEntity accendantLogEntity = new AccendantLogEntity();
            accendantLogEntity.setAccendantId(UUID.randomUUID().toString());
            accendantLogEntity.setUid(entranceEquipmentLogEntity.getWriterId());
            accendantLogEntity.setLogTime(entranceEquipmentLogEntity.getLogTime());
            accendantLogEntity.setLogDescription(entranceEquipmentLogEntity.getDescription());
            accendantLogEntity.setDeviceName(entranceEquipmentLogEntity.getEquipmentName());
            accendantLogEntity.setDeviceType("入口设备");
            accendantLogEntity.setDeviceId(entranceEquipmentLogEntity.getEntranceEquipmentId());
            accendantLogMapper.insert(accendantLogEntity);

            entranceEquipmentService.setEquipmentState(entranceEquipmentLogEntity.getEntranceEquipmentId(), "连接");

            String faultLogId = entranceEquipmentLogEntity.getErrorCode();

            EntranceEquipmentLogEntity faultLog = entranceEquipmentLogMapper.selectById(faultLogId);
            faultLog.setState("连接");
            entranceEquipmentLogMapper.updateById(faultLog);
        }

        EntranceEquipmentEntity entranceEquipmentEntity = entranceEquipmentMapper.selectById(entranceEquipmentLogEntity.getEntranceEquipmentId());
        entranceEquipmentEntity.setCardNumber(entranceEquipmentLogEntity.getCardNumber());
        entranceEquipmentMapper.updateById(entranceEquipmentEntity);

        return null;
    }

}
