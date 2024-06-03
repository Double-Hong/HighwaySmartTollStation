package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.ExportPaymentEquipmentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 出口自助缴费设备 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Mapper
public interface ExportPaymentEquipmentMapper extends BaseMapper<ExportPaymentEquipmentEntity> {


    /**
     * 获取所有出口自助缴费设备信息
     * @return List<ExportPaymentEquipmentEntity>
     */
    @Select("select export_payment_equipment.*,lane_smart_device.lane_smart_device_name as fatherName from export_payment_equipment,lane_smart_device where export_payment_equipment.lane_smart_device_id = lane_smart_device.lane_smart_device_id")
    List<ExportPaymentEquipmentEntity> getAllExportPaymentEquipment();
}
