package com.example.demo.mapper;

import com.example.demo.entity.PaperJustify;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用于存储学生问题答案	 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
@Mapper
public interface PaperJustifyMapper extends BaseMapper<PaperJustify> {

}
