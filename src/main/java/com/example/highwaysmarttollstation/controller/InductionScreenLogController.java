package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.*;
import com.example.highwaysmarttollstation.mapper.*;
import com.example.highwaysmarttollstation.service.InductionScreenService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 诱导屏日志 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@RestController
@RequestMapping("/induction-screen-log-entity")
public class InductionScreenLogController {

    @Resource
    private InductionScreenLogMapper inductionScreenLogMapper;

    @Resource
    private FaultLogMapper faultLogMapper;

    @Resource
    private InspectorLogMapper inspectorLogMapper;

    @Resource
    private MaintenanceLogMapper maintenanceLogMapper;

    @Resource
    private AccendantLogMapper accendantLogMapper;

    @Resource
    private InductionScreenMapper inductionScreenMapper;

    @Resource
    private InductionScreenService inductionScreenService;

    @GetMapping("/getInductionScreenLogById/{inductionScreenId}")
    public List<InductionScreenLogEntity> getInductionScreenLogById(@PathVariable String inductionScreenId) {
        return inductionScreenLogMapper.getInductionScreenLogByInductionScreenId(inductionScreenId);
    }

    @PostMapping("/addInductionScreenLog")
    public InductionScreenEntity addInductionScreenLog(@RequestBody InductionScreenLogEntity inductionScreenLogEntity){
        inductionScreenLogEntity.setLogId(UUID.randomUUID().toString());
        inductionScreenLogMapper.insert(inductionScreenLogEntity);

        // 如果是故障日志，添加故障日志
        if (Objects.equals(inductionScreenLogEntity.getLogType(), "故障日志")) {
            FaultLogEntity faultLogEntity = new FaultLogEntity();
            faultLogEntity.setLogId(UUID.randomUUID().toString());
            faultLogEntity.setFaultTime(inductionScreenLogEntity.getLogTime());
            faultLogEntity.setDescription(inductionScreenLogEntity.getDescription());
            faultLogEntity.setWriterId(inductionScreenLogEntity.getWriterId());
            faultLogEntity.setMaintenanceState("未维修");
            faultLogEntity.setDeviceName(inductionScreenLogEntity.getEquipmentName());
            faultLogMapper.insert(faultLogEntity);

            InspectorLogEntity inspectorLogEntity = new InspectorLogEntity();
            inspectorLogEntity.setInspectorLogId(UUID.randomUUID().toString());
            inspectorLogEntity.setUid(inductionScreenLogEntity.getWriterId());
            inspectorLogEntity.setLogTime(inductionScreenLogEntity.getLogTime());
            inspectorLogEntity.setLogDescription(inductionScreenLogEntity.getDescription());
            inspectorLogEntity.setDeviceName(inductionScreenLogEntity.getEquipmentName());
            inspectorLogEntity.setDeviceType("诱导屏");
            inspectorLogMapper.insert(inspectorLogEntity);

            inductionScreenService.setEquipmentState(inductionScreenLogEntity.getInductionScreenId(), "未连接");
        }
        else if (Objects.equals(inductionScreenLogEntity.getLogType(), "维修日志")) {
            MaintenanceLogEntity maintenanceLogEntity = new MaintenanceLogEntity();
            maintenanceLogEntity.setMaintenanceId(UUID.randomUUID().toString());
            maintenanceLogEntity.setMaintainTime(inductionScreenLogEntity.getLogTime());
            maintenanceLogEntity.setMaintainDescription(inductionScreenLogEntity.getDescription());
            maintenanceLogEntity.setMaintainPeople(inductionScreenLogEntity.getWriterId());
            maintenanceLogEntity.setDeviceName(inductionScreenLogEntity.getEquipmentName());
            maintenanceLogEntity.setMaintainResult("已维修");
            maintenanceLogMapper.insert(maintenanceLogEntity);

            AccendantLogEntity accendantLogEntity = new AccendantLogEntity();
            accendantLogEntity.setAccendantId(UUID.randomUUID().toString());
            accendantLogEntity.setUid(inductionScreenLogEntity.getWriterId());
            accendantLogEntity.setLogTime(inductionScreenLogEntity.getLogTime());
            accendantLogEntity.setLogDescription(inductionScreenLogEntity.getDescription());
            accendantLogEntity.setDeviceName(inductionScreenLogEntity.getEquipmentName());
            accendantLogEntity.setDeviceType("诱导屏");
            accendantLogEntity.setDeviceId(inductionScreenLogEntity.getInductionScreenId());
            accendantLogMapper.insert(accendantLogEntity);

            inductionScreenService.setEquipmentState(inductionScreenLogEntity.getInductionScreenId(), "连接");

            String faultLogId = inductionScreenLogEntity.getErrorCode();

            InductionScreenLogEntity faultLog = inductionScreenLogMapper.selectById(faultLogId);
            faultLog.setState("连接");
            inductionScreenLogMapper.updateById(faultLog);
        }


        return null;
    }

}
