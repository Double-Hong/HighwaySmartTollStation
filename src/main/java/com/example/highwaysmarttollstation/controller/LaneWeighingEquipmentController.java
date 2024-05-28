package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.LaneWeighingEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.LaneWeighingEquipmentMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 车道称重设备 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/lane-weighing-equipment-entity")
public class LaneWeighingEquipmentController {

    @Resource
    LaneWeighingEquipmentMapper laneWeighingEquipmentMapper;

    @PostMapping("/updateLaneWeighingEquipment")
    public int updateLaneWeighingEquipment(@RequestBody LaneWeighingEquipmentEntity laneWeighingEquipmentEntity){
        return laneWeighingEquipmentMapper.updateById(laneWeighingEquipmentEntity);
    }

}
