package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;


import java.io.Serializable;

@Data
@TableName("user")
@Accessors(chain = true)

@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableField("phone")
    private String phone;

    @TableField("studentId")
    private String studentId;

    @TableField("password")
    private String password;

    @TableField("school")
    private String school;

    @TableField("name")
    private String name;

    @TableField("role")
    private String role;


}
