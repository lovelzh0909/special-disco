package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;



import java.io.Serializable;

@Data
@TableName("user")
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

    @TableField("classroom")
    private String classroom;

    @Version
    private int version;

}
