package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.ExportPaymentEquipmentEntity;
import com.example.highwaysmarttollstation.entity.LaneSmartDeviceEntity;
import com.example.highwaysmarttollstation.mapper.ExportPaymentEquipmentMapper;
import com.example.highwaysmarttollstation.mapper.LaneSmartDeviceMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 出口自助缴费设备 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/export-payment-equipment-entity")
public class ExportPaymentEquipmentController {

    @Resource
    ExportPaymentEquipmentMapper exportPaymentEquipmentMapper;

    @Resource
    LaneSmartDeviceMapper laneSmartDeviceMapper;

    /**
     * 更新出口自助缴费设备信息
     *
     * @param exportPaymentEquipmentEntity 出口自助缴费设备实体
     * @return ExportPaymentEquipmentEntity
     */
    @PostMapping("/updateExportPaymentEquipment")
    public ExportPaymentEquipmentEntity updateExportPaymentEquipment(@RequestBody ExportPaymentEquipmentEntity exportPaymentEquipmentEntity) {
        exportPaymentEquipmentMapper.updateById(exportPaymentEquipmentEntity);
        return exportPaymentEquipmentMapper.selectById(exportPaymentEquipmentEntity.getExportEquipmentId());
    }

    /**
     * 添加出口自助缴费设备
     *
     * @param exportPaymentEquipmentEntity 出口自助缴费设备实体
     * @return ExportPaymentEquipmentEntity
     */
    @PostMapping("/addExportPaymentEquipment/{smartDeviceId}")
    public ExportPaymentEquipmentEntity addExportPaymentEquipment(@RequestBody ExportPaymentEquipmentEntity exportPaymentEquipmentEntity, @PathVariable String smartDeviceId) {
        exportPaymentEquipmentEntity.setExportEquipmentId(UUID.randomUUID().toString());
        exportPaymentEquipmentEntity.setLaneSmartDeviceId(smartDeviceId);
        exportPaymentEquipmentMapper.insert(exportPaymentEquipmentEntity);
        LaneSmartDeviceEntity laneSmartDeviceEntity = laneSmartDeviceMapper.selectById(smartDeviceId);
        laneSmartDeviceEntity.setExportEquipmentId(exportPaymentEquipmentEntity.getExportEquipmentId());
        laneSmartDeviceMapper.updateById(laneSmartDeviceEntity);
        return exportPaymentEquipmentMapper.selectById(exportPaymentEquipmentEntity.getExportEquipmentId());
    }


    /**
     * 删除出口自助缴费设备
     *
     * @param id 出口自助缴费设备id
     * @return int
     */
    @GetMapping("/deleteExportPaymentEquipment/{id}")
    public int deleteExportPaymentEquipment(@PathVariable String id) {
        return exportPaymentEquipmentMapper.deleteById(id);
    }

    /**
     * 获取所有出口自助缴费设备
     * @return List<ExportPaymentEquipmentEntity>
     */
    @GetMapping("/getAllExportPaymentEquipment")
    public List<ExportPaymentEquipmentEntity> getAllExportPaymentEquipment(){
        return exportPaymentEquipmentMapper.getAllExportPaymentEquipment();
    }

}
