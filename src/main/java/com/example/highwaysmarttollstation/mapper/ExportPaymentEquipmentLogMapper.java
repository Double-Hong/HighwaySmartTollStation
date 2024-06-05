package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.ExportPaymentEquipmentLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 出口自助缴费设备日志 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@Mapper
public interface ExportPaymentEquipmentLogMapper extends BaseMapper<ExportPaymentEquipmentLogEntity> {

    @Select("select export_payment_equipment_log.*,export_payment_equipment.export_name as equipmentName,user_info.name as writerName " +
            "from export_payment_equipment_log,export_payment_equipment,user_info where export_payment_equipment_log.export_equipment_id = #{exportPaymentEquipmentId}" +
            " and export_payment_equipment_log.export_equipment_id = export_payment_equipment.export_equipment_id and export_payment_equipment_log.writer_id = user_info.uid order by log_time desc")
    List<ExportPaymentEquipmentLogEntity> getExportPaymentEquipmentLogByExportPaymentEquipmentId(String exportPaymentEquipmentId);
}
