package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public List<Test> listAll(){
        return testService.list();
    }
    @PostMapping("/one")
    public List<Test> listOne(@RequestBody Test test){
        return testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
    }
    @PostMapping("/teacherone")
    public List<Test> listteacherOne(@RequestBody Test test){
        return testService.list(new QueryWrapper<Test>().eq("teacherphone", test.getTeacherphone()));
    }
}

