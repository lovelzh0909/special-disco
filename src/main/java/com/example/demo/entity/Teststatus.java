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
 * @since 2022-03-25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("teststatus")
@ApiModel(value = "Teststatus对象", description = "")
public class Teststatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("testStatusId")
    private Integer testStatusId;

    @TableField("testStatus")
    private String testStatus;


}
