package com.example.highwaysmarttollstation.service;

import com.example.highwaysmarttollstation.entity.UserInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
public interface UserInfoService extends IService<UserInfoEntity> {

    void backupAllDeviceLogs();
}
