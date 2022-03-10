package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Teachertest;
import com.example.demo.entity.Test;
import com.example.demo.service.TeachertestService;

import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2022-03-06
 */
@RestController
@RequestMapping("/teachertest")
public class TeachertestController {
    @Autowired
    TeachertestService teachertestService;
    @RequestMapping("/all")
    public List<Teachertest> listAll(){
        return teachertestService.list();
    }
    @RequestMapping("/one")
    public List<Teachertest> listOne(@RequestBody Teachertest teachertest){
        return teachertestService.list(new QueryWrapper<Teachertest>().eq("phone", teachertest.getPhone()));
    }
}

