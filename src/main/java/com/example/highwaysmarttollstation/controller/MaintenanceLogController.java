package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.highwaysmarttollstation.entity.MaintenanceLogEntity;
import com.example.highwaysmarttollstation.mapper.MaintenanceLogMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 维护日志 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/maintenance-log-entity")
public class MaintenanceLogController {

    @Resource
    MaintenanceLogMapper maintenanceLogMapper;

    @GetMapping("/getMaintenanceLogByUserId/{uid}")
    public List<MaintenanceLogEntity> getMaintenanceLogByUserId(@PathVariable String uid){
        return maintenanceLogMapper.selectList(new QueryWrapper<MaintenanceLogEntity>().eq("maintain_people",uid));
    }

}
