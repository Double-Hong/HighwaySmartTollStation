package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.highwaysmarttollstation.entity.AccendantLogEntity;
import com.example.highwaysmarttollstation.mapper.AccendantLogMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 维修人员日志 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-21 10:04:36
 */
@RestController
@RequestMapping("/accendant-log-entity")
public class AccendantLogController {

    @Resource
    AccendantLogMapper accendantLogMapper;

    @GetMapping("/getAccendantLogByUserId/{uid}")
    public List<AccendantLogEntity> getAccendantLogByUserId(@PathVariable String uid){
        return accendantLogMapper.selectList(new QueryWrapper<AccendantLogEntity>().eq("uid",uid));
    }

}
