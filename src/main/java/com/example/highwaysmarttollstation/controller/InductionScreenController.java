package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.highwaysmarttollstation.entity.InductionScreenEntity;
import com.example.highwaysmarttollstation.entity.PreTransactionGantryEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.InductionScreenMapper;
import com.example.highwaysmarttollstation.mapper.PreTransactionGantryEquipmentMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 诱导屏 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/induction-screen-entity")
public class InductionScreenController {

    @Resource
    InductionScreenMapper inductionScreenMapper;

    @Resource
    PreTransactionGantryEquipmentMapper preTransactionGantryEquipmentMapper;

    /**
     * 根据id获取诱导屏信息
     * @param id 诱导屏id
     * @return InductionScreenEntity
     */
    @GetMapping("/getInductionScreen/{id}")
    public InductionScreenEntity getInductionScreenById(@PathVariable String id) {
        return inductionScreenMapper.selectById(id);
    }

    /**
     * 更新诱导屏信息
     * @param inductionScreenEntity 诱导屏实体
     * @return List<InductionScreenEntity>
     */
    @PostMapping("/updateInductionScreen")
    public List<InductionScreenEntity> updateInductionScreen(@RequestBody InductionScreenEntity inductionScreenEntity){
        inductionScreenMapper.updateById(inductionScreenEntity);
        return inductionScreenMapper.selectList(new QueryWrapper<InductionScreenEntity>().eq("transaction_id",inductionScreenEntity.getTransactionId()));
    }

    /**
     * 添加诱导屏
     * @param inductionScreenEntity 诱导屏实体
     * @return InductionScreenEntity
     */
    @PostMapping("/addInductionScreen")
    public InductionScreenEntity addInductionScreen(@RequestBody InductionScreenEntity inductionScreenEntity){
        PreTransactionGantryEquipmentEntity preTransactionGantryEquipmentEntity = preTransactionGantryEquipmentMapper.selectById(inductionScreenEntity.getTransactionId());
        preTransactionGantryEquipmentEntity.setCurrentNumber(preTransactionGantryEquipmentEntity.getCurrentNumber()+1);
        preTransactionGantryEquipmentEntity.setChildrenNumber(preTransactionGantryEquipmentEntity.getChildrenNumber()+1);
        if (preTransactionGantryEquipmentEntity.getCurrentNumber() == preTransactionGantryEquipmentEntity.getChildrenNumber()){
            preTransactionGantryEquipmentEntity.setState("连接");
        }else {
            preTransactionGantryEquipmentEntity.setState("未连接");
        }
        preTransactionGantryEquipmentMapper.updateById(preTransactionGantryEquipmentEntity);
        inductionScreenEntity.setInductionScreenId(UUID.randomUUID().toString());
        inductionScreenMapper.insert(inductionScreenEntity);
        return inductionScreenEntity;
    }

    /**
     * 删除诱导屏
     * @param inductionScreenId 诱导屏ID
     * @return int
     */
    @GetMapping("/deleteInductionScreen/{inductionScreenId}")
    public int deleteInductionScreen(@PathVariable String inductionScreenId) {
        PreTransactionGantryEquipmentEntity preTransactionGantryEquipmentEntity = preTransactionGantryEquipmentMapper.selectById(inductionScreenMapper.selectById(inductionScreenId).getTransactionId());
        preTransactionGantryEquipmentEntity.setCurrentNumber(preTransactionGantryEquipmentEntity.getCurrentNumber() - 1);
        preTransactionGantryEquipmentEntity.setChildrenNumber(preTransactionGantryEquipmentEntity.getChildrenNumber() - 1);
        if (preTransactionGantryEquipmentEntity.getCurrentNumber() == preTransactionGantryEquipmentEntity.getChildrenNumber()){
            preTransactionGantryEquipmentEntity.setState("连接");
        }else {
            preTransactionGantryEquipmentEntity.setState("未连接");
        }
        preTransactionGantryEquipmentMapper.updateById(preTransactionGantryEquipmentEntity);
        return inductionScreenMapper.deleteById(inductionScreenId);
    }

    /**
     * 获取所有诱导屏
     * @return List<InductionScreenEntity>
     */
    @GetMapping("/getAllInductionScreen")
    public List<InductionScreenEntity> getAllInductionScreen(){
        return inductionScreenMapper.getAllInductionScreen();
    }
}
