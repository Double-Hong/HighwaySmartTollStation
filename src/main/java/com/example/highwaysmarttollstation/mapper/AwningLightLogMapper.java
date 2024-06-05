package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.AwningLightLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 雨棚灯日志 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-03 17:31:30
 */
@Mapper
public interface AwningLightLogMapper extends BaseMapper<AwningLightLogEntity> {


    @Select("select awning_light_log.*,awning_light.awning_light_name as equipmentName,user_info.name as writerName " +
            "from awning_light_log,awning_light,user_info where awning_light_log.awning_light_id = #{awningLightId}" +
            " and awning_light_log.awning_light_id = awning_light.awning_light_id and awning_light_log.writer_id = user_info.uid order by log_time desc")
    List<AwningLightLogEntity> getAwningLogById(String awningLightId);
}
