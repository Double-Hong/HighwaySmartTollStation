package com.example.highwaysmarttollstation.controller;

import com.example.highwaysmarttollstation.entity.IntelBoardEntity;
import com.example.highwaysmarttollstation.mapper.IntelBoardMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public int updateIntelBoard(@RequestBody IntelBoardEntity intelBoardEntity) {
        return intelBoardMapper.updateById(intelBoardEntity);
    }

    /**
     * 添加LED情报板
     *
     * @param intelBoardEntity LED情报板实体
     * @return IntelBoardEntity
     */
    @PostMapping("/addIntelBoard")
    public IntelBoardEntity addIntelBoard(@RequestBody IntelBoardEntity intelBoardEntity) {
        intelBoardEntity.setLedBoardId(UUID.randomUUID().toString());
        intelBoardMapper.insert(intelBoardEntity);
        return intelBoardEntity;
    }

    /**
     * 删除LED情报板
     *
     * @param ledBoardId LED情报板ID
     * @return int
     */
    @GetMapping("/deleteIntelBoard/{ledBoardId}")
    public int deleteIntelBoard(@PathVariable String ledBoardId) {
        return intelBoardMapper.deleteById(ledBoardId);
    }

}
