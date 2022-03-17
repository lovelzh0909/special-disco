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
 * 用于存储学生问题答案	
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("paper_justify")
@ApiModel(value = "PaperJustify对象", description = "用于存储学生问题答案	")
public class PaperJustify implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Id")
    private Integer Id;

    @TableField("questionId")
    private Integer questionId;

    @TableField("studentId")
    private Integer studentId;

    @TableField("studentAnswer")
    private String exmaineAnswer;

    @TableField("correctAnswer")
    private String correctAnswer;

    @ApiModelProperty("de fen")
    @TableField("justify")
    private Integer score;

    @TableField("score")
    private Integer totalscore;

    @TableField("testId")
    private Integer testId;


}
