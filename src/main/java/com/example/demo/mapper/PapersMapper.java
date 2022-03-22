package com.example.demo.mapper;

import com.example.demo.entity.Papers;
import com.example.demo.entity.Question;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
@Mapper
public interface PapersMapper extends BaseMapper<Papers> {

    @Select("select questionId from paper_manage where paperId = #{paperId}")
    List<Integer> findById(Integer paperId);

    @Select("SELECT question.`type`,question.`stem`,question.`choiceA`,question.`choiceB`,question.`choiceC`,question.`choiceD`,question.`choiceE`,question.`choiceF`,question.`choiceG`,question.`answer`,question.`score` FROM question,paper_manage WHERE question.id=paper_manage.questionId and paper_manage.paperId ="+"${paperId}")
    List<Question> getpapersQuestions(/*Page<StudentTestNoticeVO> page*/Integer paperId);

    @Select("select * from question where id = #{questionId}")
    Question getQuestions(/*Page<StudentTestNoticeVO> page*/Integer questionId);

    @Select("select paperId from papers order by id desc limit 1")
    int lastQuestionId();
}
