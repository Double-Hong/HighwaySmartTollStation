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
 * 车检器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("car_detector")
@ApiModel(value = "CarDetectorEntity对象", description = "车检器")
public class CarDetectorEntity {

    @TableId(value = "car_detector_id", type = IdType.AUTO)
    private String carDetectorId;

    @TableField("lane_infrastructure_id")
    private String laneInfrastructureId;

    @TableField("car_detector_name")
    private String carDetectorName;

    @ApiModelProperty("安装日期")
    @TableField("installation_date")
    private LocalDate installationDate;

    @ApiModelProperty("状态（连接还是未连接）")
    @TableField("state")
    private String state;

    @ApiModelProperty("检测方式,描述车检器采用的检测技术，如电感、红外线、微波雷达、摄像头等")
    @TableField("detection_method")
    private String detectionMethod;

    @ApiModelProperty("检测范围,指明车检器能够检测到车辆的范围，通常以距离或者角度来描述。")
    @TableField("detection_range")
    private Float detectionRange;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;


}
