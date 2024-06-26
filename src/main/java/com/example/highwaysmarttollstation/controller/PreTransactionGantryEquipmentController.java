package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.highwaysmarttollstation.Result;
import com.example.highwaysmarttollstation.entity.CameraEntity;
import com.example.highwaysmarttollstation.entity.DTO.PreTransactionDTO;
import com.example.highwaysmarttollstation.entity.EtcAntennaEntity;
import com.example.highwaysmarttollstation.entity.InductionScreenEntity;
import com.example.highwaysmarttollstation.entity.PreTransactionGantryEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.CameraMapper;
import com.example.highwaysmarttollstation.mapper.EtcAntennaMapper;
import com.example.highwaysmarttollstation.mapper.InductionScreenMapper;
import com.example.highwaysmarttollstation.mapper.PreTransactionGantryEquipmentMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 预交易门架设备 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/pre-transaction-gantry-equipment-entity")
public class PreTransactionGantryEquipmentController {

    @Resource
    PreTransactionGantryEquipmentMapper preTransactionGantryEquipmentMapper;

    @Resource
    CameraMapper cameraMapper;

    @Resource
    InductionScreenMapper inductionScreenMapper;

    @Resource
    EtcAntennaMapper etcAntennaMapper;

    /**
     * 获取所有预交易门架设备基础信息
     *
     * @return 所有预交易门架设备基础信息
     */
    @GetMapping("/getAllTransactionEquipment")
    public List<PreTransactionGantryEquipmentEntity> getAllTransactionEquipment() {
        return preTransactionGantryEquipmentMapper.selectList(null);
    }


    /**
     * 根据id获取预交易门架设备基础信息
     *
     * @param uid 门架设备uid
     * @return PreTransactionGantryEquipmentEntity
     */
    @GetMapping("/getTransactionDetailById/{uid}")
    public Result<?> getTransactionDetailById(@PathVariable String uid) {
        PreTransactionDTO preTransactionDTO = new PreTransactionDTO();
        preTransactionDTO.cameraEntities = cameraMapper.selectList(new QueryWrapper<CameraEntity>().eq("transaction_id", uid));
        preTransactionDTO.etcAntennaEntities = etcAntennaMapper.selectList(new QueryWrapper<EtcAntennaEntity>().eq("transaction_id",uid));
        preTransactionDTO.inductionScreenEntities = inductionScreenMapper.selectList(new QueryWrapper<InductionScreenEntity>().eq("transaction_id",uid));

        return Result.success(preTransactionDTO);
    }

    /**
     * 更新预交易门架设备基础信息
     * @param preTransactionGantryEquipmentEntity 预交易门架设备实体
     * @return List<PreTransactionGantryEquipmentEntity>
     */
    @PostMapping("/updateTransactionDetail")
    public List<PreTransactionGantryEquipmentEntity> updateTransactionDetail(@RequestBody PreTransactionGantryEquipmentEntity preTransactionGantryEquipmentEntity){
        preTransactionGantryEquipmentMapper.updateById(preTransactionGantryEquipmentEntity);
        return preTransactionGantryEquipmentMapper.selectList(null);
    }

    /**
     * 添加预交易门架设备
     * @param preTransactionGantryEquipmentEntity 预交易门架设备实体
     * @return List<PreTransactionGantryEquipmentEntity>
     */
    @PostMapping("/addTransactionDetail")
    public List<PreTransactionGantryEquipmentEntity> addTransactionDetail(@RequestBody PreTransactionGantryEquipmentEntity preTransactionGantryEquipmentEntity){
        preTransactionGantryEquipmentEntity.setState("未连接");
        UUID uuid = UUID.randomUUID();
        preTransactionGantryEquipmentEntity.setTransactionId(uuid.toString());
        preTransactionGantryEquipmentMapper.insert(preTransactionGantryEquipmentEntity);
        return preTransactionGantryEquipmentMapper.selectList(null);
    }

    @GetMapping("/deleteTransactionDetail/{uid}")
    public List<PreTransactionGantryEquipmentEntity> deleteTransactionDetail(@PathVariable String uid){
        preTransactionGantryEquipmentMapper.deleteById(uid);
        return preTransactionGantryEquipmentMapper.selectList(null);
    }
}
