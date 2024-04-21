package com.example.highwaysmarttollstation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Double-Hong and My-way and 何栋梁 and 肖雅云
 * @since 2024-04-21 18:21:07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_info")
@ApiModel(value = "UserInfoEntity对象", description = "")
public class UserInfoEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField("username")
    private String username;

    @TableField("`password`")
    private String password;

    @TableField("`name`")
    private String name;


}
