package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.EtcAntennaEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * ETC天线 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Mapper
public interface EtcAntennaMapper extends BaseMapper<EtcAntennaEntity> {


    /**
     * 获取所有ETC天线信息
     * @return List<EtcAntennaEntity>
     */
    @Select("select etc_antenna.*,pre_transaction_gantry_equipment.transaction_name as fatherName from etc_antenna left join pre_transaction_gantry_equipment on etc_antenna.transaction_id = pre_transaction_gantry_equipment.transaction_id")
    List<EtcAntennaEntity> getAllEtcAntenna();
}
