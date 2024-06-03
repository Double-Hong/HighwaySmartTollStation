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
 * ETC天线
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("etc_antenna")
@ApiModel(value = "EtcAntennaEntity对象", description = "ETC天线")
public class EtcAntennaEntity {

    @TableId(value = "antenna_id", type = IdType.AUTO)
    private String antennaId;

    @TableField("transaction_id")
    private String transactionId;

    @TableField(exist = false)
    private String fatherName;

    @ApiModelProperty("天线名称")
    @TableField("antenna_name")
    private String antennaName;

    @ApiModelProperty("安装日期")
    @TableField("installation_date")
    private LocalDate installationDate;

    @ApiModelProperty("工作频率")
    @TableField("frequency")
    private Float frequency;

    @ApiModelProperty("读取距离，ETC天线能够读取车辆电子标签的最大距离")
    @TableField("read_range")
    private Float readRange;

    @ApiModelProperty("波束宽度")
    @TableField("beam_width")
    private Float beamWidth;

    @ApiModelProperty("状态（连接还是未连接）")
    @TableField("state")
    private String state;

    @ApiModelProperty("IP地址")
    @TableField("equipment_ip")
    private String equipmentIp;


}
