package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.School;
import com.example.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-12
 */
@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolService schoolService;


    @PostMapping("/save")
    public CommonReturnType save(@RequestBody School school){
        schoolService.save(school);
        return CommonReturnType.create(null);
    }

    @PostMapping("/update")
    public CommonReturnType update(@RequestBody School school){
        QueryWrapper<School> wrapper = new QueryWrapper<>();
        wrapper.eq("school",school.getSchool());
        schoolService.update(school,wrapper);
        return CommonReturnType.create(null);
    }

    @PostMapping("/delete")
    public CommonReturnType delete(@RequestBody School school){
        QueryWrapper<School> wrapper = new QueryWrapper<>();
        wrapper.eq("school",school.getSchool());
        schoolService.remove(wrapper);
        return CommonReturnType.create(null);
    }

    @PostMapping("/get")
    public CommonReturnType get(@RequestBody School school){
        QueryWrapper<School> wrapper = new QueryWrapper<>();
        wrapper.eq("school",school.getSchool());
        School school1 = schoolService.getOne(wrapper);
        return CommonReturnType.create(school1);
    }

    @PostMapping("/list")
    public CommonReturnType list(){
        return CommonReturnType.create(schoolService.list());
    }
}



