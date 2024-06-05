package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.IntelBoardLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * LED情报板日志 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@Mapper
public interface IntelBoardLogMapper extends BaseMapper<IntelBoardLogEntity> {

    @Select("select intel_board_log.*,intel_board.led_board_name as equipmentName,user_info.name as writerName " +
            "from intel_board_log,intel_board,user_info where intel_board_log.led_board_id = #{intelBoardId}" +
            " and intel_board_log.led_board_id = intel_board.led_board_id and intel_board_log.writer_id = user_info.uid order by log_time desc")
    List<IntelBoardLogEntity> getIntelBoardLogByIntelBoardId(String intelBoardId);
}
