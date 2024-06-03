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
 * 雨棚灯
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@ToString
@Getter
@Setter
@Accessors(chain = true)
@TableName("awning_light")
@ApiModel(value = "AwningLightEntity对象", description = "雨棚灯")
public class AwningLightEntity {

    @TableId(value = "awning_light_id", type = IdType.AUTO)
    private String awningLightId;

    @TableField("lane_infrastructure_id")
    private String laneInfrastructureId;

    @TableField(exist = false)
    private String fatherName;

    @TableField("awning_light_name")
    private String awningLightName;

    @ApiModelProperty("安装日期")
    @TableField("installation_date")
    private LocalDate installationDate;

    @ApiModelProperty("状态（连接还是未连接）")
    @TableField("state")
    private String state;

    @ApiModelProperty("灯具类型")
    @TableField("fixture_type")
    private String fixtureType;

    @ApiModelProperty("亮度")
    @TableField("brightness")
    private Float brightness;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;


}
