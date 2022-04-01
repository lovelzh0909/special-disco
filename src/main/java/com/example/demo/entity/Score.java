package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class Score {
    @TableField("examCode")
    private Integer examCode;
    @TableField("studentId")
    private Integer studentId;
    @TableField("subject")
    private String subject;
    @TableField("ptScore")
    private Double ptScore;
    @TableField("etScore")
    private Double etScore;
    @TableField("score")
    private Double score;
    @TableField("scoreId")
    private Integer scoreId;
    @TableField("answerDate")
    private String answerDate;
}