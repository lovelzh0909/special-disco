package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

     /**
     * 查询最后一条记录的questionId
     * @return JudgeQuestion
     */
    @Select("select id from question order by id desc limit 1")
    int findOnlyQuestionId();

    @Select("select * from question where type = #{type} and pointId in (#{s})")
    Question[] findsomeQuestion(String type ,String s);

    @Select("select * from question where type = #{type} and pointId in ({)#{pointId}) and id not in #{sid}")
    Question[] findanothorQuestion(String type ,String pointId,String sid);
}