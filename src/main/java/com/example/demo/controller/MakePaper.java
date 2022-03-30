package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Papers;
import com.example.demo.entity.Question;
import com.example.demo.entity.Rule;
import com.example.demo.entity.Ruleqnum;
import com.example.demo.service.PapersService;
import com.example.demo.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/MakePaper")
public class MakePaper {
//    RuleService ruleService;
    @Autowired
    QuestionService questionService;

    @Autowired
    PapersService papersService;

    @PostMapping("/autoProblem")
    public CommonReturnType saveQuestion(@RequestBody Rule rule,@RequestBody List<Ruleqnum> ruleqnum,@PathVariable String coursename){
        List<Integer> list =new ArrayList<Integer>();
        List<Integer> questionnum = new ArrayList<Integer>();
        for(Ruleqnum r:ruleqnum){
            // questionnum.set(r.getTypeId(), (int) questionService.count(new QueryWrapper<Question> ().eq("type",1).eq("coursename", coursename)));
            questionnum.set(r.getTypeId(), (int) questionService.count(new QueryWrapper<Question> ().eq("quesTypeId",r.getTypeId()).eq("coursename", coursename).in("pointId", rule.getpointIdList())));
            if(questionnum.get(r.getTypeId())<r.getNum()){
                return CommonReturnType.create(null,"题库数目不足");
            }
            List<Question> qArray = questionService.list(new QueryWrapper<Question> ().eq("quesTypeId",r.getTypeId())
            .eq("coursename", coursename)
            .in("pointId", rule.getpointIdList()));
            list.addAll(randquestion(qArray,rule.getSingleNum(),r.getScore()));
           
        }
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getCreateTime()==null){
        //     //添加时间
        //     // Date d= new Date();
        // }
        // return testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
        // for(int i=0;i<20;i++){
        //      questionnum.set(i, (int) questionService.count(new QueryWrapper<Question> ().eq("type",1).eq("coursename", "string")));
        //     if(questionnum.get(i)==null){
                
        //     }
        //     // else if(questionnum.get(i))
        // } 
        // long chosenum=questionService.count(new QueryWrapper<Question> ().eq("type","choose"));
        // long fillblanknum=questionService.count(new QueryWrapper<Question> ().eq("type","fillblank"));
        // long subjectnum=questionService.count(new QueryWrapper<Question> ().eq("type","subjective"));
        // String idsString =rule.getPointIds().toString();
        // if(chosenum<rule.getSingleNum()||fillblanknum<rule.getCompleteNum()||subjectnum<rule.getSubjectiveNum()){
        //     return  CommonReturnType.create(null,"题库数目不足");
        // }
        // else{
        //     // @Autowired
        //     // Paper paper;
        //     for (String s: rule.getpointIdList()){
        //         System.out.println(s);
        //     }
        //     List<Integer> list =new ArrayList<Integer>();
        //     List<Question> singleArray = questionService.list(new QueryWrapper<Question>()
        //             .in("pointId", rule.getpointIdList())
        //             .eq("type", "choose"));

        //         System.out.println(singleArray.size());

        //     List<Question> comArray = questionService.list(new QueryWrapper<Question>()
        //             .in("pointId", rule.getpointIdList())
        //             .eq("type", "fillblank"));
        //     System.out.println(comArray.size());
        //     List<Question> subArray = questionService.list(new QueryWrapper<Question>()
        //             .in("pointId", rule.getpointIdList())
        //             .eq("type", "subjective"));
        //     System.out.println(subArray.size());
            // Question[] singleArray = questionService.getQuestionArray("choose", rule.getPointIds());
            // Question[] comArray = questionService.getQuestionArray("choose", rule.getPointIds());
            // Question[] subArray = questionService.getQuestionArray("choose", rule.getPointIds());
            // list.addAll(randquestion(singleArray,rule.getSingleNum(),2));
            // list.addAll(randquestion(comArray, rule.getCompleteNum(), 2));
            // list.addAll(randquestion(subArray, rule.getSubjectiveNum(), 2));
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
    /**
     * 
     * @param singleArray 该类型题库
     * @param chosenum 题目数目
     * @param s  题目分数
     * @return
     */
    private List<Integer> randquestion(List<Question> singleArray, long chosenum, Double s) {
            Question tmpQuestion;
            List<Integer> list =new ArrayList<Integer>();
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
                singleArray.set(index, tmpQuestion);}
            return list;
        }
    
        @PostMapping("/paperProblem/save")
        public CommonReturnType notice(@RequestParam int paperId, @RequestParam String papercontext) {
            //new CommonReturnType();
            boolean b= papersService.update(new UpdateWrapper<Papers>().set("papercontext",papercontext).eq("paperId", paperId));
            if(b){
                return CommonReturnType.create(null);
            }else{
                return CommonReturnType.create(null,"暂无通知");
            }
        }

    @PostMapping("/paperProblem/save/byListQuestion")
    public CommonReturnType saveProblem(@RequestParam int paperId, @RequestBody List<Question> l) {
        //new CommonReturnType();
        StringBuilder papercontext = new StringBuilder();
        for (Question q : l) {
            papercontext.append(q.getId());
            if (q == l.get(l.size() - 1)) {
                break;
            }
            papercontext.append(",");
        }
        boolean b = papersService.update(new UpdateWrapper<Papers>().set("papercontext", papercontext.toString()).eq("paperId", paperId));
        if (b) {
            return CommonReturnType.create(null);
        } else {
            return CommonReturnType.create(null, "暂无通知");
        }
    }

    
}
