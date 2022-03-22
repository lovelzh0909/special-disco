package com.example.demo.service.impl;

import com.example.demo.entity.Question;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Override
    public int lastQuestionId() {
        // TODO Auto-generated method stub
        return this.baseMapper.findOnlyQuestionId();
    }

    @Override
    public  Question[] getQuestionArray(String type, String substring) {
        // TODO Auto-generated method stub
        return this.baseMapper.findsomeQuestion(type,  substring);
    }

    

}
