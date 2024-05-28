package com.example.highwaysmarttollstation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.highwaysmarttollstation.entity.UserInfoEntity;
import com.example.highwaysmarttollstation.mapper.UserInfoMapper;
import jakarta.annotation.Resource;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@RestController
@RequestMapping("/user-info-entity")
public class UserInfoController {

    @Resource
    UserInfoMapper userInfoMapper;

    /**
     * 登录
     *
     * @param password 密码
     * @param username 用户名
     * @return 一个用户信息实体类
     */
    @GetMapping("/login/{username},{password}")
    public UserInfoEntity login(@PathVariable String password, @PathVariable String username) {
        return userInfoMapper.selectOne(new QueryWrapper<UserInfoEntity>().eq("username", username).eq("password", password));
    }

    /**
     * 通过uid获取用户信息
     *
     * @param uid 用户uid
     * @return 一个用户实体类
     */
    @GetMapping("/getUserInfoById/{uid}")
    public UserInfoEntity getUserInfoById(@PathVariable String uid) {
        return userInfoMapper.selectOne(new QueryWrapper<UserInfoEntity>().eq("uid", uid));
    }

    /**
     * 用户修改个人信息
     *
     * @param userInfoEntity 修改后的信息
     * @return 一个int数值，标识修改是否成功
     */
    @PostMapping("/modifyUserInfo")
    public int modifyUserInfo(@RequestBody UserInfoEntity userInfoEntity) {
        return userInfoMapper.updateById(userInfoEntity);
    }

    @GetMapping("/modifyPassword/{uid},{password}")
    public int modifyPassword(@PathVariable String uid, @PathVariable String password) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setPassword(password);
        UpdateWrapper<UserInfoEntity> userInfoEntityUpdateWrapper = new UpdateWrapper<>();
        userInfoEntityUpdateWrapper.eq("uid",uid);
        return userInfoMapper.update(userInfoEntity,userInfoEntityUpdateWrapper);
    }

    /**
     * 获取所有维修人员和监测人员
     * @return 所有维修人员和监测人员
     */
    @GetMapping("/getAllUserInfo")
    public List<UserInfoEntity> getAllUserInfo(){
        return userInfoMapper.selectList(new QueryWrapper<UserInfoEntity>().eq("type",2).or().eq("type",3));
    }

}
