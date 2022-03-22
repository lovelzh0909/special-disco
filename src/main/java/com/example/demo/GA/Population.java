package com.example.demo.GA;

import com.example.demo.entity.Question;
import com.example.demo.entity.Rule;

// import com.lxl.nebula.question.QuestionService;
// import sun.rmi.runtime.Log;

import com.example.demo.service.QuestionService;

import java.util.Random;

/**
 * 种群，即多套试卷
 *
 * @author: xlli
 * @CreateDate: 2016-4-29 下午01:29:02
 * @version: 1.0
 */
public class Population {
    // private Log log = LogFactory.getLog(Population.class);
    /** 
     * 试卷集合
     */
    private Paper[] papers;

    QuestionService questionService;
    /**
     * 初始种群
     *
     * @param populationSize 种群规模
     * @param initFlag       初始化标志 true-初始化
     * @param rule           规则bean
     */
    public Population(int populationSize, boolean initFlag, Rule rule) {
        papers = new Paper[populationSize];
        if (initFlag) {
            Paper paper;
            Random random = new Random();
            for (int i = 0; i < populationSize; i++) {
                paper = new Paper();
                paper.setId(i + 1);
                while (paper.getTotalScore() != rule.getTotalMark()) {
                    paper.getQuestionList().clear();
                    String idString = rule.getPointIds().toString();
                    // 单选题
                    if (rule.getSingleNum() > 0) {
                        generateQuestion("xuanze", random, rule.getSingleNum(), rule.getSingleScore(), idString,
                                "单选题数量不够，组卷失败", paper);
                    }
                    // 填空题
                    if (rule.getCompleteNum() > 0) {
                        generateQuestion("tiankong", random, rule.getCompleteNum(), rule.getCompleteScore(), idString,
                                "填空题数量不够，组卷失败", paper);
                    }
                    // 主观题
                    if (rule.getSubjectiveNum() > 0) {
                        generateQuestion("subect", random, rule.getSubjectiveNum(), rule.getSubjectiveScore(), idString,
                                "主观题数量不够，组卷失败", paper);
                    }
                }
                // 计算试卷知识点覆盖率
                paper.setKpCoverage(rule);
                // 计算试卷适应度
                paper.setAdaptationDegree(rule, Global.KP_WEIGHT, Global.DIFFCULTY_WEIGHt);
                papers[i] = paper;
            }
        }
    }

    private void generateQuestion(String type, Random random, int qustionNum, double score, String idString,
                                  String errorMsg, Paper paper) {
        Question[] singleArray = questionService.getQuestionArray(type, idString
                .substring(1, idString.indexOf("]")));
        if (singleArray.length < qustionNum) {
            return;
        }
        Question tmpQuestion;
        for (int j = 0; j < qustionNum; j++) {
            int index = random.nextInt(singleArray.length - j);
            // 初始化分数
            singleArray[index].setScore(score);
            paper.addQuestion(singleArray[index]);
            // 保证不会重复添加试题
            tmpQuestion = singleArray[singleArray.length - j - 1];
            singleArray[singleArray.length - j - 1] = singleArray[index];
            singleArray[index] = tmpQuestion;
        }
    }

    /**
     * 获取种群中最优秀个体
     *
     * @return
     */
    public Paper getFitness() {
        Paper paper = papers[0];
        for (int i = 1; i < papers.length; i++) {
            if (paper.getAdaptationDegree() < papers[i].getAdaptationDegree()) {
                paper = papers[i];
            }
        }
        return paper;
    }

    public Population(int populationSize) {
        papers = new Paper[populationSize];
    }

    /**
     * 获取种群中某个个体
     *
     * @param index
     * @return
     */
    public Paper getPaper(int index) {
        return papers[index];
    }

    /**
     * 设置种群中某个个体
     *
     * @param index
     * @param paper
     */
    public void setPaper(int index, Paper paper) {
        papers[index] = paper;
    }

    /**
     * 返回种群规模
     *
     * @return
     */
    public int getLength() {
        return papers.length;
    }

}
