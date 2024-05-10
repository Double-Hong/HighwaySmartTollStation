package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.ExportPaymentEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.ExportPaymentEquipmentMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/updateExportPaymentEquipment")
    public ExportPaymentEquipmentEntity updateExportPaymentEquipment(@RequestBody ExportPaymentEquipmentEntity exportPaymentEquipmentEntity){
        exportPaymentEquipmentMapper.updateById(exportPaymentEquipmentEntity);
        return exportPaymentEquipmentMapper.selectById(exportPaymentEquipmentEntity.getExportEquipmentId());
    }
}
