package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.ClassScore;
import com.example.demo.entity.Score;
import com.example.demo.entity.ClassScore;
import com.example.demo.service.impl.ScoreServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScoreController {
    @Autowired
    ScoreServiceImpl scoreService;

    @GetMapping("/scores")
    public CommonReturnType findAll() {
        List<Score> res = scoreService.findAll();
        return CommonReturnType.create(res,"查询所有学生成绩");
    }

    @GetMapping("/scores/byteachphone")
    public CommonReturnType findAllbyphone(@RequestParam String teacherphone) {
        List<Score> scoreLists =new ArrayList<>();
        List<Score> scoreList =scoreService.list(new QueryWrapper<Score>().select("DISTINCT examCode").eq("teacherPhone",teacherphone));
        for(Score s:scoreList){
            List<Score> scores =scoreService.list(new QueryWrapper<Score>().eq("teacherPhone",teacherphone).eq("examCode",s.getExamCode()));
            Score ss= new Score() ;
            ss.setExamCode(s.getExamCode());
            ss.setSubject(scores.get(0).getSubject());
            ss.setAnswerDate(scores.get(0).getAnswerDate());
            ss.setNum(scores.size());
            scoreLists.add(ss);
        }
//        List<Score> res = scoreService.list(new QueryWrapper<Score>().eq("teacherPhone",teacherphone));
        return CommonReturnType.create(scoreLists,"查询所有学生成绩");
    }

    /**
     *
     * @param teacherphone
     * @return
     */
    @GetMapping("/scores/devidebycourse")
    public CommonReturnType devidebycourse(@RequestParam String teacherphone) {
        List<ClassScore> classScoreList=new ArrayList<>();
        List<Score> stringList =scoreService.list(new QueryWrapper<Score>().select("DISTINCT subject").eq("teacherPhone",teacherphone));
        for(Score s:stringList){
            Double maxs=-999.0;
            Double mins=9999.0;
            Double sum=0.0;
            ClassScore classScore =new ClassScore();
            classScore.setCoursename(s.getSubject());
            List<Score> res = scoreService.list(new QueryWrapper<Score>().eq("teacherPhone",teacherphone).eq("subject",s.getSubject()));
            for(Score ss:res){
                if(ss.getEtScore()>maxs){
                    maxs=ss.getEtScore();
                }
                if(ss.getEtScore()<mins){
                    mins=ss.getEtScore();
                }
                sum+=ss.getEtScore();
            }
            classScore.setAveragescore(sum/res.size());
            classScore.setMaxscore(maxs);
            classScore.setMinscore(mins);
            classScoreList.add(classScore);
        }
        return CommonReturnType.create(classScoreList,"查询所有学生成绩");
    }

//    分页
    @GetMapping("/score/{page}/{size}/{studentId}")
    public CommonReturnType findById(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("studentId") String studentId) {
        Page<Score> scorePage = new Page<>(page, size);
        IPage<Score> res = scoreService.findById(scorePage, studentId);
//        Page<Score> res = scoreService.page(new QueryWrapper<Score>().eq("studentId",studentId));
        return CommonReturnType.create(res,"ID查询学生成绩");
    }

//    不分页
    @GetMapping("/score/{studentId}")
        public CommonReturnType findById(@PathVariable("studentId") String studentId) {
        List<Score> res = scoreService.findById(studentId);
        if (!res.isEmpty()) {
            return CommonReturnType.create( res, "根据ID查询成绩");
        } else {
            return CommonReturnType.create( res, "ID不存在");
        }
    }

    @PostMapping("/score")
    public CommonReturnType add(@RequestBody Score score) {
        int res = scoreService.add(score);
        if (res == 0) {
            return CommonReturnType.create(res,"成绩添加失败");
        }else {
            return CommonReturnType.create(res,"成绩添加成功");
        }
    }

    @GetMapping("/scores/{examCode}")
    public CommonReturnType findByExamCode(@PathVariable("examCode") Integer examCode) {
        List<Score> scores = scoreService.findByExamCode(examCode);
        return CommonReturnType.create(scores,"查询成功");
    }

    @PostMapping("/scores/class/{examCode}")
    public CommonReturnType findclassscoreByExamCode(@PathVariable("examCode") Integer examCode) {

        List<Score> scores = scoreService.list(new QueryWrapper<Score>().eq("examCode",examCode));

        List<ClassScore> classScoreList =new ArrayList<>();

        return CommonReturnType.create(scores,"查询成功");
    }
}
