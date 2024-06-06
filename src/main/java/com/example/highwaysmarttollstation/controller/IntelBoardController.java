package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.IntelBoardEntity;
import com.example.highwaysmarttollstation.entity.LaneInfrastructureEntity;
import com.example.highwaysmarttollstation.mapper.IntelBoardMapper;
import com.example.highwaysmarttollstation.mapper.LaneInfrastructureMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * LED情报板 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/intel-board-entity")
public class IntelBoardController {

    @Resource
    IntelBoardMapper intelBoardMapper;

    @Resource
    LaneInfrastructureMapper laneInfrastructureMapper;

    @PostMapping("/updateIntelBoard")
    public int updateIntelBoard(@RequestBody IntelBoardEntity intelBoardEntity) {
        return intelBoardMapper.updateById(intelBoardEntity);
    }

    /**
     * 添加LED情报板
     *
     * @param intelBoardEntity LED情报板实体
     * @return IntelBoardEntity
     */
    @PostMapping("/addIntelBoard")
    public IntelBoardEntity addIntelBoard(@RequestBody IntelBoardEntity intelBoardEntity) {
        LaneInfrastructureEntity laneInfrastructureEntity = laneInfrastructureMapper.selectById(intelBoardEntity.getLaneInfrastructureId());
        laneInfrastructureEntity.setCurrentNumber(laneInfrastructureEntity.getCurrentNumber() + 1);
        laneInfrastructureEntity.setChildrenNumber(laneInfrastructureEntity.getChildrenNumber() + 1);
        if (laneInfrastructureEntity.getCurrentNumber() == laneInfrastructureEntity.getChildrenNumber()){
            laneInfrastructureEntity.setState("连接");
        }else {
            laneInfrastructureEntity.setState("未连接");
        }
        laneInfrastructureMapper.updateById(laneInfrastructureEntity);
        intelBoardEntity.setLedBoardId(UUID.randomUUID().toString());
        intelBoardMapper.insert(intelBoardEntity);
        return intelBoardEntity;
    }

    /**
     * 删除LED情报板
     *
     * @param ledBoardId LED情报板ID
     * @return int
     */
    @GetMapping("/deleteIntelBoard/{ledBoardId}")
    public int deleteIntelBoard(@PathVariable String ledBoardId) {
        LaneInfrastructureEntity laneInfrastructureEntity = laneInfrastructureMapper.selectById(intelBoardMapper.selectById(ledBoardId).getLaneInfrastructureId());
        laneInfrastructureEntity.setCurrentNumber(laneInfrastructureEntity.getCurrentNumber() - 1);
        laneInfrastructureEntity.setChildrenNumber(laneInfrastructureEntity.getChildrenNumber() - 1);
        if (laneInfrastructureEntity.getCurrentNumber() == laneInfrastructureEntity.getChildrenNumber()){
            laneInfrastructureEntity.setState("连接");
        }else {
            laneInfrastructureEntity.setState("未连接");
        }
        laneInfrastructureMapper.updateById(laneInfrastructureEntity);
        return intelBoardMapper.deleteById(ledBoardId);
    }

    /**
     * 获取所有LED情报板
     * @return List<IntelBoardEntity>
     */
    @GetMapping("/getAllIntelBoard")
    public List<IntelBoardEntity> getAllIntelBoard(){
        return intelBoardMapper.getAllIntelBoard();
    }

}
