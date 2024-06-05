package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.CarDetectorLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 车检器日志 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@Mapper
public interface CarDetectorLogMapper extends BaseMapper<CarDetectorLogEntity> {

    @Select("select car_detector_log.*,car_detector.car_detector_name as equipmentName,user_info.name as writerName " +
            "from car_detector_log,car_detector,user_info where car_detector_log.car_detector_id = #{carDetectorId}" +
            " and car_detector_log.car_detector_id = car_detector.car_detector_id and car_detector_log.writer_id = user_info.uid order by log_time desc")
    List<CarDetectorLogEntity> getCarDetectorLogByCarDetectorId(String carDetectorId);
}
