package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.AwningLightEntity;
import com.example.highwaysmarttollstation.mapper.AwningLightMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 雨棚灯 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/awning-light-entity")
public class AwningLightController {

    @Resource
    AwningLightMapper awningLightMapper;

    @PostMapping("/updateAwningLight")
    public int updateAwningLight(@RequestBody AwningLightEntity awningLightEntity) {
        awningLightMapper.updateById(awningLightEntity);
        return 1;
    }

    /**
     * 添加雨棚灯
     *
     * @param awningLightEntity 雨棚灯实体
     * @return AwningLightEntity
     */
    @PostMapping("/addAwningLight")
    public AwningLightEntity addAwningLight(@RequestBody AwningLightEntity awningLightEntity) {
        awningLightEntity.setAwningLightId(UUID.randomUUID().toString());
        awningLightMapper.insert(awningLightEntity);
        return awningLightEntity;
    }

    /**
     * 删除雨棚灯
     *
     * @param awningLightId 雨棚灯ID
     * @return int
     */
    @GetMapping("/deleteAwningLight/{awningLightId}")
    public int deleteAwningLight(@PathVariable String awningLightId) {
        return awningLightMapper.deleteById(awningLightId);
    }

    /**
     * 获取所有雨棚灯
     * @return List<AwningLightEntity>
     */
    @GetMapping("/getAllAwningLight")
    public List<AwningLightEntity> getAllAwningLight(){
        return awningLightMapper.getAllAwningLight();
    }

}
