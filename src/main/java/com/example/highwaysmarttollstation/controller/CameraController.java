package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.highwaysmarttollstation.entity.CameraEntity;
import com.example.highwaysmarttollstation.entity.PreTransactionGantryEquipmentEntity;
import com.example.highwaysmarttollstation.mapper.CameraMapper;
import com.example.highwaysmarttollstation.mapper.PreTransactionGantryEquipmentMapper;
import com.example.highwaysmarttollstation.service.CameraService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 摄像头 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/camera-entity")
public class CameraController {

    @Resource
    CameraMapper cameraMapper;

    /**
     * 根据id获取摄像头信息
     *
     * @param id 摄像头id
     * @return CameraEntity
     */
    @GetMapping("/getCamera/{id}")
    public CameraEntity getCameraById(@PathVariable String id) {
        return cameraMapper.selectById(id);
    }

    /**
     * 更新摄像头信息
     *
     * @param cameraEntity 摄像头实体
     * @return List<CameraEntity>
     */
    @PostMapping("/updateCamera")
    public List<CameraEntity> updateCamera(@RequestBody CameraEntity cameraEntity) {
        cameraMapper.updateById(cameraEntity);
        return cameraMapper.selectList(new QueryWrapper<CameraEntity>().eq("transaction_id", cameraEntity.getTransactionId()));
    }

    /**
     * 添加摄像头
     *
     * @param cameraEntity 摄像头实体
     * @return CameraEntity
     */
    @PostMapping("/addCamera")
    public CameraEntity addCamera(@RequestBody CameraEntity cameraEntity) {
        cameraEntity.setCameraId(UUID.randomUUID().toString());
        cameraMapper.insert(cameraEntity);
        return cameraEntity;
    }

    /**
     * 删除摄像头
     *
     * @param cameraId 摄像头ID
     * @return List<CameraEntity>
     */
    @GetMapping("/deleteCamera/{cameraId},{transactionId}")
    public List<CameraEntity> deleteCamera(@PathVariable String cameraId, @PathVariable String transactionId) {
        cameraMapper.deleteById(cameraId);
        return cameraMapper.selectList(new QueryWrapper<CameraEntity>().eq("transaction_id", transactionId));
    }

    /**
     * 获取所有摄像头
     *
     * @return List<CameraEntity>
     */
    @GetMapping("/getAllCamera")
    public List<CameraEntity> getAllCamera() {
        return cameraMapper.getAllCamera();
    }


}
