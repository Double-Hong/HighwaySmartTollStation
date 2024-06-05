package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.*;
import com.example.highwaysmarttollstation.mapper.*;
import com.example.highwaysmarttollstation.service.CarDetectorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 车检器日志 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@RestController
@RequestMapping("/car-detector-log-entity")
public class CarDetectorLogController {

    @Resource
    private CarDetectorLogMapper carDetectorLogMapper;

    @Resource
    private FaultLogMapper faultLogMapper;

    @Resource
    private InspectorLogMapper inspectorLogMapper;

    @Resource
    private MaintenanceLogMapper maintenanceLogMapper;

    @Resource
    private AccendantLogMapper accendantLogMapper;

    @Resource
    private CarDetectorMapper carDetectorMapper;

    @Resource
    private CarDetectorService carDetectorService;

    @GetMapping("/getCarDetectorLogById/{carDetectorId}")
    public List<CarDetectorLogEntity> getCarDetectorLogById(@PathVariable String carDetectorId) {
        return carDetectorLogMapper.getCarDetectorLogByCarDetectorId(carDetectorId);
    }

    @PostMapping("/addCarDetectorLog")
    public CarDetectorEntity addCarDetectorLog(@RequestBody CarDetectorLogEntity carDetectorLogEntity){
        carDetectorLogEntity.setLogId(UUID.randomUUID().toString());
        carDetectorLogMapper.insert(carDetectorLogEntity);

        //如果是故障日志，添加故障日志
        if (carDetectorLogEntity.getLogType().equals("故障日志")) {
            FaultLogEntity faultLogEntity = new FaultLogEntity();
            faultLogEntity.setLogId(UUID.randomUUID().toString());
            faultLogEntity.setFaultTime(carDetectorLogEntity.getLogTime());
            faultLogEntity.setDescription(carDetectorLogEntity.getDescription());
            faultLogEntity.setWriterId(carDetectorLogEntity.getWriterId());
            faultLogEntity.setMaintenanceState("未维修");
            faultLogEntity.setDeviceName(carDetectorLogEntity.getEquipmentName());
            faultLogMapper.insert(faultLogEntity);

            InspectorLogEntity inspectorLogEntity = new InspectorLogEntity();
            inspectorLogEntity.setInspectorLogId(UUID.randomUUID().toString());
            inspectorLogEntity.setUid(carDetectorLogEntity.getWriterId());
            inspectorLogEntity.setLogTime(carDetectorLogEntity.getLogTime());
            inspectorLogEntity.setLogDescription(carDetectorLogEntity.getDescription());
            inspectorLogEntity.setDeviceName(carDetectorLogEntity.getEquipmentName());
            inspectorLogEntity.setDeviceType("车检器");
            inspectorLogMapper.insert(inspectorLogEntity);

            carDetectorService.setEquipmentState(carDetectorLogEntity.getCarDetectorId(), "未连接");
        } else if (Objects.equals(carDetectorLogEntity.getLogType(), "维修日志")) {
            MaintenanceLogEntity maintenanceLogEntity = new MaintenanceLogEntity();
            maintenanceLogEntity.setMaintenanceId(UUID.randomUUID().toString());
            maintenanceLogEntity.setMaintainTime(carDetectorLogEntity.getLogTime());
            maintenanceLogEntity.setMaintainDescription(carDetectorLogEntity.getDescription());
            maintenanceLogEntity.setMaintainPeople(carDetectorLogEntity.getWriterId());
            maintenanceLogEntity.setDeviceName(carDetectorLogEntity.getEquipmentName());
            maintenanceLogEntity.setMaintainResult("已维修");
            maintenanceLogMapper.insert(maintenanceLogEntity);

            AccendantLogEntity accendantLogEntity = new AccendantLogEntity();
            accendantLogEntity.setAccendantId(UUID.randomUUID().toString());
            accendantLogEntity.setUid(carDetectorLogEntity.getWriterId());
            accendantLogEntity.setLogTime(carDetectorLogEntity.getLogTime());
            accendantLogEntity.setLogDescription(carDetectorLogEntity.getDescription());
            accendantLogEntity.setDeviceName(carDetectorLogEntity.getEquipmentName());
            accendantLogEntity.setDeviceType("车检器");
            accendantLogEntity.setDeviceId(carDetectorLogEntity.getCarDetectorId());
            accendantLogMapper.insert(accendantLogEntity);

            carDetectorService.setEquipmentState(carDetectorLogEntity.getCarDetectorId(), "连接");
            String faultLogId = carDetectorLogEntity.getErrorCode();

            CarDetectorLogEntity faultLog = carDetectorLogMapper.selectById(faultLogId);
            faultLog.setState("连接");
            carDetectorLogMapper.updateById(faultLog);
        }
        return null;
    }


}
