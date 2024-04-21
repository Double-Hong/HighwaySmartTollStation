package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.highwaysmarttollstation.entity.UserInfoEntity;
import com.example.highwaysmarttollstation.mapper.UserInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Double-Hong and My-way and 何栋梁 and 肖雅云
 * @since 2024-04-21 18:21:07
 */
@RestController
@RequestMapping("/user-info-entity")
public class UserInfoController {

    @Resource
    UserInfoMapper userInfoMapper;

    @GetMapping("/login")
    public UserInfoEntity login(){
        return userInfoMapper.selectOne(new QueryWrapper<UserInfoEntity>().eq("id","001"));
    }

}
