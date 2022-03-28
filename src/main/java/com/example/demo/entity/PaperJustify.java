package com.example.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(format="id")
    private Integer id;

    @TableField("questionId")
    @JSONField(format="questionId")
    private Integer questionId;

    @JSONField(format="studentphone")
    @TableField("studentphone")
    private String studentphone;

    @JSONField(format="exmaineAnswer")
    @TableField("studentAnswer")
    private String exmaineAnswer;

    @JSONField(format="correctAnswer")
    @TableField("correctAnswer")
    private String correctAnswer;

    @JSONField(format="score")
    @ApiModelProperty("1 dui 0 cuo")
    @TableField("justify")
    private Integer score;

    @JSONField(format="totalscore")
    @TableField("score")
    private Integer totalscore;

    @JSONField(format="testId")
    @TableField("testId")
    private Integer testId;


}
