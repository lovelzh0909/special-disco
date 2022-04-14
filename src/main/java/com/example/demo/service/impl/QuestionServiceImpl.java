package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.GA.Global;
import com.example.demo.GA.Paper;
import com.example.demo.entity.Question;
import com.example.demo.entity.Rule;
import com.example.demo.entity.Ruleqnum;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@Slf4j
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

    @Override
    public  List<Paper> randpapers(int num,Rule rule){
        List<Paper> papers =new ArrayList<>();
        for(int i=1;i<=num;i++){
            Paper paper =new Paper();
            paper.setQuestionList(randpaper(rule));
            // 计算试卷知识点覆盖率
            paper.setKpCoverage(rule);
            // 计算试卷适应度
            paper.setAdaptationDegree(rule, Global.KP_WEIGHT, Global.DIFFCULTY_WEIGHt);
            papers.add(paper);

        }
        return  papers;
    }

    @Override
    public  List<Question> randpaper(Rule rule){
        List<Question> list = new ArrayList<>();
//        List<Integer> questionnum = new ArrayList<Integer>(typenum);
        String coursename = rule.getCoursename();
        int[] questionnnum  =new int[10];
        List<Ruleqnum>  ruleqnum =rule.getRuleqnumList();
        log.info("l"+ruleqnum);
        log.info("l"+rule.getpointIdList());
        log.info("l"+coursename);
        for (Ruleqnum r : ruleqnum) {
            if(r.getTypeId()!=null) {
                log.info("l" + r);
                // questionnum.set(r.getTypeId(), (int) questionService.count(new QueryWrapper<Question> ().eq("type",1).eq("coursename", coursename)));
                long l = count(new QueryWrapper<Question>().eq("quesTypeId", r.getTypeId())
                        .eq("coursename", coursename)
                        .in("pointId", rule.getpointIdList()));
                log.info("l" + l);
                log.info("l" + r.getTypeId());
                questionnnum[r.getTypeId()] = (int) l;
//            questionnum.set(r.getTypeId(), (int) l);
//            if (questionnum.get(r.getTypeId()) < r.getNum()) {
                if (questionnnum[r.getTypeId()] < r.getNum()) {
                    log.info("----------log----------");
                    log.info("***题目不足***");
                    return null;
                }
                /***
                 *   获取 该类型题目
                 */
                List<Question> qArray = list(new QueryWrapper<Question>().eq("quesTypeId", r.getTypeId())
                        .eq("coursename", coursename)
                        .in("pointId", rule.getpointIdList()));
                log.info("l:" + qArray + rule.getSingleNum() + r.getScore());
                list.addAll(randsquestion(qArray, r.getNum(), r.getScore()));
            }
        }
        log.info("--------------log-------------");
        log.info("l"+list);
        return list;
    }

    /**
     * 需求占比（题库）
     * @param rule
     * @return
     */
    @Override
    public  Double sumb(Rule rule){
        Double sum=1.0;
        String coursename = rule.getCoursename();
        List<Ruleqnum>  ruleqnum =rule.getRuleqnumList();
        for (Ruleqnum r : ruleqnum) {
            if(r.getTypeId()!=null) {
                log.info("l" + r);
                // questionnum.set(r.getTypeId(), (int) questionService.count(new QueryWrapper<Question> ().eq("type",1).eq("coursename", coursename)));
                long l = count(new QueryWrapper<Question>().eq("quesTypeId", r.getTypeId())
                        .eq("coursename", coursename)
                        .in("pointId", rule.getpointIdList()));
                sum =sum*(l/r.getNum());
                if (l < r.getNum()) {
                    log.info("----------log----------");
                    log.info("***题目不足***");
                    return null;
                }
            }
        }
        return sum;
    }
    /**
     * 非接口
     *
     * @param singleArray 该类型题库
     * @param chosenum    题目数目
     * @param s           题目分数
     * @return
     */
    @Override
    public List<Integer> randquestion(List<Question> singleArray, long chosenum, Double s) {
        Question tmpQuestion;
        List<Integer> list = new ArrayList<Integer>();
        Random r = new Random();
        for (int j = 0; j < chosenum; j++) {
            int index = r.nextInt(singleArray.size() - j);
            // 初始化分数
            singleArray.get(index).setScore(s);
            list.add(singleArray.get(index).getId());
            // paper.addQuestion(singleArray[index]);
            // 保证不会重复添加试题，把最后一位拿来覆盖，并减少随机长度
            tmpQuestion = singleArray.get(singleArray.size() - j - 1);
            singleArray.set(singleArray.size() - j - 1, singleArray.get(index));
            singleArray.set(index, tmpQuestion);
        }
        return list;
    }

    @Override
    public List<Question> randsquestion(List<Question> singleArray, long chosenum, Double s) {
        Question tmpQuestion;
        List<Question> list = new ArrayList<>();
        Random r = new Random();
        for (int j = 0; j < chosenum; j++) {
            int index = r.nextInt(singleArray.size() - j);
            // 初始化分数
            singleArray.get(index).setScore(s);
            list.add(singleArray.get(index));
            // paper.addQuestion(singleArray[index]);
            // 保证不会重复添加试题，把最后一位拿来覆盖，并减少随机长度
            tmpQuestion = singleArray.get(singleArray.size() - j - 1);
            singleArray.set(singleArray.size() - j - 1, singleArray.get(index));
            singleArray.set(index, tmpQuestion);
        }
        return list;
    }

    

}
