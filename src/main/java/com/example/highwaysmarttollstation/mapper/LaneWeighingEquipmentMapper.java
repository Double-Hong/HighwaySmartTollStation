package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.LaneWeighingEquipmentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 车道称重设备 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Mapper
public interface LaneWeighingEquipmentMapper extends BaseMapper<LaneWeighingEquipmentEntity> {


    /**
     * 获取所有车道称重设备信息
     * @return List<LaneWeighingEquipmentEntity>
     */
    @Select("select lane_weighing_equipment.*,lane_infrastructure.lane_infrastructure_name as fatherName from lane_infrastructure,lane_weighing_equipment where lane_weighing_equipment.lane_infrastructure_id = lane_infrastructure.lane_infrastructure_id")
    List<LaneWeighingEquipmentEntity> getAllLaneWeighingEquipment();
}
