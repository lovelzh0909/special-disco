package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;


import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;


/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
@Data
@Accessors(chain = true)
@TableName("papers")
@ApiModel(value = "Papers对象", description = "")
public class Papers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "paperId", type = IdType.AUTO)
    private Integer paperId;

    @TableField("createrPhone")
    private String Phone;

    @TableField("papercontext")
    private String papercontext;

    @ApiModelProperty("试卷使用状态（待发布，已发布，已完成，答题中，待批阅，批阅完成）")
    @TableField("paperstatus")
    private String paperstatus;

    @ApiModelProperty("使用次数	")
    @TableField("papernum")
    private Integer papernum;

    @ApiModelProperty("备注")
    @TableField("note")
    private String note;

    @ApiModelProperty("试卷名")
    @TableField("source")
    private String source;

    @TableField("totalScore")
    private double totalScore;

    @TableField("totalTime")
    private String totalTime;

    @TableField("createtime")
    private String createTime;
    
    @TableField("description")
    private String description;

    @Version
    private int version;

}
