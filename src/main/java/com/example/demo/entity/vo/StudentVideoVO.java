package com.example.demo.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "StudentVideoVO对象", description = "学生照片")
public class StudentVideoVO {
    private static final long serialVersionUID = 1L;

    @TableField("status")
    private Integer status;

    @TableField("phone")
    private String phone;

    @TableField("video")
    private String video;

    @TableField("time")
    private LocalDateTime time;

    @TableField("studentId")
    private String studentId;

    @TableField("name")
    private String name;

}
