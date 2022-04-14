package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.GA.Paper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.*;
import com.example.demo.service.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * <p>
 * 用于存储学生问题答案	 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
@Slf4j
@RestController
@RequestMapping("/paperJustify")
public class PaperJustifyController {
    @Autowired
    PaperJustifyService paperJustifyService;

    @Autowired
    QuestionService questionService;

    @Autowired
    TestrelstudentService testrelstudentService;

    @Autowired
    QuestionrelscoreService questionrelscoreService;

    @Autowired
    TestService testService;

    //fillQuestion
    //添加成功
    @PostMapping("/save")
    public CommonReturnType saveQuestion(@RequestBody PaperJustify q ){
        log.info("----------------log---------------");
        log.info(q.toString());
        boolean data= paperJustifyService.save(q);
        if(!data){
            return CommonReturnType.create(null, "没有gaixuesheng");
        }
        return CommonReturnType.create(null);
    }
    //duojiedhou

    /**
     * 学生交卷
     * @param ps
     * @param phone
     * @param testId
     * @return
     */
    @PostMapping("/saveall/{phone}/{testId}")
    public CommonReturnType saveallQuestion(@RequestBody List<Question> ps , @PathVariable String phone , @PathVariable Integer testId ){
        log.info("学生交卷");
        log.info("前端发送:"+phone+":"+testId+":"+ps);
        List<PaperJustify> paperJustify =paperJustifyService.list(new QueryWrapper<PaperJustify>().eq("studentPhone", phone).eq("testId", testId));
        if(paperJustify!=null&&paperJustify.size()!=0){
            testrelstudentService.update(new UpdateWrapper<Testrelstudent>().set("status",3).eq("testId",testId).eq("studentPhone", phone));
            return CommonReturnType.create(null,"你已经交卷");
        }
        Test test =testService.getOne(new QueryWrapper<Test>().eq("testId",testId));
        if(test==null){
            return CommonReturnType.create(null,"该测试不存在");
        }
        testrelstudentService.update(new UpdateWrapper<Testrelstudent>().set("status",3).eq("testId",testId).eq("studentPhone", phone));
        PaperJustify p = new PaperJustify();
        for(Question q :ps){
            Questionrelscore questionrelscore = questionrelscoreService.getOne(new QueryWrapper<Questionrelscore>().eq("questionId",q.getId()).eq("paperId",test.getPaperId() ));
            if(questionrelscore==null){
                return CommonReturnType.create(null,q.getId()+":没有存储该题目与试卷关系");
            }
            p.setId(null);
            p.setQuestionId(q.getId());
            // p.setExmaineAnswer(q.getAnswer());
            p.setCorrectAnswer(questionService.getById(q.getId()).getAnswer());
//            p.setCorrectAnswer(String.join(",",p.getCorrectAnswer()));
            p.setCorrectAnswer(p.getCorrectAnswer().substring(1,p.getCorrectAnswer().indexOf("]")));
            if(q.getQuesTypeId()==1||q.getQuesTypeId()==2||q.getQuesTypeId()==5){
                q.setAnswer(q.getAnswer().substring(1,q.getAnswer().indexOf("]")));
            }
            p.setExmaineAnswer(q.getAnswer());
            log.info(p.getCorrectAnswer());
            log.info(p.getExmaineAnswer());
//            p.setCorrectAnswer(q.getAnswer());
            p.setScore(0.0);
            if(q.getQuesTypeId()==1){
                if(Objects.equals(p.getCorrectAnswer(), p.getExmaineAnswer())){
                    p.setScore(questionrelscore.getScore());
                }
            }
            if(q.getQuesTypeId()==2){
                if(Objects.equals(p.getCorrectAnswer(), p.getExmaineAnswer())){
                    p.setScore(questionrelscore.getScore());
                }
            }
//            if(q.getQuesTypeId()==3){
//                if(Objects.equals(p.getCorrectAnswer(), p.getExmaineAnswer())){
//                    p.setScore(questionrelscore.getScore());
//                }
//            }
//            if(q.getQuesTypeId()==4){
//                if(Objects.equals(p.getCorrectAnswer(), p.getExmaineAnswer())){
//                    p.setScore(questionrelscore.getScore());
//                }
//            }
            if(q.getQuesTypeId()==5){
                if(Objects.equals(p.getCorrectAnswer(), p.getExmaineAnswer())){
                    p.setScore(questionrelscore.getScore());
                }
            }
            p.setTestId(testId);
            p.setTotalscore(questionrelscore.getScore());
            p.setStudentphone(phone);
            boolean data= paperJustifyService.save(p);
            log.info("后端发送:"+data);
            if(!data){
                return CommonReturnType.create(null, "没有该学生");
            }
        }
        return CommonReturnType.create(p);
    }
    //查询成功
    @PostMapping ("/get")
    public CommonReturnType getAllQuesion(@RequestParam String phone){
        List<PaperJustify> data=paperJustifyService.list(new QueryWrapper<PaperJustify>()
                .eq("studentphone", phone)
        );
        if(data==null){
            return CommonReturnType.create(null, "没有该学生");
        }
        return CommonReturnType.create(data);
    }

    //删除成功
    @PostMapping ("/remove")
    public CommonReturnType removeQuestion(@RequestParam String id){
        boolean data=paperJustifyService.remove(new QueryWrapper<PaperJustify>()
                .eq("studentphone", id)
        );
        if(!data){
            return CommonReturnType.create(null, "没有该题目或已经被删除");
        }
        return CommonReturnType.create(null);
    }

    @PostMapping ("/wrongQuestion")
    public CommonReturnType getWrongQuestion(@RequestParam String phone) {
        log.info("获取错题集");
        log.info("前端发送:"+phone);
        List<PaperJustify> list = paperJustifyService.list(new QueryWrapper<PaperJustify>().apply("justify<score").eq("studentphone",phone));
        List<Question> questionList=new ArrayList<>();
        for (PaperJustify paperJustify : list) {
            Question q = questionService.getOne(new QueryWrapper<Question>().eq("id", paperJustify.getQuestionId()));
            q.setStudentAnswer(paperJustify.getExmaineAnswer());
            questionList.add(q);
        }
        log.info("后端发送:"+questionList);
        return CommonReturnType.create(questionList);
    }

}

