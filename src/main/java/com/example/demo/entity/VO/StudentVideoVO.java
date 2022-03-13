package com.example.demo.entity.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "StudentVideoVO对象", description = "")
public class StudentVideoVO {
    private static final long serialVersionUID = 1L;

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
