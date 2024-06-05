package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.EtcAntennaLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * ETC天线日志 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-05 11:53:02
 */
@Mapper
public interface EtcAntennaLogMapper extends BaseMapper<EtcAntennaLogEntity> {

    @Select("select etc_antenna_log.*,etc_antenna.antenna_name as equipmentName,user_info.name as writerName " +
            "from etc_antenna_log,etc_antenna,user_info where etc_antenna_log.antenna_id = #{antennaId}" +
            " and etc_antenna_log.antenna_id = etc_antenna.antenna_id and etc_antenna_log.writer_id = user_info.uid order by log_time desc")
    List<EtcAntennaLogEntity> getEtcAntennaLogByAntennaId(String antennaId);
}
