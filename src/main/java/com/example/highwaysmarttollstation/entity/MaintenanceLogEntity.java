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
 * 维护日志
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("maintenance_log")
@ApiModel(value = "MaintenanceLogEntity对象", description = "维护日志")
public class MaintenanceLogEntity {

    @TableId(value = "maintenance_id", type = IdType.AUTO)
    private String maintenanceId;

    @TableField("log_id")
    private String logId;

    @ApiModelProperty("维修时间")
    @TableField("maintain_time")
    private LocalDateTime maintainTime;

    @ApiModelProperty("维修描述")
    @TableField("maintain_description")
    private String maintainDescription;

    @ApiModelProperty("维修结果")
    @TableField("maintain_result")
    private String maintainResult;

    @ApiModelProperty("维修人员（可多个）")
    @TableField("maintain_people")
    private String maintainPeople;

    @ApiModelProperty("设备名称")
    @TableField("device_name")
    private String deviceName;


}
