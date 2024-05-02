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
 * 故障日志表
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("fault_log")
@ApiModel(value = "FaultLogEntity对象", description = "故障日志表")
public class FaultLogEntity {

    @TableId(value = "log_id", type = IdType.AUTO)
    private String logId;

    @ApiModelProperty("对应设备名称")
    @TableField("device_id")
    private String deviceId;

    @ApiModelProperty("故障时间")
    @TableField("fault_time")
    private LocalDateTime faultTime;

    @ApiModelProperty("故障描述")
    @TableField("`description`")
    private String description;

    @ApiModelProperty("书写人id")
    @TableField("writer_id")
    private String writerId;

    @ApiModelProperty("维修状态（已维修、未维修、未完全维修）")
    @TableField("maintenance_state")
    private String maintenanceState;


}
