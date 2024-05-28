package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.highwaysmarttollstation.entity.FaultLogEntity;
import com.example.highwaysmarttollstation.mapper.FaultLogMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 故障日志表 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/fault-log-entity")
public class FaultLogController {

    @Resource
    FaultLogMapper faultLogMapper;

    @GetMapping("/getFaultLogByUserId/{uid}")
    public List<FaultLogEntity> getFaultLogByUserId(@PathVariable String uid){
        return faultLogMapper.selectList(new QueryWrapper<FaultLogEntity>().eq("writer_id",uid));
    }

}
