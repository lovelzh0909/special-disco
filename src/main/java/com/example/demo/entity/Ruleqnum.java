package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2022-03-25
 */
@Data
@Accessors(chain = true)
@TableName("ruleqnum")
@ApiModel(value = "Ruleqnum对象", description = "")
public class Ruleqnum implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    @TableField("typeId")
    private Integer typeId;

    @ApiModelProperty("每种类型题目数量")
    @TableField("num")
    private Integer num;

    @TableField("ruleId")
    private Integer ruleId;

    @TableField("score")
    private Double score;


}
