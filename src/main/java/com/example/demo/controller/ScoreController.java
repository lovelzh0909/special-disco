package com.example.demo.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Score;
import com.example.demo.service.impl.ScoreServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScoreController {
    @Autowired
    private ScoreServiceImpl scoreService;

    @GetMapping("/scores")
    public CommonReturnType findAll() {
        List<Score> res = scoreService.findAll();
        return CommonReturnType.create(res,"查询所有学生成绩");
    }

    @GetMapping("/scores/byteachphone")
    public CommonReturnType findAllbyphone(@RequestParam String teacherphone) {

        List<Score> res = scoreService.list(new QueryWrapper<Score>().eq("teacherPhone",teacherphone));
        return CommonReturnType.create(res,"查询所有学生成绩");
    }
//    分页
    @GetMapping("/score/{page}/{size}/{studentId}")
    public CommonReturnType findById(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("studentId") Integer studentId) {
        Page<Score> scorePage = new Page<>(page, size);
        IPage<Score> res = scoreService.findById(scorePage, studentId);
        return CommonReturnType.create(res,"ID查询学生成绩");
    }

//    不分页
    @GetMapping("/score/{studentId}")
        public CommonReturnType findById(@PathVariable("studentId") Integer studentId) {
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
}
