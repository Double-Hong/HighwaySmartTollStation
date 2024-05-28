package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.highwaysmarttollstation.Result;
import com.example.highwaysmarttollstation.entity.*;
import com.example.highwaysmarttollstation.entity.DTO.LaneInfrastructureDTO;
import com.example.highwaysmarttollstation.mapper.*;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 车道基础设备 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/lane-infrastructure-entity")
public class LaneInfrastructureController {

    @Resource
    LaneInfrastructureMapper laneInfrastructureMapper;

    @Resource
    AwningLightMapper awningLightMapper;

    @Resource
    CarDetectorMapper carDetectorMapper;

    @Resource
    IntelBoardMapper intelBoardMapper;

    @Resource
    LaneWeighingEquipmentMapper laneWeighingEquipmentMapper;

    /**
     * 获取所有车道基础设备信息
     * @return List<LaneInfrastructureEntity>
     */
    @GetMapping("/getAllLaneInfrastructure")
    public List<LaneInfrastructureEntity> getAllLaneInfrastructure(){
        return laneInfrastructureMapper.selectList(null);
    }

    @GetMapping("/getLaneInfrastructureDetailById/{id}")
    public Result<?> getLaneInfrastructureDetailById(@PathVariable String id){
        LaneInfrastructureDTO laneInfrastructureDTO = new LaneInfrastructureDTO();
        laneInfrastructureDTO.awningLightEntity = awningLightMapper.selectOne(new QueryWrapper<AwningLightEntity>().eq("lane_infrastructure_id",id));
        laneInfrastructureDTO.carDetectorEntity = carDetectorMapper.selectOne(new QueryWrapper<CarDetectorEntity>().eq("lane_infrastructure_id",id));
        laneInfrastructureDTO.intelBoardEntity = intelBoardMapper.selectOne(new QueryWrapper<IntelBoardEntity>().eq("lane_infrastructure_id",id));
        laneInfrastructureDTO.laneWeighingEquipmentEntity = laneWeighingEquipmentMapper.selectOne(new QueryWrapper<LaneWeighingEquipmentEntity>().eq("lane_infrastructure_id",id));
        return Result.success(laneInfrastructureDTO);
    }

    @PostMapping("/updateLaneInfrastructure")
    public List<LaneInfrastructureEntity> updateLaneInfrastructure(@RequestBody LaneInfrastructureEntity laneInfrastructureEntity) {
        laneInfrastructureMapper.updateById(laneInfrastructureEntity);
        return laneInfrastructureMapper.selectList(null);
    }
}
