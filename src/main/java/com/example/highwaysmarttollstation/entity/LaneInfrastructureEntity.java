package com.example.highwaysmarttollstation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 车道基础设备
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("lane_infrastructure")
@ApiModel(value = "LaneInfrastructureEntity对象", description = "车道基础设备")
public class LaneInfrastructureEntity {

    @TableId(value = "lane_infrastructure_id", type = IdType.AUTO)
    private String laneInfrastructureId;

    @TableField("car_detector_id")
    private String carDetectorId;

    @TableField("lane_weighing_id")
    private String laneWeighingId;

    @TableField("led_board_id")
    private String ledBoardId;

    @TableField("awning_light_id")
    private String awningLightId;

    @TableField("lane_infrastructure_name")
    private String laneInfrastructureName;

    @ApiModelProperty("安装日期")
    @TableField("installation_date")
    private LocalDate installationDate;

    @ApiModelProperty("状态（连接还是未连接）")
    @TableField("state")
    private String state;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;

    @ApiModelProperty("子设备数量")
    @TableField("children_number")
    private int childrenNumber;

    @ApiModelProperty("当前子设备状态为连接的数量")
    @TableField("current_number")
    private int currentNumber;

}
