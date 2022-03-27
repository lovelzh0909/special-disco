package com.example.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Question;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
public interface QuestionService extends IService<Question> {

    // static List<Question> getQuestionListWithOutSId(Question tmpQuestion) {
    //     return null;
    // }

    Question[] getQuestionArray(String type, String substring);

    int lastQuestionId();

    static List<Question> getQuestionListWithOutSId(String substring, String string, String type) {
        return null;
    }
}
