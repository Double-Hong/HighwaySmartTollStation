package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.CarDetectorEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 车检器 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Mapper
public interface CarDetectorMapper extends BaseMapper<CarDetectorEntity> {

    /**
     * 获取所有车检器信息
     * @return List<CarDetectorEntity>
     */
    @Select("select car_detector.*,lane_infrastructure.lane_infrastructure_name as fatherName from lane_infrastructure,car_detector where car_detector.lane_infrastructure_id = lane_infrastructure.lane_infrastructure_id")
    List<CarDetectorEntity> getAllCarDetector();
}
