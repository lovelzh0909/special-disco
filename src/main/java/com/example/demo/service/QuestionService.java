package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.GA.Global;
import com.example.demo.GA.Paper;
import com.example.demo.entity.Question;
import com.example.demo.entity.Rule;
import com.example.demo.entity.Ruleqnum;


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

    List<Paper> randpapers(int num, Rule rule);

    List<Question> randpaper(Rule rule);

    List<Integer> randquestion(List<Question> singleArray, long chosenum, Double s);

    List<Question> randsquestion(List<Question> singleArray, long chosenum, Double s);

    Double sumb(Rule rule);
}
