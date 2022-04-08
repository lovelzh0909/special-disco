package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.GA.GA;
import com.example.demo.GA.Global;
import com.example.demo.GA.Paper;
import com.example.demo.GA.Population;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Papers;
import com.example.demo.entity.Question;
import com.example.demo.entity.Rule;
import com.example.demo.entity.Ruleqnum;
import com.example.demo.service.PapersService;
import com.example.demo.service.QuestionService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
@Slf4j
@RestController
@RequestMapping("/MakePaper")
public class MakePaper {
    //    RuleService ruleService;
    @Autowired
    QuestionService questionService;

    @Autowired
    PapersService papersService;

    /**
     * 返回问题id
     * @param rule
     * @return
     */
    @PostMapping("/autoProblem")
    public CommonReturnType saveQuestion(@RequestBody Rule rule) {
        List<Question> list = randpaper(rule);
        if (list.size() == 0||list==null)
            return CommonReturnType.create(null, "题目不足");
        return CommonReturnType.create(list);
    }

    @PostMapping("/autoProblem/intelligence")
    public CommonReturnType makepaper(@RequestBody Rule rule) {
        Paper resultPaper =new Paper();
        int runCount =2;
        int count=0;
        if (rule != null) {
            // 初始化种群
            Population population = new Population(5 , rule);
            log.info("初次适应度  " + population.getFitness().getAdaptationDegree());
            while (count < runCount) {
                count++;
                population = GA.evolvePopulation(population, rule);
                System.out.println("第 " + count + " 次进化，适应度为： " + population.getFitness().getAdaptationDegree());
            }
            System.out.println("进化次数： " + count);
            System.out.println(population.getFitness().getAdaptationDegree());
            resultPaper = population.getFitness();
        }
//        System.out.println(resultPaper);
        return  CommonReturnType.create(resultPaper);
    }

    private  List<Paper> randpapers(int num,Rule rule){
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

    private  List<Question> randpaper(Rule rule){
        List<Question> list = new ArrayList<>();
        List<Integer> questionnum = new ArrayList<Integer>();
        String coursename = rule.getCoursename();
        List<Ruleqnum>  ruleqnum =rule.getRuleqnumList();
        for (Ruleqnum r : ruleqnum) {
            // questionnum.set(r.getTypeId(), (int) questionService.count(new QueryWrapper<Question> ().eq("type",1).eq("coursename", coursename)));
            questionnum.set(r.getTypeId(), (int) questionService.count(new QueryWrapper<Question>().eq("quesTypeId", r.getTypeId())
                    .eq("coursename", coursename)
                    .in("pointId", rule.getpointIdList())));
            if (questionnum.get(r.getTypeId()) < r.getNum()) {
                log.info("----------log----------");
                log.info("***题目不足***");
                return null;
            }
            /***
             *   获取 该类型题目
             */
            List<Question> qArray = questionService.list(new QueryWrapper<Question>().eq("quesTypeId", r.getTypeId())
                    .eq("coursename", coursename)
                    .in("pointId", rule.getpointIdList()));
            list.addAll(randsquestion(qArray, rule.getSingleNum(), r.getScore()));

        }
        return list;
    }
    /**
     * 非接口
     *
     * @param singleArray 该类型题库
     * @param chosenum    题目数目
     * @param s           题目分数
     * @return
     */
    private List<Integer> randquestion(List<Question> singleArray, long chosenum, Double s) {
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

    private List<Question> randsquestion(List<Question> singleArray, long chosenum, Double s) {
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

    @PostMapping("/paperProblem/save")
    public CommonReturnType notice(@RequestParam int paperId, @RequestParam String papercontext) {
        //new CommonReturnType();
        boolean b = papersService.update(new UpdateWrapper<Papers>().set("papercontext", papercontext).eq("paperId", paperId));
        if (b) {
            return CommonReturnType.create(null);
        } else {
            return CommonReturnType.create(null, "暂无通知");
        }
    }

    @PostMapping("/paperProblem/save/byListQuestion/{paperId}")
    public CommonReturnType saveProblem(@PathVariable int paperId, @RequestBody List<Question> l) {
        //new CommonReturnType();
        String papercontext = "";
        for (Question q : l) {
            papercontext = papercontext + String.valueOf(q.getId());
            if (q == l.get(l.size() - 1)) {
                break;
            }
            papercontext = papercontext + ",";
        }
        boolean b = papersService.update(new UpdateWrapper<Papers>().set("papercontext", papercontext).eq("paperId", paperId));
        if (b) {
            return CommonReturnType.create(null);
        } else {
            return CommonReturnType.create(null, "暂无通知");
        }
    }


    
}
