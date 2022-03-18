package com.example.demo.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.GA.Question;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Papers;
import com.example.demo.service.PapersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
@RestController
@RequestMapping("/papers")
public class PapersController {
    @Autowired
    PapersService papersService;

    @PostMapping("/save")
    public CommonReturnType saveQuestion(@RequestBody Papers p){
        Boolean b = papersService.save(p);
        if(b==false)
        return CommonReturnType.create("添加试卷失败");
        return CommonReturnType.create(null);
    }

    @PostMapping("/getpaper")
    public CommonReturnType getStudentvideo(@RequestBody Integer paperId) {
        Papers p =papersService.getById(paperId);
        // String lString =p.getPapercontext();
        //获取每个题目Id放入lint
        // List<String> l =stringToList(lString);
        // List<Integer> lint;
        // for(String i:l ){
        //     lint.add(Integer.parseInt(i));
        // }
        // List<Question> picture = papersService.getpapersQuestions(paperId);
        if(p==null){
            return CommonReturnType.create("改试卷不存在或没有题目");
        }
        return CommonReturnType.create(p);
    }

    @PostMapping("/getpaperproblem")
    public CommonReturnType getpaperproblem(@RequestBody Integer paperId) {
        Papers p =papersService.getById(paperId);
        String lString =p.getPapercontext();
        //获取每个题目Id放入lint
        List<String> l =stringToList(lString);
        List<Integer> lint =new ArrayList<Integer>();
        List<Question> lQuestion =new ArrayList<Question>();
        for(String i:l ){
            lint.add(Integer.parseInt(i));
            lQuestion.add(papersService.getQuestions(Integer.parseInt(i)));
        }
        // List<Question> picture = papersService.getpapersQuestions(paperId);
        if(lint.size()==0||lQuestion.size()==0){
            return CommonReturnType.create("改试卷不存在或没有题目");
        }
        return CommonReturnType.create(lQuestion);
    }


    @PostMapping("/getteacherallpaper")
    public CommonReturnType getallpaper(@RequestParam String phone) {
        List<Papers> picture = papersService.list(new QueryWrapper<Papers>()
        .eq("createrPhone", phone));
        if(picture==null){
            return CommonReturnType.create("试卷不存在或没有题目");
        }
        return CommonReturnType.create(picture);
    }

    @PostMapping ("/remove")
    public CommonReturnType removeQuestion(@RequestParam int id){

        boolean data=papersService.remove(new QueryWrapper<Papers>()
                .eq("paperId", id)
        );
        if(data==false){
            return CommonReturnType.create("试卷已不存在");
        }

        return CommonReturnType.create(null);
    }


    @PostMapping ("/removemore")
    public CommonReturnType removemoreQuestion(@RequestParam List<Integer> id){
        // papersService.removeBatchByIds(list)
        boolean data=papersService.removeBatchByIds(id);
        if(data==false){
            return CommonReturnType.create("试卷已不存在");
        }

        return CommonReturnType.create(null);
    }

    private List<String> stringToList(String strs){
        String str[] = strs.split(",");
        return Arrays.asList(str);
   }
    
//    private List<int[]> stringToIntList(String strs){
//        int str[];
//        try{
//     int str[] =Integer.parseInt(strs.split(","));}
//     // String str[] =Integer.parseInt(strs.split(","));
//     return Arrays.asList(str);
}


