package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2022-03-03
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("test")
@ApiModel(value = "Test对象", description = "")
public class Test implements Serializable {

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
}
