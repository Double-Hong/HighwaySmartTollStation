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
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 摄像头
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@ToString
@Getter
@Setter
@Accessors(chain = true)
@TableName("camera")
@ApiModel(value = "CameraEntity对象", description = "摄像头")
public class CameraEntity {

    @TableId(value = "camera_id", type = IdType.AUTO)
    private String cameraId;

    @TableField("lane_infrastructure_id")
    private String laneInfrastructureId;

    @TableField("transaction_id")
    private String transactionId;

    @TableField(exist = false)
    private String fatherName;

    @ApiModelProperty("摄像头名称")
    @TableField("camera_name")
    private String cameraName;

    @ApiModelProperty("安装日期")
    @TableField("installation_date")
    private LocalDate installationDate;

    @ApiModelProperty("焦距")
    @TableField("focal_length")
    private Float focalLength;

    @ApiModelProperty("光圈")
    @TableField("aperture")
    private String aperture;

    @ApiModelProperty("状态（连接还是未连接）")
    @TableField("state")
    private String state;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;


}
