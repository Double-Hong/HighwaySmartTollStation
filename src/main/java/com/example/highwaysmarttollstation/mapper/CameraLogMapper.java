package com.example.highwaysmarttollstation.mapper;

import com.example.highwaysmarttollstation.entity.CameraLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 摄像头日志 Mapper 接口
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-03 17:31:30
 */
@Mapper
public interface CameraLogMapper extends BaseMapper<CameraLogEntity> {

    @Select("select camera_log.*,camera.camera_name as equipmentName,user_info.name as writerName " +
            "from camera_log,camera,user_info where camera_log.camera_id = #{cameraId}" +
            " and camera_log.camera_id = camera.camera_id and camera_log.writer_id = user_info.uid")
    List<CameraLogEntity> getCameraLogByCameraId(String cameraId);
}
