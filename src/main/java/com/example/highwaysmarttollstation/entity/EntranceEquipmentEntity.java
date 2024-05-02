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
 * 入口自助发卡设备
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("entrance_equipment")
@ApiModel(value = "EntranceEquipmentEntity对象", description = "入口自助发卡设备")
public class EntranceEquipmentEntity {

    @TableId(value = "entrance_equipment_id", type = IdType.AUTO)
    private String entranceEquipmentId;

    @TableField("lane_smart_device_id")
    private String laneSmartDeviceId;

    @TableField("entrance_name")
    private String entranceName;

    @ApiModelProperty("安装日期")
    @TableField("installation_date")
    private LocalDate installationDate;

    @ApiModelProperty("状态（连接还是未连接）")
    @TableField("state")
    private String state;

    @ApiModelProperty("机器内卡片数量")
    @TableField("card_number")
    private Integer cardNumber;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;


}
