package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

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
 * @since 2022-03-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("notice")
@ApiModel(value = "Notice对象", description = "")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Integer id;

    @TableField("phone")
    private String phone;

    @TableField("notice")
    private String notice;

    @TableField("deadLine")
    private LocalDateTime deadLine;

    @TableField("creatTime")
    private LocalDateTime creatTime;


}
