package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.AwningLightEntity;
import com.example.highwaysmarttollstation.mapper.AwningLightMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public int updateAwningLight(@RequestBody AwningLightEntity awningLightEntity){
        awningLightMapper.updateById(awningLightEntity);
        return 1;
    }

}
