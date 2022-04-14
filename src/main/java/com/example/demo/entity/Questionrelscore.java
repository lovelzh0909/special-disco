package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;

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
 * @since 2022-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("questionrelscore")
@ApiModel(value = "Questionrelscore对象", description = "")
public class Questionrelscore implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("paperId")
    private Integer paperId;

    @TableField("questionId")
    private Integer questionId;

    @TableField("score")
    private Double score;

    @Version
    private int version;

}
