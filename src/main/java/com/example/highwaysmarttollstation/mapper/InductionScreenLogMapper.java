package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.InductionScreenLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 诱导屏日志 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@Mapper
public interface InductionScreenLogMapper extends BaseMapper<InductionScreenLogEntity> {

    @Select("select induction_screen_log.*,induction_screen.induction_screen_name as equipmentName,user_info.name as writerName " +
            "from induction_screen_log,induction_screen,user_info where induction_screen_log.induction_screen_id = #{inductionScreenId}" +
            " and induction_screen_log.induction_screen_id = induction_screen.induction_screen_id and induction_screen_log.writer_id = user_info.uid order by log_time desc")
    List<InductionScreenLogEntity> getInductionScreenLogByInductionScreenId(String inductionScreenId);
}
