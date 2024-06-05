package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.EntranceEquipmentLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 入口自助发卡设备日志 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@Mapper
public interface EntranceEquipmentLogMapper extends BaseMapper<EntranceEquipmentLogEntity> {

    @Select("select entrance_equipment_log.*,entrance_equipment.entrance_name as equipmentName,user_info.name as writerName " +
            "from entrance_equipment_log,entrance_equipment,user_info where entrance_equipment_log.entrance_equipment_id = #{entranceEquipmentId}" +
            " and entrance_equipment_log.entrance_equipment_id = entrance_equipment.entrance_equipment_id and entrance_equipment_log.writer_id = user_info.uid order by log_time desc")
    List<EntranceEquipmentLogEntity> getEntranceEquipmentLogByEntranceEquipmentId(String entranceEquipmentId);
}
