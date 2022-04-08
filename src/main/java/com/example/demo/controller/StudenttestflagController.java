package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Studenttestflag;
import com.example.demo.service.StudenttestflagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/studenttestflag")
public class StudenttestflagController {
    @Autowired
    private StudenttestflagService studenttestflagService;
    //保存
    @RequestMapping("/save")
    public CommonReturnType save(@RequestBody Studenttestflag studenttestflag){
        studenttestflagService.save(studenttestflag);
        return CommonReturnType.create(null);
    }
    //删除
    @RequestMapping("/delete")
    public CommonReturnType delete(@RequestBody Studenttestflag studenttestflag){
        studenttestflagService.remove(new QueryWrapper<>(studenttestflag));
        return CommonReturnType.create(null);
    }
    //更新
    @RequestMapping("/update")
    public CommonReturnType update(@RequestBody Studenttestflag studenttestflag){
        studenttestflagService.updateById(studenttestflag);
        return CommonReturnType.create(null);
    }
    //查询
    @RequestMapping("/select")
    public CommonReturnType select(@RequestBody Studenttestflag studenttestflag){
        return CommonReturnType.create(studenttestflagService.getById(studenttestflag.getPhone()));
    }
}




