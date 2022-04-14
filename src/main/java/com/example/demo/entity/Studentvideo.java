package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.Version;
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
 * @since 2022-03-12
 */
@Data
@Accessors(chain = true)
@TableName("studentvideo")
@ApiModel(value = "Studentvideo对象", description = "")
public class Studentvideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("phone")
    private String phone;

    @TableField("video")
    private String video;

    @TableField("time")
    private LocalDateTime time;

    @Version
    private int version;


}
