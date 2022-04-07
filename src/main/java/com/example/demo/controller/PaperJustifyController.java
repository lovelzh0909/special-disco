package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.PaperJustify;
import com.example.demo.entity.Question;
import com.example.demo.entity.Test;
import com.example.demo.entity.Testrelstudent;
import com.example.demo.service.PaperJustifyService;
import com.example.demo.service.QuestionService;

import com.example.demo.service.TestService;
import com.example.demo.service.TestrelstudentService;
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

    //fillQuestion
    //添加成功
    @PostMapping("/save")
    public CommonReturnType saveQuestion(@RequestBody PaperJustify q ){
        log.info("----------------log---------------");
        log.info(q.toString());
        boolean data= paperJustifyService.save(q);
        if(!data){
            return CommonReturnType.create("没有gaixuesheng");
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
        if(paperJustifyService.getOne(new QueryWrapper<PaperJustify>().eq("studentPhone", phone).eq("testId", testId))!=null){
            return CommonReturnType.create("你已经交卷");
        }
        testrelstudentService.update(new UpdateWrapper<Testrelstudent>().set("status",3).eq("testId",testId).eq("studentPhone", phone));
        PaperJustify p = new PaperJustify();
        for(Question q :ps){
            p.setId(null);
            p.setQuestionId(q.getId());
            // p.setExmaineAnswer(q.getAnswer());
            p.setCorrectAnswer(questionService.getById(q.getId()).getAnswer());
            p.setCorrectAnswer(q.getAnswer());
            p.setExmaineAnswer(q.getAnswer());
            if(q.getQuesTypeId()==1){
                if(Objects.equals(p.getCorrectAnswer(), p.getExmaineAnswer())){
                    p.setScore(q.getScore());
                }
            }
            if(q.getQuesTypeId()==2){
                if(Objects.equals(p.getCorrectAnswer(), p.getExmaineAnswer())){
                    p.setScore(q.getScore());
                }
            }
            if(q.getQuesTypeId()==3){
                if(Objects.equals(p.getCorrectAnswer(), p.getExmaineAnswer())){
                    p.setScore(q.getScore());
                }
            }
            if(q.getQuesTypeId()==4){
                if(Objects.equals(p.getCorrectAnswer(), p.getExmaineAnswer())){
                    p.setScore(q.getScore());
                }
            }
            p.setTestId(testId);
            p.setTotalscore(q.getScore());
            p.setStudentphone(phone);
            boolean data= paperJustifyService.save(p);
            if(!data){
                return CommonReturnType.create("没有gaixuesheng");
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
            return CommonReturnType.create("没有gaixuesheng");
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
            return CommonReturnType.create("没有该题目或已经被删除");
        }

        return CommonReturnType.create(null);
    }

    @PostMapping ("/wrongQuestion")
    public CommonReturnType getWrongQuestion(@RequestParam String phone) {

        List<PaperJustify> list = paperJustifyService.list(new QueryWrapper<PaperJustify>().apply("justify<score").eq("studentphone",phone));
        List<Question> questionList=new ArrayList<>();
        for (PaperJustify paperJustify : list) {
            Question q = questionService.getOne(new QueryWrapper<Question>().eq("id", paperJustify.getQuestionId()));
            q.setStudentAnswer(paperJustify.getExmaineAnswer());
            questionList.add(q);
        }
        return CommonReturnType.create(questionList);
    }

}

