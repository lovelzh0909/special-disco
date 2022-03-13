package com.example.demo.GA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

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
@TableName("rule")
@ApiModel(value = "Rule对象", description = "")
public class Rule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private long id;

    @TableField("testId")
    private long testId;

    @TableField("totalMark")
    private double totalMark;

    @TableField("difficulty")
    private double difficulty;

    @TableField("singleNum")
    private Integer singleNum;

    @TableField("singleScore")
    private double singleScore;

    @TableField("completeNum")
    private Integer completeNum;

    @TableField("completeScore")
    private double completeScore;

    @TableField("subjectiveNum")
    private Integer subjectiveNum;

    @TableField("subjectiveScore")
    private double subjectiveScore;

    @TableField("createTime")
    private Date createTime;

    private List<String> pointIds;
    public List<String> getPointIds() {
        return pointIds;
    }
    public void setPointIds(String pointIds) {
        // 是否是表单传过来的数据
        if (pointIds.endsWith("#")) {
            pointIds = pointIds.substring(0, pointIds.lastIndexOf("#"));
        }
        // 使用HashSet去重
        this.pointIds = new ArrayList<>(new HashSet<>(Arrays.asList(pointIds.split("#"))));
    }
    public Rule(long id, long testId, int totalMark, double difficulty, int singleNum, double singleScore,
                    int completeNum, double completeScore, int subjectiveNum, double subjectiveScore, List<String> pointIds,
                Date createTime) {
        this.id = id;
        this.testId = testId;
        this.totalMark = totalMark;
        this.difficulty = difficulty;
        this.singleNum = singleNum;
        this.singleScore = singleScore;
        this.completeNum = completeNum;
        this.completeScore = completeScore;
        this.subjectiveNum = subjectiveNum;
        this.subjectiveScore = subjectiveScore;
        this.pointIds = pointIds;
        this.createTime = createTime;
    }
}
