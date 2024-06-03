package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.EntranceEquipmentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 入口自助发卡设备 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Mapper
public interface EntranceEquipmentMapper extends BaseMapper<EntranceEquipmentEntity> {


    /**
     * 获取所有入口自助发卡设备信息
     * @return List<EntranceEquipmentEntity>
     */
    @Select("select entrance_equipment.*,lane_smart_device.lane_smart_device_name as fatherName from entrance_equipment,lane_smart_device where entrance_equipment.lane_smart_device_id = lane_smart_device.lane_smart_device_id")
    List<EntranceEquipmentEntity> getAllEntranceEquipment();
}
