package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.PaperJustify;
import com.example.demo.service.PaperJustifyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * <p>
 * 用于存储学生问题答案	 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
@RestController
@RequestMapping("/paperJustify")
public class PaperJustifyController {
    @Autowired
    PaperJustifyService questionService;

    //fillQuestion
    //添加成功
    @PostMapping("/save")
    public CommonReturnType saveQuestion(@RequestBody PaperJustify q ){
        boolean data= questionService.save(q);
        if(data==false){
            return CommonReturnType.create("没有gaixuesheng");
        }
        return CommonReturnType.create(null);
    }
    //duojiedhou
    @PostMapping("/saveall")
    public CommonReturnType saveallQuestion(@RequestBody List<PaperJustify> p ){
        boolean data= questionService.saveBatch(p);
        if(data==false){
            return CommonReturnType.create("没有gaixuesheng");
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

