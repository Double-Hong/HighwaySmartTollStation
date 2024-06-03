package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.AwningLightEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 雨棚灯 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Mapper
public interface AwningLightMapper extends BaseMapper<AwningLightEntity> {

    /**
     * 获取所有雨棚灯信息
     * @return List<AwningLightEntity>
     */
    @Select("select awning_light.*,lane_infrastructure.lane_infrastructure_name as fatherName from awning_light,lane_infrastructure where awning_light.lane_infrastructure_id = lane_infrastructure.lane_infrastructure_id")
    List<AwningLightEntity> getAllAwningLight();
}
