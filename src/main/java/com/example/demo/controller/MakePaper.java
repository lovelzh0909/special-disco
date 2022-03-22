package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Question;
import com.example.demo.entity.Rule;
import com.example.demo.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/MakePaper")
public class MakePaper {
//    RuleService ruleService;
    @Autowired
    QuestionService questionService;

    @PostMapping("/returnProblem")
    public CommonReturnType saveQuestion(@RequestBody Rule rule){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getCreateTime()==null){
        //     //添加时间
        //     // Date d= new Date();
        // }
        // return testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
        long chosenum=questionService.count(new QueryWrapper<Question> ().eq("type","choose"));
        long fillblanknum=questionService.count(new QueryWrapper<Question> ().eq("type","fillblank"));
        long subjectnum=questionService.count(new QueryWrapper<Question> ().eq("type","subjective"));
        // String idsString =rule.getPointIds().toString();
        if(chosenum<rule.getSingleNum()||fillblanknum<rule.getCompleteNum()||subjectnum<rule.getSubjectiveNum()){
            return  CommonReturnType.create(null,"题库数目不足");
        }
        else{
            // @Autowired
            // Paper paper;
            for (String s: rule.getpointIdList()){
                System.out.println(s);
            }
            List<Integer> list =new ArrayList<Integer>();
            List<Question> singleArray = questionService.list(new QueryWrapper<Question>()
                    .in("pointId", rule.getpointIdList())
                    .eq("type", "choose"));

                System.out.println(singleArray.size());

            List<Question> comArray = questionService.list(new QueryWrapper<Question>()
                    .in("pointId", rule.getpointIdList())
                    .eq("type", "fillblank"));
            System.out.println(comArray.size());
            List<Question> subArray = questionService.list(new QueryWrapper<Question>()
                    .in("pointId", rule.getpointIdList())
                    .eq("type", "subjective"));
            System.out.println(subArray.size());
            // Question[] singleArray = questionService.getQuestionArray("choose", rule.getPointIds());
            // Question[] comArray = questionService.getQuestionArray("choose", rule.getPointIds());
            // Question[] subArray = questionService.getQuestionArray("choose", rule.getPointIds());
            list.addAll(randquestion(singleArray,rule.getSingleNum(),rule));
            list.addAll(randquestion(comArray, rule.getCompleteNum(), rule));
            list.addAll(randquestion(subArray, rule.getSubjectiveNum(), rule));
            // Question tmpQuestion;
            // for (int j = 0; j < chosenum; j++) {
            //     int index = r.nextInt(singleArray.length - j);
            //     // 初始化分数
            //     singleArray[index].setScore(rule.getSingleScore());
            //     list.add(singleArray[index].getId());
            //     // paper.addQuestion(singleArray[index]);
            //     // 保证不会重复添加试题，把最后一位拿来覆盖，并减少随机长度
            //     tmpQuestion = singleArray[singleArray.length - j - 1];
            //     singleArray[singleArray.length - j - 1] = singleArray[index];
            //     singleArray[index] = tmpQuestion;}
                if(list.size()==0)
                return CommonReturnType.create(null,"添加失败");
                return CommonReturnType.create(list);
        }  
    }
    private List<Integer> randquestion(List<Question> singleArray, long chosenum, Rule rule) {
            Question tmpQuestion;
            List<Integer> list =new ArrayList<Integer>();
            Random r = new Random();
            for (int j = 0; j < chosenum; j++) {
                int index = r.nextInt(singleArray.size() - j);
                // 初始化分数
                singleArray.get(index).setScore(rule.getSingleScore());
                list.add(singleArray.get(index).getId());
                // paper.addQuestion(singleArray[index]);
                // 保证不会重复添加试题，把最后一位拿来覆盖，并减少随机长度
                tmpQuestion = singleArray.get(singleArray.size() - j - 1);
                singleArray.set(singleArray.size() - j - 1, singleArray.get(index));
                singleArray.set(index, tmpQuestion);}
            return list;
        }        



    
}
