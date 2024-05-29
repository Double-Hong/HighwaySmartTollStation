package com.example.highwaysmarttollstation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 监测人员日志表
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-21 10:04:36
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("inspector_log")
@ApiModel(value = "InspectorLogEntity对象", description = "监测人员日志表")
public class InspectorLogEntity {

    @TableId(value = "inspector_log_id", type = IdType.AUTO)
    private String inspectorLogId;

    @TableField("uid")
    private String uid;

    @ApiModelProperty("日志时间")
    @TableField("log_time")
    private LocalDateTime logTime;

    @ApiModelProperty("日志描述")
    @TableField("log_description")
    private String logDescription;

    @ApiModelProperty("设备名称")
    @TableField("device_name")
    private String deviceName;

    @ApiModelProperty("对应设备id")
    @TableField("device_id")
    private String deviceId;

    @ApiModelProperty("设备类型")
    @TableField("device_type")
    private String deviceType;


}
