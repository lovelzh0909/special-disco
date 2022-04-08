package com.example.demo.controller;


import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Testrelstudent;
import com.example.demo.service.TestrelstudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * @author 作者
 * @since 2022-03-23
 */
@RestController
@RequestMapping("/testrelstudent")
public class TestrelstudentController {


    @Autowired
    TestrelstudentService testrelstudentService;
    /**
     * 通过测试id，获取参与该测试学生
     * @param t 测试id
     * @return 获取参与该测试学生list
     */
    @PostMapping("/selectbytestid")
    public CommonReturnType showTest(@RequestBody String t ){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        List<Testrelstudent> data = testrelstudentService.list(new QueryWrapper<Testrelstudent>()
        .eq("testId", t));
        if(data.size()==0)
        return CommonReturnType.create(null,"没有任何该考试信息");
        return CommonReturnType.create(null);
    }

     /**
     * 通过测试id，获取参与该测试学生
     * @param t 学生phone
     * @return 获取参与该测试id list
     */
    @PostMapping("/selectbyphone")
    public CommonReturnType showTestbyphone(@RequestBody String t ){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        List<Testrelstudent> data = testrelstudentService.list(new QueryWrapper<Testrelstudent>()
        .eq("studentPhone", t));
        if(data.size()==0)
        return CommonReturnType.create(null,"没有任何该考试信息");
        return CommonReturnType.create(null);
    }

    

   
    /**
     * 用于保存一个新添加的学生到某个测试
     * @param t
     * @return 
     */
    @PostMapping("/save")
    public CommonReturnType saveTest(@RequestBody Testrelstudent t ){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        Boolean data = testrelstudentService.save(t);
        if(data==false)
        return CommonReturnType.create(null,"添加失败");
        return CommonReturnType.create(null);
    }

    /**
     * 用于删除某个测试所有学生，
     * @param t（测试id）
     * @return 
     */
    @PostMapping("/removeTestbytestId")
    public CommonReturnType removeTestbyid(@RequestBody String t ){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        Boolean data = testrelstudentService.remove(new QueryWrapper<Testrelstudent>()
        .eq("testId", t));
        if(data==false)
        return CommonReturnType.create(null,"添加失败");
        return CommonReturnType.create(null);
    }

        /**
     * 用于删除某个测试某个学生，
     * @param t（测试id）
     * @return 
     */
    @PostMapping("/removeStufromtest")
    public CommonReturnType removeTest(@RequestBody Testrelstudent t ){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        Boolean data = testrelstudentService.remove(new QueryWrapper<Testrelstudent>()
        .eq("testId", t.getTestId())
        .eq("studentPhone", t.getStudentPhone()));
        if(data==false)
        return CommonReturnType.create(null,"添加失败");
        return CommonReturnType.create(null);
    }

    /**
     * 
     * @param t
     * @return
     */
    @PostMapping("/saveall")
    public CommonReturnType saveallTest(@RequestBody List<Testrelstudent> t ) {
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        boolean data = testrelstudentService.saveBatch(t);
        if (!data)
            return CommonReturnType.create(null, "添加失败");
        return CommonReturnType.create(null);
    }
}

