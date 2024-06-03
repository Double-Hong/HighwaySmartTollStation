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
 * 车道称重设备
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("lane_weighing_equipment")
@ApiModel(value = "LaneWeighingEquipmentEntity对象", description = "车道称重设备")
public class LaneWeighingEquipmentEntity {

    @TableId(value = "lane_weighing_id", type = IdType.AUTO)
    private String laneWeighingId;

    @TableField("lane_infrastructure_id")
    private String laneInfrastructureId;

    @TableField(exist = false)
    private String fatherName;

    @TableField("lane_weighing_name")
    private String laneWeighingName;

    @ApiModelProperty("安装日期")
    @TableField("installation_date")
    private LocalDate installationDate;

    @ApiModelProperty("状态（连接还是未连接）")
    @TableField("state")
    private String state;

    @ApiModelProperty("称重器状态")
    @TableField("weighing_machine_state")
    private Float weighingMachineState;

    @ApiModelProperty("称重显示器状态")
    @TableField("display_state")
    private String displayState;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;


}
