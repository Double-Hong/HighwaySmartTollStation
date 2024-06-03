package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.IntelBoardEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * LED情报板 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Mapper
public interface IntelBoardMapper extends BaseMapper<IntelBoardEntity> {


    /**
     * 获取所有LED情报板信息
     * @return List<IntelBoardEntity>
     */
    @Select("select intel_board.*,lane_infrastructure.lane_infrastructure_name as fatherName from lane_infrastructure,intel_board where intel_board.lane_infrastructure_id = lane_infrastructure.lane_infrastructure_id")
    List<IntelBoardEntity> getAllIntelBoard();
}
