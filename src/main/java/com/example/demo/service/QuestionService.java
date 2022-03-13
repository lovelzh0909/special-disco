package com.example.demo.service;

import com.example.demo.GA.Question;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
public interface QuestionService extends IService<Question> {

    static List<Question> getQuestionListWithOutSId(Question tmpQuestion) {
        return null;
    }

    static Question[] getQuestionArray(int type, String substring) {
        return null;
    }
}
