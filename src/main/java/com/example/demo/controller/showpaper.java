package com.example.demo.controller;




import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.*;
import com.example.demo.entity.vo.StudentVideoVO;
import com.example.demo.service.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
@Slf4j
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
    @Autowired
    ScoreService scoreService;

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
        Map<String,Object> m =new HashMap<>();
        for(String s:qs){
            List<Testrelstudent> testrelstudent =testrelstudentService.list(new QueryWrapper<Testrelstudent>()
                    .eq("testId",testId).eq("status",1));
            m.put("studentphone",testrelstudent.get(0).getStudentPhone());
            if(testrelstudent.size()==0){
                return CommonReturnType.create("没有待批阅学生参加");
            }
            Question tempq =questionService.getById(Integer.valueOf(s)) ;
            tempq.setStudentAnswer(paperJustifyService.getOne(new QueryWrapper<PaperJustify>()
                    .eq("testId",testId).eq("studentphone",testrelstudent.get(0).getStudentPhone()).eq("questionid", tempq.getId())).getExmaineAnswer());
            q.add(tempq);
        }
        log.info("-----------------log---------------");
        log.info(q.toString());
        if(q.size()==0){
            return CommonReturnType.create("没有找到该试卷");
        }
        return CommonReturnType.create(q,m);
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

    @PostMapping("/marking/finish/{studentphone}/{testId}")
    public CommonReturnType savescore(@RequestBody List<Question> questionList,@PathVariable String studentphone ,@PathVariable Integer testId) {
        Double sum=0.0;
        for(Question q:questionList) {
            sum += paperJustifyService.getById(q.getId()).setScore(q.getGetScore()).getScore();
        }
//        Testrelstudent stu = testrelstudentService.getOne(new QueryWrapper<Testrelstudent>().eq("studentphone", studentphone).eq("testId", testId));
//        stu.setStatus(3);
        testrelstudentService.update(new UpdateWrapper<Testrelstudent>().set("status",3).eq("studentPhone", studentphone).eq("testId", testId));

        Test t= testService.getById(testId);
        Papers p =papersService.getById(t.getPaperId());
        if(p==null){
            return CommonReturnType.create("没有找到该试卷");
        }
        Score score = new Score();
        score.setScore(sum);
        score.setSubject(t.getCoursename());
        score.setStudentId(Integer.valueOf(studentphone));
        scoreService.save(score);

        return CommonReturnType.create(score);
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

