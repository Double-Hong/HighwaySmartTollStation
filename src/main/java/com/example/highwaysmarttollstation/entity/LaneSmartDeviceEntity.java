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
 * 车道智能自助设备
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("lane_smart_device")
@ApiModel(value = "LaneSmartDeviceEntity对象", description = "车道智能自助设备")
public class LaneSmartDeviceEntity {

    @TableId(value = "lane_smart_device_id", type = IdType.AUTO)
    private String laneSmartDeviceId;

    @TableField("entrance_equipment_id")
    private String entranceEquipmentId;

    @TableField("export_equipment_id")
    private String exportEquipmentId;

    @TableField("lane_smart_device_name")
    private String laneSmartDeviceName;

    @ApiModelProperty("安装日期")
    @TableField("installation_date")
    private LocalDate installationDate;

    @ApiModelProperty("状态（连接还是未连接）")
    @TableField("state")
    private String state;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;


}