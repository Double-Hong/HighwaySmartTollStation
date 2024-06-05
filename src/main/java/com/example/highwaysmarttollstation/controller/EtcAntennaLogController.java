package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.*;
import com.example.highwaysmarttollstation.mapper.*;
import com.example.highwaysmarttollstation.service.EtcAntennaService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * ETC天线日志 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@RestController
@RequestMapping("/etc-antenna-log-entity")
public class EtcAntennaLogController {

    @Resource
    private EtcAntennaLogMapper etcAntennaLogMapper;

    @Resource
    private FaultLogMapper faultLogMapper;

    @Resource
    private InspectorLogMapper inspectorLogMapper;

    @Resource
    private MaintenanceLogMapper maintenanceLogMapper;

    @Resource
    private AccendantLogMapper accendantLogMapper;

    @Resource
    private EtcAntennaMapper etcAntennaMapper;

    @Resource
    private EtcAntennaService etcAntennaService;


    @GetMapping("/getEtcAntennaLogById/{antennaId}")
    public List<EtcAntennaLogEntity> getEtcAntennaLogById(@PathVariable String antennaId) {
        return etcAntennaLogMapper.getEtcAntennaLogByAntennaId(antennaId);
    }

    @PostMapping("/addEtcAntennaLog")
    public EtcAntennaEntity addEtcAntennaLog(@RequestBody EtcAntennaLogEntity etcAntennaLogEntity){
        etcAntennaLogEntity.setLogId(UUID.randomUUID().toString());
        etcAntennaLogMapper.insert(etcAntennaLogEntity);

        // 如果是故障日志，添加故障日志
        if (Objects.equals(etcAntennaLogEntity.getLogType(), "故障日志")) {
            FaultLogEntity faultLogEntity = new FaultLogEntity();
            faultLogEntity.setLogId(UUID.randomUUID().toString());
            faultLogEntity.setFaultTime(etcAntennaLogEntity.getLogTime());
            faultLogEntity.setDescription(etcAntennaLogEntity.getDescription());
            faultLogEntity.setWriterId(etcAntennaLogEntity.getWriterId());
            faultLogEntity.setMaintenanceState("未维修");
            faultLogEntity.setDeviceName(etcAntennaLogEntity.getEquipmentName());
            faultLogMapper.insert(faultLogEntity);

            InspectorLogEntity inspectorLogEntity = new InspectorLogEntity();
            inspectorLogEntity.setInspectorLogId(UUID.randomUUID().toString());
            inspectorLogEntity.setUid(etcAntennaLogEntity.getWriterId());
            inspectorLogEntity.setLogTime(etcAntennaLogEntity.getLogTime());
            inspectorLogEntity.setLogDescription(etcAntennaLogEntity.getDescription());
            inspectorLogEntity.setDeviceName(etcAntennaLogEntity.getEquipmentName());
            inspectorLogEntity.setDeviceType("ETC");
            inspectorLogMapper.insert(inspectorLogEntity);

            etcAntennaService.setEquipmentState(etcAntennaLogEntity.getAntennaId(), "未连接");

        }else if (Objects.equals(etcAntennaLogEntity.getLogType(), "维修日志")) {
            MaintenanceLogEntity maintenanceLogEntity = new MaintenanceLogEntity();
            maintenanceLogEntity.setMaintenanceId(UUID.randomUUID().toString());
            maintenanceLogEntity.setMaintainTime(etcAntennaLogEntity.getLogTime());
            maintenanceLogEntity.setMaintainDescription(etcAntennaLogEntity.getDescription());
            maintenanceLogEntity.setMaintainPeople(etcAntennaLogEntity.getWriterId());
            maintenanceLogEntity.setDeviceName(etcAntennaLogEntity.getEquipmentName());
            maintenanceLogEntity.setMaintainResult("已维修");
            maintenanceLogMapper.insert(maintenanceLogEntity);

            AccendantLogEntity accendantLogEntity = new AccendantLogEntity();
            accendantLogEntity.setAccendantId(UUID.randomUUID().toString());
            accendantLogEntity.setUid(etcAntennaLogEntity.getWriterId());
            accendantLogEntity.setLogTime(etcAntennaLogEntity.getLogTime());
            accendantLogEntity.setLogDescription(etcAntennaLogEntity.getDescription());
            accendantLogEntity.setDeviceName(etcAntennaLogEntity.getEquipmentName());
            accendantLogEntity.setDeviceType("ETC");
            accendantLogEntity.setDeviceId(etcAntennaLogEntity.getAntennaId());
            accendantLogMapper.insert(accendantLogEntity);

            etcAntennaService.setEquipmentState(etcAntennaLogEntity.getAntennaId(), "连接");

            String faultLogId = etcAntennaLogEntity.getErrorCode();

            EtcAntennaLogEntity faultLog = etcAntennaLogMapper.selectById(faultLogId);
            faultLog.setState("连接");
            etcAntennaLogMapper.updateById(faultLog);
        }
        return null;
    }


}
