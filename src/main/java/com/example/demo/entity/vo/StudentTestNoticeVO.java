package com.example.demo.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "StudentTestNoticeVO对象", description = "")
public class StudentTestNoticeVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId("testid")
    private Integer testid;

    @TableField("testsettingid")
    private Integer testsettingid;

    @TableField("roomId")
    private Integer roomId;

    @TableField("note")
    private String note;

    @TableField("teststatus")
    private String teststatus;

    @TableField("testteacher")
    private String testteacher;

    @TableField("coursename")
    private String coursename;

    @TableField("testtime")
    private String testtime;

    @TableField("phone")
    private String phone;

    @TableField("createdate")
    private String createdate;

    @TableField("invigilator")
    private String invigilator;

    @TableField("timelast")
    private String timelast;

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
