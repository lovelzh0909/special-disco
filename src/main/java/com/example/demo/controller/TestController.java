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
    @PostMapping("/save")
    public CommonReturnType saveTest(@RequestBody Test t ){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        if(t.getCreatedate()==null){
            //添加时间
            // Date d= new Date();
        }
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        Boolean data = testService.save(t);
        if(data==false)
        return CommonReturnType.create(null,"添加失败");
        return CommonReturnType.create(null);
    }
    @PostMapping("/changeStatus")
    public  CommonReturnType changeStatus(@RequestBody int id){
        Test t =testService.getById(id);
        t.setPystatus("pystatus");
        t.setTeststatus("teststatus");
        Boolean data = testService.save(t);
        if(data==false)
        return CommonReturnType.create(null,"添加失败");
        return CommonReturnType.create(null);
        // return testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
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

    @PostMapping ("/remove")
    public CommonReturnType removeQuestion(@RequestBody int id){

        boolean data=testService.remove(new QueryWrapper<Test>()
                .eq("testid", id)
        );
        if(data==false){
            return CommonReturnType.create("没有该测试或已经被删除");
        }

        return CommonReturnType.create(null);
    }



}

