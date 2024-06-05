package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.LaneWeighingEquipmentLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 车道称重设备日志 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@Mapper
public interface LaneWeighingEquipmentLogMapper extends BaseMapper<LaneWeighingEquipmentLogEntity> {

    @Select("select lane_weighing_equipment_log.*,lane_weighing_equipment.lane_weighing_name as equipmentName,user_info.name as writerName " +
            "from lane_weighing_equipment_log,lane_weighing_equipment,user_info where lane_weighing_equipment_log.lane_weighing_id = #{laneWeighingEquipmentId}" +
            " and lane_weighing_equipment_log.lane_weighing_id = lane_weighing_equipment.lane_weighing_id and lane_weighing_equipment_log.writer_id = user_info.uid order by log_time desc")
    List<LaneWeighingEquipmentLogEntity> getLaneWeighingEquipmentLogByLaneWeighingEquipmentId(String laneWeighingEquipmentId);
}
