package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.*;
import com.example.highwaysmarttollstation.mapper.*;
import com.example.highwaysmarttollstation.service.ExportPaymentEquipmentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 出口自助缴费设备日志 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@RestController
@RequestMapping("/export-payment-equipment-log-entity")
public class ExportPaymentEquipmentLogController {


    @Resource
    private ExportPaymentEquipmentLogMapper exportPaymentEquipmentLogMapper;

    @Resource
    private FaultLogMapper faultLogMapper;

    @Resource
    private InspectorLogMapper inspectorLogMapper;

    @Resource
    private MaintenanceLogMapper maintenanceLogMapper;

    @Resource
    private AccendantLogMapper accendantLogMapper;

    @Resource
    private ExportPaymentEquipmentMapper exportPaymentEquipmentMapper;

    @Resource
    private ExportPaymentEquipmentService exportPaymentEquipmentService;

    @GetMapping("/getExportPaymentEquipmentLogById/{exportPaymentEquipmentId}")
    public List<ExportPaymentEquipmentLogEntity> getExportPaymentEquipmentLogById(@PathVariable String exportPaymentEquipmentId) {
        return exportPaymentEquipmentLogMapper.getExportPaymentEquipmentLogByExportPaymentEquipmentId(exportPaymentEquipmentId);
    }

    @PostMapping("/addExportPaymentEquipmentLog")
    public ExportPaymentEquipmentEntity addExportPaymentEquipmentLog(@RequestBody ExportPaymentEquipmentLogEntity exportPaymentEquipmentLogEntity){
        exportPaymentEquipmentLogEntity.setLogId(UUID.randomUUID().toString());
        exportPaymentEquipmentLogMapper.insert(exportPaymentEquipmentLogEntity);

        // 如果是故障日志，添加故障日志
        if (Objects.equals(exportPaymentEquipmentLogEntity.getLogType(), "故障日志")) {
            FaultLogEntity faultLogEntity = new FaultLogEntity();
            faultLogEntity.setLogId(UUID.randomUUID().toString());
            faultLogEntity.setFaultTime(exportPaymentEquipmentLogEntity.getLogTime());
            faultLogEntity.setDescription(exportPaymentEquipmentLogEntity.getDescription());
            faultLogEntity.setWriterId(exportPaymentEquipmentLogEntity.getWriterId());
            faultLogEntity.setMaintenanceState("未维修");
            faultLogEntity.setDeviceName(exportPaymentEquipmentLogEntity.getEquipmentName());
            faultLogMapper.insert(faultLogEntity);

            InspectorLogEntity inspectorLogEntity = new InspectorLogEntity();
            inspectorLogEntity.setInspectorLogId(UUID.randomUUID().toString());
            inspectorLogEntity.setUid(exportPaymentEquipmentLogEntity.getWriterId());
            inspectorLogEntity.setLogTime(exportPaymentEquipmentLogEntity.getLogTime());
            inspectorLogEntity.setLogDescription(exportPaymentEquipmentLogEntity.getDescription());
            inspectorLogEntity.setDeviceName(exportPaymentEquipmentLogEntity.getEquipmentName());
            inspectorLogEntity.setDeviceType("出口设备");
            inspectorLogMapper.insert(inspectorLogEntity);

            exportPaymentEquipmentService.setEquipmentState(exportPaymentEquipmentLogEntity.getExportEquipmentId(), "未连接");
        }else if (Objects.equals(exportPaymentEquipmentLogEntity.getLogType(), "维修日志")) {
            MaintenanceLogEntity maintenanceLogEntity = new MaintenanceLogEntity();
            maintenanceLogEntity.setMaintenanceId(UUID.randomUUID().toString());
            maintenanceLogEntity.setMaintainTime(exportPaymentEquipmentLogEntity.getLogTime());
            maintenanceLogEntity.setMaintainDescription(exportPaymentEquipmentLogEntity.getDescription());
            maintenanceLogEntity.setMaintainPeople(exportPaymentEquipmentLogEntity.getWriterId());
            maintenanceLogEntity.setDeviceName(exportPaymentEquipmentLogEntity.getEquipmentName());
            maintenanceLogEntity.setMaintainResult("已维修");
            maintenanceLogMapper.insert(maintenanceLogEntity);

            AccendantLogEntity accendantLogEntity = new AccendantLogEntity();
            accendantLogEntity.setAccendantId(UUID.randomUUID().toString());
            accendantLogEntity.setUid(exportPaymentEquipmentLogEntity.getWriterId());
            accendantLogEntity.setLogTime(exportPaymentEquipmentLogEntity.getLogTime());
            accendantLogEntity.setLogDescription(exportPaymentEquipmentLogEntity.getDescription());
            accendantLogEntity.setDeviceName(exportPaymentEquipmentLogEntity.getEquipmentName());
            accendantLogEntity.setDeviceType("出口设备");
            accendantLogEntity.setDeviceId(exportPaymentEquipmentLogEntity.getExportEquipmentId());
            accendantLogMapper.insert(accendantLogEntity);

            exportPaymentEquipmentService.setEquipmentState(exportPaymentEquipmentLogEntity.getExportEquipmentId(), "连接");

            String faultLogId = exportPaymentEquipmentLogEntity.getErrorCode();

            ExportPaymentEquipmentLogEntity faultLog = exportPaymentEquipmentLogMapper.selectById(faultLogId);
            faultLog.setState("连接");
            exportPaymentEquipmentLogMapper.updateById(faultLog);
        }

        ExportPaymentEquipmentEntity exportPaymentEquipmentEntity = exportPaymentEquipmentMapper.selectById(exportPaymentEquipmentLogEntity.getExportEquipmentId());
        exportPaymentEquipmentEntity.setReceiptNumber(exportPaymentEquipmentLogEntity.getReceiptNumber());
        exportPaymentEquipmentEntity.setScannerState(exportPaymentEquipmentLogEntity.getScannerState());
        exportPaymentEquipmentMapper.updateById(exportPaymentEquipmentEntity);

        return null;
    }
}
