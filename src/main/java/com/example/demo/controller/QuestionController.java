package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Question;
import com.example.demo.service.QuestionService;
import com.example.demo.service.QuestionrelscoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@Slf4j
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionrelscoreService questionrelscoreService;
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
        boolean data = questionService.save(q);
        if(!data)
        return CommonReturnType.create(null,"添加失败");
        return CommonReturnType.create(null);
    }
    //多条题目保存
    @PostMapping("/saveall")
    public CommonReturnType saveallQuestion(@RequestBody List<Question> q ){
        log.info("l"+q);
        for(Question qs:q){
            qs.setId(null);
        }
        boolean data = questionService.saveBatch(q);
        if(!data)
        return CommonReturnType.create(null,"添加失败");
        return CommonReturnType.create(null);
    }
    //查询成功
    @PostMapping ("/get")
    public CommonReturnType getAllQuesion(@RequestParam String phone){

        List<Question> data=questionService.list(new QueryWrapper<Question>()
                .eq("userId", phone)
        );

        if(data==null){
            return CommonReturnType.create(null,"没有该题目或已经被删除");
        }

        return CommonReturnType.create(data);
    }

    //查询该题库的题目
    @PostMapping ("/getbycourse/{page}/{size}")
    public CommonReturnType getCourseQuesion(@RequestParam String phone,String coursename,@PathVariable int page,int size){
        log.info("获取题库题目数据");
        log.info("前端发送:"+phone+":"+coursename);
        Page <Question> p = new Page<>(page, size);
        p=questionService.page(p, new QueryWrapper<Question>()
        .eq("userId", phone) .eq("coursename", coursename));
        List<Question> data=questionService.list(new QueryWrapper<Question>()
                .eq("userId", phone) .eq("coursename", coursename)
        );
        p.setSize(data.size());
        log.info("后端发送:"+data);
        if(data.size()==0){
            return CommonReturnType.create(null,"没有该题目或已经被删除");
        }
        return CommonReturnType.create(data);
    }
        //查询该题库的题目
        @PostMapping ("/get/bycourse/{quesTypeId}/{page}/{size}")
        public CommonReturnType getCourseandtypeQuesion(@RequestParam String phone,String coursename,@PathVariable int quesTypeId ,int page,int size){
            log.info("获取题库题目数据");
            log.info("前端发送:"+phone+":"+coursename+":"+quesTypeId);
        Page <Question> p = new Page<>(page, size);
            p=questionService.page(p, new QueryWrapper<Question>()
            .eq("userId", phone) .eq("coursename", coursename).eq("quesTypeId", quesTypeId));
            List<Question> data=questionService.list(new QueryWrapper<Question>()
                    .eq("userId", phone) .eq("coursename", coursename) .eq("quesTypeId", quesTypeId)
            );
            p.setTotal(data.size());
            log.info("后端发送:"+data);
            if(data.size()==0){
                return CommonReturnType.create(null,"没有该题目或已经被删除");
            }
            return CommonReturnType.create(p);
        }
    /**
     * 获取该老师的所有题库课程名
     * @param phone
     * @return
     */
    @PostMapping ("/getCoursename")
    public CommonReturnType getCoursename(@RequestParam String phone){
        log.info("获取题库名称");
        log.info("前端发送:"+phone);
            // List<Question> data=questionService.list(new QueryWrapper<Question>().select("distinct coursename")
            //         .eq("userId", phone) 
            // );
            List<Map<String, Object>> m=
             questionService.listMaps(new QueryWrapper<Question>().select("distinct coursename")
            .eq("userId", phone) );
            //确定这些问题的coursename 集合
            if(m==null){
                return CommonReturnType.create(null,"没有该题目或已经被删除");
            }
        log.info("后端发送:"+m);
            return CommonReturnType.create(m);
    }

    @PostMapping ("/getPointId")
    public CommonReturnType getPointId(@RequestParam String coursename){

        // List<Question> data=questionService.list(new QueryWrapper<Question>().select("distinct coursename")
        //         .eq("userId", phone)
        // );
        List<Map<String, Object>> m=
                questionService.listMaps(new QueryWrapper<Question>().select("distinct pointId")
                        .eq("coursename", coursename) );
        //确定这些问题的coursename 集合
        if(m==null){
            return CommonReturnType.create(null,"没有该题目或已经被删除");
        }
        return CommonReturnType.create(m);
    }


    //删除成功
    @PostMapping ("/remove")
    public CommonReturnType removeQuestion(@RequestParam int id){

        boolean data=questionService.remove(new QueryWrapper<Question>()
                .eq("id", id)
        );
        if(!data){
            return CommonReturnType.create(null,"没有该题目或已经被删除");
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
    public CommonReturnType removeQuestion(@RequestParam String coursename ,String phone){

        boolean data=questionService.remove(new QueryWrapper<Question>()
                .eq("userId", phone).eq("coursename",coursename)
        );
        if(!data){
            return CommonReturnType.create(null,"没有该题目或已经被删除");
        }

        return CommonReturnType.create(null);
    }

    @PostMapping ("/update/bycoursename")
    public CommonReturnType changeCoursename(@RequestParam String coursename ,String phone){

        boolean data=questionService.update(new UpdateWrapper<Question>()
                .eq("userId", phone).set("coursename",coursename)
        );
        if(!data){
            return CommonReturnType.create(null,"没有该题目或已经被删除");
        }

        return CommonReturnType.create(null);
    }
    /**
     * 通过id查询问题
     * @param
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
    /**
     * 创建题库
     * @param coursename
     * @param phone
     * @return
     */
    @PostMapping("/coursename/save")
    public CommonReturnType saveQuestion(@RequestParam String coursename ,String phone ){
        log.info("添加题库");
        log.info("前端发送:"+phone+":"+coursename);
        Question q=new Question();
        List<Question> m=
                questionService.list(new QueryWrapper<Question>().select("distinct coursename")
                        .eq("userId", phone) );
        log.info(String.valueOf(m));
        log.info(coursename);
        for(Question qs:m){
            log.info(qs.getCoursename());
            if(qs.getCoursename().contentEquals(coursename)){
                return  CommonReturnType.create(null,"题库名重复");
            }
        }
        q.setCoursename(coursename);
        q.setUserId(phone);
        log.info("save["+q+"]");
        boolean data = questionService.save(q);
        if(!data)
        return CommonReturnType.create(null,"添加失败");
        log.info("--------------log----------");
        log.info("save["+q+"]");
        log.info("后端发送:success");
        return CommonReturnType.create(null);
    }
}

