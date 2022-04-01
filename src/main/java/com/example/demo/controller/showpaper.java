package com.example.demo.controller;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.*;
import com.example.demo.entity.vo.StudentVideoVO;
import com.example.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/showpaper")

public class showpaper {
    @Autowired
    PapersService papersService;
    @Autowired
    QuestionService questionService;
    @Autowired
    TestService testService;
    @Autowired
    PaperJustifyService paperJustifyService;
    @Autowired
    TestrelstudentService testrelstudentService;

    /**
     * 
     * @param StudentId
     * @return
     */
    @PostMapping("/getQuestion/bytestId")
    public CommonReturnType getStudentvideo(@RequestParam Integer testId) {
        Test t= testService.getById(testId);
        Papers p =papersService.getById(t.getPaperId());
        List<String> qs=   stringToList(p.getPapercontext());
        List<Question> q=new ArrayList<>();
        for(String s:qs){
            q.add(questionService.getById(Integer.valueOf(s)).setAnswer(null));
        }

        if(q.size()==0){
            return CommonReturnType.create("没有找到该试卷");
        }
        return CommonReturnType.create(q);
    }

    @PostMapping("/getQuestion/bytestId/grant")
    public CommonReturnType getpaperforteachergrant(@RequestParam Integer testId) {
        Test t= testService.getById(testId);
        Papers p =papersService.getById(t.getPaperId());
        List<String> qs=   stringToList(p.getPapercontext());
        List<Question> q=new ArrayList<>();
        for(String s:qs){
            Testrelstudent testrelstudent =testrelstudentService.getOne(new QueryWrapper<Testrelstudent>()
                    .eq("testId",testId).eq("status",1));
            Question tempq =questionService.getById(Integer.valueOf(s)) ;
            tempq.setStudentAnswer(paperJustifyService.getOne(new QueryWrapper<PaperJustify>()
                    .eq("testId",testId).eq("studentphone",testrelstudent.getStudentPhone())).getExmaineAnswer());
            q.add(tempq);
        }
        if(q.size()==0){
            return CommonReturnType.create("没有找到该试卷");
        }
        return CommonReturnType.create(q);
    }

    @PostMapping("/getQuestion/bypaperId")
    public CommonReturnType getQuestion(@RequestParam Integer paperId) {
        Papers p =papersService.getById(paperId);
        List<String> qs=   stringToList(p.getPapercontext());
        List<Question> q=new ArrayList<>();
        for(String s:qs){
            q.add(questionService.getById(Integer.valueOf(s)));
        }

        if(q.size()==0){
            return CommonReturnType.create("没有找到该试卷");
        }
        return CommonReturnType.create(q);
    }



   @PostMapping("/getpaper/bytestId")
   public CommonReturnType getStudentpaper(@RequestParam Integer testId) {
       Test t= testService.getById(testId);
       Papers p =papersService.getById(t.getPaperId());
       if(p==null){
           return CommonReturnType.create("没有找到该试卷");
       }
       return CommonReturnType.create(p);
   }

   @PostMapping("/getpaper/bypaperId")
   public CommonReturnType getpaper(@RequestParam Integer paperId) {
       Papers p =papersService.getById(paperId);
       if(p==null){
           return CommonReturnType.create("没有找到该试卷");
       }
       return CommonReturnType.create(p);
   }

   private List<String> stringToList(String strs){
       String str[] = strs.split(",");
       return Arrays.asList(str);
  }
}

