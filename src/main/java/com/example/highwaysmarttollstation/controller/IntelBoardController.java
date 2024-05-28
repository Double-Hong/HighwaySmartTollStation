package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.IntelBoardEntity;
import com.example.highwaysmarttollstation.mapper.IntelBoardMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * LED情报板 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/intel-board-entity")
public class IntelBoardController {

    @Resource
    IntelBoardMapper intelBoardMapper;

    @PostMapping("/updateIntelBoard")
    public int updateIntelBoard(@RequestBody IntelBoardEntity intelBoardEntity){
        return intelBoardMapper.updateById(intelBoardEntity);
    }

}
