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
 * @since 2022-03-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("papers")
@ApiModel(value = "Papers对象", description = "")
public class Papers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("paperId")
    private Integer paperId;

    @TableField("papername")
    private String papername;

    @TableField("createrPhone")
    private Integer createrPhone;

    @TableField("userphone")
    private Integer userphone;

    @TableField("papercontext")
    private String papercontext;

    @ApiModelProperty("试卷使用状态（待发布，已发布，已完成，答题中，待批阅，批阅完成）")
    @TableField("paperstatus")
    private String paperstatus;

    @ApiModelProperty("使用次数	")
    @TableField("papernum")
    private Integer papernum;

    @TableField("paperAnswer")
    private String paperAnswer;




}
