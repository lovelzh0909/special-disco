package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@Getter
@ToString
@Setter
@Accessors(chain = true)
@TableName("test")
@ApiModel(value = "Test对象", description = "")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("testId")
    private Integer testId;

    @TableField("testsettingid")
    private Integer testsettingid;

    @TableField("roomId")
    private Integer roomId;

    @TableField("note")
    private String note;

    @TableField("teststatus")
    private int teststatus;

    @TableField("invigilator")
    private String invigilator;

    @TableField("testteacher")
    private String testteacher;

    @TableField("coursename")
    private String coursename;


    @TableField("createdate")
    private String createdate;

    @TableField("testtime")
    private String testtime;

    @TableField("timelast")
    private String timelast;

    @TableField("pystatus")
    private String pystatus;

    @TableField("teacherphone")
    private String teacherphone;

    @TableField("paperId")
    private int paperId;

    @TableField("testtype")
    private String testtype;

    @TableField("invigilatorId")
    private String invigilatorId;

    @TableField(exist = false)
    private List<String> studentphone;
}
