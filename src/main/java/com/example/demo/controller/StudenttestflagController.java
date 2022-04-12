package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Studenttestflag;
import com.example.demo.service.StudenttestflagService;
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
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/studenttestflag")
public class StudenttestflagController {
    @Autowired
    private StudenttestflagService studenttestflagService;
    //保存
    @PostMapping("/save")
    public CommonReturnType save(@RequestBody Studenttestflag studenttestflag){
        studenttestflag.setFlag(1);
        studenttestflagService.save(studenttestflag);
        return CommonReturnType.create(null);
    }
    //删除
    @PostMapping("/delete")
    public CommonReturnType delete(@RequestBody Studenttestflag studenttestflag){
        studenttestflagService.removeById(studenttestflag.getId());
        return CommonReturnType.create(null);
    }
    //更新
    @PostMapping("/update")
    public CommonReturnType update(@RequestBody Studenttestflag studenttestflag){
        studenttestflagService.updateById(studenttestflag);
        return CommonReturnType.create(null);
    }
    //查询
    @PostMapping("/find")
    public CommonReturnType find(@RequestBody Studenttestflag studenttestflag){
        QueryWrapper<Studenttestflag> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("studentId",studenttestflag.getStudentId());
        queryWrapper.eq("testid",studenttestflag.getTestId());
        Studenttestflag studenttestflag1=studenttestflagService.getOne(queryWrapper);
        return CommonReturnType.create(studenttestflag1);
    }
}




