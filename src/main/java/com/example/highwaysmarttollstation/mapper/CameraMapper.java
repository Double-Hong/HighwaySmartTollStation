package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.CameraEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 摄像头 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Mapper
public interface CameraMapper extends BaseMapper<CameraEntity> {

    /**
     * 获取所有摄像头信息
     * @return List<CameraEntity>
     */
    @Select("select  camera.*,pre_transaction_gantry_equipment.transaction_name as fatherName from camera left join pre_transaction_gantry_equipment on camera.transaction_id = pre_transaction_gantry_equipment.transaction_id")
    List<CameraEntity> getAllCamera();


}
