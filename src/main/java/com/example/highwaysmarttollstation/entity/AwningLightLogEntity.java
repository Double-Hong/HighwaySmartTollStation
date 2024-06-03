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
 * 雨棚灯日志
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-03 17:31:30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("awning_light_log")
@ApiModel(value = "AwningLightLogEntity对象", description = "雨棚灯日志")
public class AwningLightLogEntity {

    @TableId(value = "log_id", type = IdType.AUTO)
    private String logId;

    @TableField("awning_light_id")
    private String awningLightId;

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

    @ApiModelProperty("亮度")
    @TableField("brightness")
    private Float brightness;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;

    @ApiModelProperty("日志类型（维修日志或故障日志）")
    @TableField("log_type")
    private String logType;

    @ApiModelProperty("日志书写人ID")
    @TableField("writer_id")
    private String writerId;


}
