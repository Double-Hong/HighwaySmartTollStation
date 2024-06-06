package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.highwaysmarttollstation.entity.EtcAntennaEntity;
import com.example.highwaysmarttollstation.entity.PreTransactionGantryEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.EtcAntennaMapper;
import com.example.highwaysmarttollstation.mapper.PreTransactionGantryEquipmentMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * ETC天线 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/etc-antenna-entity")
public class EtcAntennaController {

    @Resource
    EtcAntennaMapper etcAntennaMapper;

    @Resource
    PreTransactionGantryEquipmentMapper preTransactionGantryEquipmentMapper;

    /**
     * 根据id获取ETC天线信息
     * @param id 天线id
     * @return EtcAntennaEntity
     */
    @GetMapping("/getAntenna/{id}")
    public EtcAntennaEntity getAntennaById(@PathVariable String id) {
        return etcAntennaMapper.selectById(id);
    }

    /**
     * 更新ETC天线信息
     * @param etcAntennaEntity 天线实体
     * @return List<EtcAntennaEntity>
     */
    @PostMapping("/updateAntenna")
    public List<EtcAntennaEntity> updateAntenna(@RequestBody EtcAntennaEntity etcAntennaEntity) {
        etcAntennaMapper.updateById(etcAntennaEntity);
        return etcAntennaMapper.selectList(new QueryWrapper<EtcAntennaEntity>().eq("transaction_id",etcAntennaEntity.getTransactionId()));
    }

    /**
     * 添加ETC天线
     * @param etcAntennaEntity 天线实体
     * @return EtcAntennaEntity
     */
    @PostMapping("/addAntenna")
    public EtcAntennaEntity addAntenna(@RequestBody EtcAntennaEntity etcAntennaEntity) {
        etcAntennaEntity.setAntennaId(UUID.randomUUID().toString());
        PreTransactionGantryEquipmentEntity preTransactionGantryEquipmentEntity = preTransactionGantryEquipmentMapper.selectById(etcAntennaEntity.getTransactionId());
        preTransactionGantryEquipmentEntity.setCurrentNumber(preTransactionGantryEquipmentEntity.getCurrentNumber()+1);
        preTransactionGantryEquipmentEntity.setChildrenNumber(preTransactionGantryEquipmentEntity.getChildrenNumber()+1);
        if (preTransactionGantryEquipmentEntity.getCurrentNumber() == preTransactionGantryEquipmentEntity.getChildrenNumber()){
            preTransactionGantryEquipmentEntity.setState("连接");
        }else {
            preTransactionGantryEquipmentEntity.setState("未连接");
        }
        preTransactionGantryEquipmentMapper.updateById(preTransactionGantryEquipmentEntity);
        etcAntennaMapper.insert(etcAntennaEntity);
        return etcAntennaEntity;
    }

    /**
     * 删除ETC天线
     * @param antennaId 天线id
     * @return int
     */
    @GetMapping("/deleteAntenna/{antennaId}")
    public int deleteAntenna(@PathVariable String antennaId) {
        PreTransactionGantryEquipmentEntity preTransactionGantryEquipmentEntity = preTransactionGantryEquipmentMapper.selectById(etcAntennaMapper.selectById(antennaId).getTransactionId());
        preTransactionGantryEquipmentEntity.setCurrentNumber(preTransactionGantryEquipmentEntity.getCurrentNumber()-1);
        preTransactionGantryEquipmentEntity.setChildrenNumber(preTransactionGantryEquipmentEntity.getChildrenNumber()-1);
        if (preTransactionGantryEquipmentEntity.getCurrentNumber() == preTransactionGantryEquipmentEntity.getChildrenNumber()){
            preTransactionGantryEquipmentEntity.setState("连接");
        }else {
            preTransactionGantryEquipmentEntity.setState("未连接");
        }
        preTransactionGantryEquipmentMapper.updateById(preTransactionGantryEquipmentEntity);
        return etcAntennaMapper.deleteById(antennaId);
    }

    /**
     * 获取所有ETC天线
     * @return List<EtcAntennaEntity>
     */
    @GetMapping("/getAllAntenna")
    public List<EtcAntennaEntity> getAllAntenna() {
        return etcAntennaMapper.getAllEtcAntenna();
    }

}
