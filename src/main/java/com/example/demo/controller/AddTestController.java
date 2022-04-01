package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.*;
import com.example.demo.service.PapersService;
import com.example.demo.service.QuestionService;

import com.example.demo.service.TestService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Test")
public class AddTestController {
    //    RuleService ruleService;
    @Autowired
    TestService testService;
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public CommonReturnType saveTest(@RequestBody Test t ){

        if(t.getCreatedate()==null){

            t.setCreatedate(String.valueOf(LocalDateTime.now()));
        }
        List<User> userList =new ArrayList<>();
        if(t.getTesttime()!=null){
            String sub=t.getTesttime().substring(0,9);
            List<User> l=userService.list(new QueryWrapper<User>().eq("role","teacher"));

            for(User u:l){
                int flag=1;
                List<Test> testList =testService.list(new QueryWrapper<Test>().eq("teacherPhone",u.getPhone()));
                for(Test te:testList){
                    if(te.getTesttime().substring(0,9)==sub){
                        flag=0; break;
                    }
                }
                if(flag==1){
                    userList.add(u);
                }
            }
        }
        if(userList.size()==0){
            return  CommonReturnType.create("没有空余监考老师");
        }
        Random r =new Random();
        int i =r.nextInt(userList.size());
        //设置监考老师id及姓名
        t.setInvigilatorId(userList.get(i).getPhone());
        t.setInvigilator(userList.get(i).getName());
        Boolean data = testService.save(t);
        if(data==false)
            return CommonReturnType.create(null,"添加失败");
        return CommonReturnType.create(t.getTestId());
    }




}
