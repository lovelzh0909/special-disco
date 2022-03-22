package com.example.demo.service;

import com.example.demo.entity.Papers;
import com.example.demo.entity.Question;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
public interface PapersService extends IService<Papers> {

    List<Question> getpapersQuestions(/*Page<StudentTestNoticeVO> page*/Integer paperId);
    Question getQuestions(/*Page<StudentTestNoticeVO> page*/Integer questionId);

    int findOnlyQuestionId();

}
