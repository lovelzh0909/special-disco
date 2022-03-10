package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @author 作者
 * @since 2022-03-06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("teachertest")
@ApiModel(value = "Teachertest对象", description = "")
public class Teachertest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("testname")
    private String testname;

    @TableField("testtime")
    private LocalDateTime testtime;

    @TableField("roomId")
    private Integer roomId;

    @TableField("testteacher")
    private String testteacher;

    @TableField("testtype")
    private String testtype;

    @TableField("note")
    private String note;

    @TableField("status1")
    private String status1;

    @TableField("status2")
    private String status2;

    @TableField("phone")
    private String phone;


}
