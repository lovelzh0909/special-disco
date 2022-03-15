package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Test;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;
    @PostMapping("/all")
    public CommonReturnType listAll(){
        List<Test> l =testService.list();
        if(l==null){
            return CommonReturnType.create("没有任何考试信息");
        }
        return CommonReturnType.create(l);
    }
    @PostMapping("/one")
    public  CommonReturnType listOne(@RequestBody Test test){
        List<Test> l =testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
        if(l==null){
            return CommonReturnType.create("没有该学生考试信息");
        }
        return CommonReturnType.create(l);
        // return testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
    }
    @PostMapping("/teacherone")
    public CommonReturnType listteacherOne(@RequestBody Test test){
        List<Test> l =testService.list(new QueryWrapper<Test>().eq("teacherphone", test.getTeacherphone()));
        if(l==null){
            return CommonReturnType.create("没有该老师相关考试信息");
        }
        return CommonReturnType.create(l);
        // return testService.list(new QueryWrapper<Test>().eq("teacherphone", test.getTeacherphone()));
    }
}

