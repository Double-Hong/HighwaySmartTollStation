package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.*;
import com.example.highwaysmarttollstation.mapper.*;
import com.example.highwaysmarttollstation.service.IntelBoardService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * LED情报板日志 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@RestController
@RequestMapping("/intel-board-log-entity")
public class IntelBoardLogController {

    @Resource
    private IntelBoardLogMapper intelBoardLogMapper;

    @Resource
    private FaultLogMapper faultLogMapper;

    @Resource
    private InspectorLogMapper inspectorLogMapper;

    @Resource
    private MaintenanceLogMapper maintenanceLogMapper;

    @Resource
    private AccendantLogMapper accendantLogMapper;

    @Resource
    private IntelBoardMapper intelBoardMapper;

    @Resource
    private IntelBoardService intelBoardService;

    @GetMapping("/getIntelBoardLogById/{intelBoard}")
    public List<IntelBoardLogEntity> getIntelBoardLogById(@PathVariable String intelBoard) {
        return intelBoardLogMapper.getIntelBoardLogByIntelBoardId(intelBoard);
    }

    @PostMapping("/addIntelBoardLog")
    public IntelBoardEntity addIntelBoardLog(@RequestBody IntelBoardLogEntity intelBoardLogEntity) {
        intelBoardLogEntity.setLogId(UUID.randomUUID().toString());
        intelBoardLogMapper.insert(intelBoardLogEntity);

        // 如果是故障日志，添加故障日志
        if (Objects.equals(intelBoardLogEntity.getLogType(), "故障日志")) {
            FaultLogEntity faultLogEntity = new FaultLogEntity();
            faultLogEntity.setLogId(UUID.randomUUID().toString());
            faultLogEntity.setFaultTime(intelBoardLogEntity.getLogTime());
            faultLogEntity.setDescription(intelBoardLogEntity.getDescription());
            faultLogEntity.setWriterId(intelBoardLogEntity.getWriterId());
            faultLogEntity.setMaintenanceState("未维修");
            faultLogEntity.setDeviceName(intelBoardLogEntity.getEquipmentName());
            faultLogMapper.insert(faultLogEntity);

            InspectorLogEntity inspectorLogEntity = new InspectorLogEntity();
            inspectorLogEntity.setInspectorLogId(UUID.randomUUID().toString());
            inspectorLogEntity.setUid(intelBoardLogEntity.getWriterId());
            inspectorLogEntity.setLogTime(intelBoardLogEntity.getLogTime());
            inspectorLogEntity.setLogDescription(intelBoardLogEntity.getDescription());
            inspectorLogEntity.setDeviceName(intelBoardLogEntity.getEquipmentName());
            inspectorLogEntity.setDeviceType("车检器");
            inspectorLogMapper.insert(inspectorLogEntity);

            intelBoardService.setEquipmentState(intelBoardLogEntity.getLedBoardId(), "未连接");
        } else if (Objects.equals(intelBoardLogEntity.getLogType(), "维修日志")) {
            MaintenanceLogEntity maintenanceLogEntity = new MaintenanceLogEntity();
            maintenanceLogEntity.setMaintenanceId(UUID.randomUUID().toString());
            maintenanceLogEntity.setMaintainTime(intelBoardLogEntity.getLogTime());
            maintenanceLogEntity.setMaintainDescription(intelBoardLogEntity.getDescription());
            maintenanceLogEntity.setMaintainPeople(intelBoardLogEntity.getWriterId());
            maintenanceLogEntity.setDeviceName(intelBoardLogEntity.getEquipmentName());
            maintenanceLogEntity.setMaintainResult("已维修");
            maintenanceLogMapper.insert(maintenanceLogEntity);

            AccendantLogEntity accendantLogEntity = new AccendantLogEntity();
            accendantLogEntity.setAccendantId(UUID.randomUUID().toString());
            accendantLogEntity.setUid(intelBoardLogEntity.getWriterId());
            accendantLogEntity.setLogTime(intelBoardLogEntity.getLogTime());
            accendantLogEntity.setLogDescription(intelBoardLogEntity.getDescription());
            accendantLogEntity.setDeviceName(intelBoardLogEntity.getEquipmentName());
            accendantLogEntity.setDeviceType("情报板");
            accendantLogEntity.setDeviceId(intelBoardLogEntity.getLedBoardId());
            accendantLogMapper.insert(accendantLogEntity);

            intelBoardService.setEquipmentState(intelBoardLogEntity.getLedBoardId(), "连接");
            String faultLogId = intelBoardLogEntity.getErrorCode();

            IntelBoardLogEntity faultLog = intelBoardLogMapper.selectById(faultLogId);
            faultLog.setState("连接");
            intelBoardLogMapper.updateById(faultLog);
        }

        IntelBoardEntity intelBoardEntity = intelBoardMapper.selectById(intelBoardLogEntity.getLedBoardId());
        intelBoardEntity.setBrightness(intelBoardLogEntity.getBrightness());
        intelBoardEntity.setContrastRatio(intelBoardLogEntity.getContrastRatio());
        intelBoardEntity.setDisplayRate(intelBoardLogEntity.getDisplayRate());
        intelBoardMapper.updateById(intelBoardEntity);

        return null;
    }
}
