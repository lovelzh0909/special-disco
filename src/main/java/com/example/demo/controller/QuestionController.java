package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Question;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.crypto.Data;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    //fillQuestion
    //添加成功
    @PostMapping("/save")
    public CommonReturnType saveQuestion(@RequestBody Question q ){
        if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null)
        return CommonReturnType.create(null,"信息不全");
        if(q.getCreateTime()==null){
            q.setCreateTime(LocalDateTime.now());
            //添加时间
            // Date d= new Date();
        }
        if(q.getId()==null){
            q.setId(questionService.lastQuestionId()+1);
        }
        Boolean data = questionService.save(q);
        if(data==false)
        return CommonReturnType.create(null,"添加失败");
        return CommonReturnType.create(null);
    }
    //多条题目保存
    @PostMapping("/saveall")
    public CommonReturnType saveallQuestion(@RequestBody List<Question> q ){
        Boolean data = questionService.saveBatch(q);
        if(data==false)
        return CommonReturnType.create("添加失败");
        return CommonReturnType.create(null);
    }
    //查询成功
    @PostMapping ("/get")
    public CommonReturnType getAllQuesion(@RequestParam String phone){

        List<Question> data=questionService.list(new QueryWrapper<Question>()
                .eq("userId", phone)
        );

        if(data==null){
            return CommonReturnType.create("没有该题目或已经被删除");
        }

        return CommonReturnType.create(data);
    }

    //查询该题库的题目
    @PostMapping ("/getbycourse")
    public CommonReturnType getCourseQuesion(@RequestParam String phone,String coursename){

        List<Question> data=questionService.list(new QueryWrapper<Question>()
                .eq("userId", phone) .eq("coursename", coursename)
        );

        if(data==null){
            return CommonReturnType.create("没有该题目或已经被删除");
        }

        return CommonReturnType.create(data);
    }
    /**
     * 获取该老师的所有题库课程名
     * @param phone
     * @return
     */
    @PostMapping ("/getCoursename")
    public CommonReturnType getCoursename(@RequestParam String phone){
    
            // List<Question> data=questionService.list(new QueryWrapper<Question>().select("distinct coursename")
            //         .eq("userId", phone) 
            // );
            List<Map<String, Object>> m=
             questionService.listMaps(new QueryWrapper<Question>().select("distinct coursename")
            .eq("userId", phone) );
            //确定这些问题的coursename 集合
            if(m==null){
                return CommonReturnType.create("没有该题目或已经被删除");
            }
    
            return CommonReturnType.create(m);
    }
    //删除成功
    @PostMapping ("/remove")
    public CommonReturnType removeQuestion(@RequestParam int id){

        boolean data=questionService.remove(new QueryWrapper<Question>()
                .eq("id", id)
        );
        if(data==false){
            return CommonReturnType.create("没有该题目或已经被删除");
        }

        return CommonReturnType.create(null);
    }

    /***
     * 删除该用户该课程数据库
     * @param coursename
     * @param phone
     * @return
     */
    @PostMapping ("/remove/bycoursename")
    public CommonReturnType removeQuestion(@RequestParam String coursename ,int phone){

        boolean data=questionService.remove(new QueryWrapper<Question>()
                .eq("userId", phone).eq("coursename",coursename)
        );
        if(data==false){
            return CommonReturnType.create("没有该题目或已经被删除");
        }

        return CommonReturnType.create(null);
    }

    @PostMapping ("/update/bycoursename")
    public CommonReturnType changeCoursename(@RequestParam String coursename ,int phone){

        boolean data=questionService.update(new UpdateWrapper<Question>()
                .eq("userId", phone).set("coursename",coursename)
        );
        if(data==false){
            return CommonReturnType.create("没有该题目或已经被删除");
        }

        return CommonReturnType.create(null);
    }
    /**
     * 通过id查询问题
     * @param List<Integer> id
     * @return
     */

    @PostMapping ("/getQuestionbyid")
    public CommonReturnType getQuestionbyid(@RequestBody List<Integer> id){
    
            List<Question> data=questionService.list(new QueryWrapper<Question>()
                    .in("id", id) 
            );
            //确定这些问题的coursename 集合
            if(data==null){
                return CommonReturnType.create("没有该题目或已经被删除");
            }
    
            return CommonReturnType.create(data);
    }
}

