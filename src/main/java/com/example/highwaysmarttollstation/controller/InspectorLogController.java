package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.highwaysmarttollstation.entity.InspectorLogEntity;
import com.example.highwaysmarttollstation.entity.IntelBoardEntity;
import com.example.highwaysmarttollstation.mapper.InspectorLogMapper;
import com.example.highwaysmarttollstation.mapper.IntelBoardMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 监测人员日志表 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-21 10:04:36
 */
@RestController
@RequestMapping("/inspector-log-entity")
public class InspectorLogController {

    @Resource
    InspectorLogMapper inspectorLogMapper;

    @GetMapping("/getLogByUserId/{uid}")
    public List<InspectorLogEntity> getLogByUserId(@PathVariable String uid) {
        return inspectorLogMapper.selectList(new QueryWrapper<InspectorLogEntity>().eq("uid",uid));
    }

}
