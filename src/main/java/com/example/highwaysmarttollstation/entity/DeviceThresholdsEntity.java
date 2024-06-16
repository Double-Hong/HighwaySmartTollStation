package com.example.highwaysmarttollstation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 阈值
 * </p>
 *
 * @author Double-Hong
 * @since 2024-06-07 17:21:33
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("device_thresholds")
@ApiModel(value = "DeviceThresholdsEntity对象", description = "阈值")
public class DeviceThresholdsEntity {

    @TableId(value = "thresholds_id", type = IdType.AUTO)
    private String thresholdsId;

    @TableField("focal_length")
    private Float focalLength;

    @TableField("aperture")
    private Float aperture;

    @TableField("awning_brightness")
    private Float awningBrightness;

    @TableField("car_detector_range")
    private Float carDetectorRange;

    @TableField("card_number")
    private Integer cardNumber;

    @TableField("etc_frequency")
    private Float etcFrequency;

    @TableField("etc_read_range")
    private Float etcReadRange;

    @TableField("etc_beam_width")
    private Float etcBeamWidth;

    @TableField("receipt_number")
    private Integer receiptNumber;

    @TableField("scanner_state")
    private Float scannerState;

    @TableField("induction_brightness")
    private Float inductionBrightness;

    @TableField("induction_contrast_ratio")
    private Float inductionContrastRatio;

    @TableField("induction_display_rate")
    private Float inductionDisplayRate;

    @TableField("intel_board_brightness")
    private Float intelBoardBrightness;

    @TableField("intel_board_contrast_ratio")
    private Float intelBoardContrastRatio;

    @TableField("intel_board_display_rate")
    private Float intelBoardDisplayRate;

    @TableField("weighing_machine_state")
    private Float weighingMachineState;

    @TableField("weighing_display_state")
    private Float weighingDisplayState;


}
