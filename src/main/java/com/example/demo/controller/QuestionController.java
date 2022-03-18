package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.GA.Question;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    //fillQuestion
    //添加成功
    @PostMapping("/save")
    public CommonReturnType saveQuestion(@RequestBody Question q ){
        Boolean data = questionService.save(q);
        if(data==false)
        return CommonReturnType.create("添加失败");
        return CommonReturnType.create(null);
    }
    //多条题目保存
    @PostMapping("/saveall")
    public CommonReturnType saveallQuestion(@RequestBody List<Question> q ){
        Boolean data = questionService.saveBatch(q);
        if(data==false)
        return CommonReturnType.create("添加失败");
        return CommonReturnType.create(null);
    }
    //查询成功
    @PostMapping ("/get")
    public CommonReturnType getAllQuesion(@RequestParam String phone){

        List<Question> data=questionService.list(new QueryWrapper<Question>()
                .eq("userId", phone)
        );

        if(data==null){
            return CommonReturnType.create("没有该题目或已经被删除");
        }

        return CommonReturnType.create(data);
    }

    //查询该题库的题目
    @PostMapping ("/getbycourse")
    public CommonReturnType getCourseQuesion(@RequestParam String phone,String coursename){

        List<Question> data=questionService.list(new QueryWrapper<Question>()
                .eq("userId", phone) .eq("coursename", coursename)
        );

        if(data==null){
            return CommonReturnType.create("没有该题目或已经被删除");
        }

        return CommonReturnType.create(data);
    }

    //删除成功
    @PostMapping ("/remove")
    public CommonReturnType removeQuestion(@RequestParam int id){

        boolean data=questionService.remove(new QueryWrapper<Question>()
                .eq("userId", id)
        );
        if(data==false){
            return CommonReturnType.create("没有该题目或已经被删除");
        }

        return CommonReturnType.create(null);
    }
}

