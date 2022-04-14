package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;

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
 * @since 2022-03-28
 */
@Data
@Accessors(chain = true)
@TableName("teacherrelclass")
@ApiModel(value = "Teacherrelclass对象", description = "")
public class Teacherrelclass implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("teacher")
    private String teacher;

    @TableField("classroom")
    private String classroom;

    @Version
    private int version;


}
