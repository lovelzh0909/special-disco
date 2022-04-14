package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@Data
@Accessors(chain = true)
@TableName("question")
@ApiModel(value = "Question对象", description = "")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("quesTypeId")
    private int quesTypeId;

    @TableField("coursename")
    private String coursename;

    @TableField("stem")
    private String stem;

    @TableField("choiceA")
    private String choiceA;

    @TableField("choiceB")
    private String choiceB;

    @TableField("choiceC")
    private String choiceC;

    @TableField("choiceD")
    private String choiceD;

    @TableField("choiceE")
    private String choiceE;

    @TableField("choiceF")
    private String choiceF;

    @TableField("choiceG")
    private String choiceG;

    @TableField("answer")
    private String answer;

    @TableField("userId")
    private String userId;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("userName")
    private String userName;

    @TableField("knowledgeName")
    private String knowledgeName;

    @TableField("score")
    private Double score;

    @TableField("difficulty")
    private Double difficulty;

    @TableField("pointId")
    private String pointId;

    @TableField("content")
    private String content;

    @TableField(exist = false)
    private  String studentAnswer;

    @TableField(exist = false)
    private  Double getScore;

    @Version
    private int version;

}
