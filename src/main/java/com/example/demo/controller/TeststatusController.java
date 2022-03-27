package com.example.demo.controller;


import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Teststatus;
import com.example.demo.service.TeststatusService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-25
 */
@RestController
@RequestMapping("/demo/teststatus")
public class TeststatusController {
    TeststatusService teststatusService;
    @PostMapping("/selectAll")
    public CommonReturnType showTest( ){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        List<Teststatus> data = teststatusService.list();
        // if(data.size()==0)
        // return CommonReturnType.create(null,"没有任何该考试信息");
        return CommonReturnType.create(data);
    }

    @PostMapping("/selectbyname")
    public CommonReturnType showTestbyname( String name){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        Teststatus data = teststatusService.getOne(new QueryWrapper<Teststatus>() .eq("testStatus", name));
        if(data==null)
        return CommonReturnType.create(null,"没有该状态");
        return CommonReturnType.create(data);
    }

    @PostMapping("/selectbyId")
    public CommonReturnType showTestbyID(@RequestBody int id ){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        Teststatus data = teststatusService.getById(id);
        if(data==null)
        return CommonReturnType.create(null,"没有该ID");
        return CommonReturnType.create(data);
    }
    

}

