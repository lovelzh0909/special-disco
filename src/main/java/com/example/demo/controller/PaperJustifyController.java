package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.PaperJustify;
import com.example.demo.entity.Question;
import com.example.demo.service.PaperJustifyService;

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
    PaperJustifyService questionService;

    //fillQuestion
    //添加成功
    @PostMapping("/save")
    public CommonReturnType saveQuestion(@RequestBody PaperJustify q ){
        log.info("----------------log---------------");
        log.info(q.toString());
        boolean data= questionService.save(q);
        if(data==false){
            return CommonReturnType.create("没有gaixuesheng");
        }
        return CommonReturnType.create(null);
    }
    //duojiedhou
    @PostMapping("/saveall/{phone}/{testId}")
    public CommonReturnType saveallQuestion(@RequestBody List<Question> ps , @PathVariable String phone , @PathVariable Integer testId ){
        PaperJustify p = new PaperJustify();
        for(Question q :ps){
            p.setQuestionId(q.getId());
            p.setExmaineAnswer(q.getAnswer());
            p.setTestId(testId);
            p.setTotalscore(q.getScore());
            p.setStudentphone(phone);
            boolean data= questionService.save(p);
            if(data==false){
                return CommonReturnType.create("没有gaixuesheng");
            }
        }
        return CommonReturnType.create(p);
    }
    //查询成功
    @PostMapping ("/get")
    public CommonReturnType getAllQuesion(@RequestParam String phone){

        List<PaperJustify> data=questionService.list(new QueryWrapper<PaperJustify>()
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

        boolean data=questionService.remove(new QueryWrapper<PaperJustify>()
                .eq("studentphone", id)
        );
        if(data==false){
            return CommonReturnType.create("没有该题目或已经被删除");
        }

        return CommonReturnType.create(null);
    }

}

