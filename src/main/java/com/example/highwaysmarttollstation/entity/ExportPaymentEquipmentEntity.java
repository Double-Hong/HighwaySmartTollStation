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
 * 出口自助缴费设备
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("export_payment_equipment")
@ApiModel(value = "ExportPaymentEquipmentEntity对象", description = "出口自助缴费设备")
public class ExportPaymentEquipmentEntity {

    @TableId(value = "export_equipment_id", type = IdType.AUTO)
    private String exportEquipmentId;

    @TableField("lane_smart_device_id")
    private String laneSmartDeviceId;

    @TableField("export_name")
    private String exportName;

    @ApiModelProperty("安装日期")
    @TableField("installation_date")
    private LocalDate installationDate;

    @ApiModelProperty("状态（连接还是未连接）")
    @TableField("state")
    private String state;

    @ApiModelProperty("打印收据的纸的数量")
    @TableField("receipt_number")
    private Integer receiptNumber;

    @ApiModelProperty("收费扫描器的状态")
    @TableField("scanner_state")
    private String scannerState;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;


}
