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
 * @since 2022-03-23
 */
@Data
@Accessors(chain = true)
@TableName("testrelstudent")
@ApiModel(value = "Testrelstudent对象", description = "")
public class Testrelstudent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("testId")
    private Integer testId;

    @TableField("studentPhone")
    private String studentPhone;

    @TableField("status")
    private int status;

    @Version
    private int version;


}
