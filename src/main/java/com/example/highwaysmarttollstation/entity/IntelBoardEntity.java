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
 * LED情报板
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("intel_board")
@ApiModel(value = "IntelBoardEntity对象", description = "LED情报板")
public class IntelBoardEntity {

    @TableId(value = "led_board_id", type = IdType.AUTO)
    private String ledBoardId;

    @TableField("lane_infrastructure_id")
    private String laneInfrastructureId;

    @TableField("led_board_name")
    private String ledBoardName;

    @ApiModelProperty("状态（连接还是未连接）")
    @TableField("state")
    private String state;

    @ApiModelProperty("安装日期")
    @TableField("installation_date")
    private LocalDate installationDate;

    @ApiModelProperty("显示率，表示显示屏上有多少像素点是正常显示的")
    @TableField("display_rate")
    private Float displayRate;

    @ApiModelProperty("亮度")
    @TableField("brightness")
    private Float brightness;

    @ApiModelProperty("对比度")
    @TableField("contrast_ratio")
    private Float contrastRatio;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;


}
