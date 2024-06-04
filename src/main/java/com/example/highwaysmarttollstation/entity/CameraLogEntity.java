package com.example.highwaysmarttollstation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 摄像头日志
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-03 17:31:30
 */
@ToString
@Getter
@Setter
@Accessors(chain = true)
@TableName("camera_log")
@ApiModel(value = "CameraLogEntity对象", description = "摄像头日志")
public class CameraLogEntity {

    @TableId(value = "log_id", type = IdType.AUTO)
    private String logId;

    @TableField("camera_id")
    private String cameraId;

    @JsonFormat(pattern = "yyyy/M/d H:mm:ss")
    @TableField("log_time")
    private LocalDateTime logTime;

    @ApiModelProperty("状态（连接还是未连接）")
    @TableField("state")
    private String state;

    @ApiModelProperty("错误代码")
    @TableField("error_code")
    private String errorCode;

    @ApiModelProperty("日志描述")
    @TableField("`description`")
    private String description;

    @ApiModelProperty("焦距")
    @TableField("focal_length")
    private Float focalLength;

    @ApiModelProperty("光圈")
    @TableField("aperture")
    private String aperture;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;

    @ApiModelProperty("日志类型（维修日志或故障日志）")
    @TableField("log_type")
    private String logType;

    @ApiModelProperty("日志书写人ID")
    @TableField("writer_id")
    private String writerId;

    @TableField(exist = false)
    private String equipmentName;

    @TableField(exist = false)
    private String writerName;


}
