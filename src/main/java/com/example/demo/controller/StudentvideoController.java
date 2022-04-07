package com.example.demo.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Studentvideo;
import com.example.demo.entity.Test;
import com.example.demo.entity.Testrelstudent;
import com.example.demo.entity.User;
import com.example.demo.entity.vo.StudentVideoVO;
import com.example.demo.service.StudentvideoService;
import com.example.demo.service.TestService;
import com.example.demo.service.TestrelstudentService;
import com.example.demo.service.UserService;

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
@RequestMapping("/studentvideo")
public class StudentvideoController {
    @Autowired
    StudentvideoService studentvideoService;
    @Autowired
    UserService userService;
    @Autowired
    TestrelstudentService testrelstudentService;
    @Autowired
    TestService testService;
    @PostMapping("/save")
    public CommonReturnType saveStudentvideo(@RequestBody Studentvideo studentvideo) {

        boolean b ;
        try{
        studentvideo.setTime(LocalDateTime.now());
        b=studentvideoService.save(studentvideo);}catch(Exception e){
            e.printStackTrace();
            return CommonReturnType.create("失败");
        }
        if(b){
            return CommonReturnType.create(null);
        }
        else
        return CommonReturnType.create("插入失败");

        // try {
        //     specialDate = sdf.parse(note.get(i).getTesttime()).getTime();
        //     betweenDate = (specialDate - nowDate) / (1000 * 60 * 60 * 24);
        // } catch (ParseException e) {
        //     e.printStackTrace();
        // }
    }
    /**
     * 
     * @param StudentId
     * @return
     */
    @PostMapping("/get")
    public CommonReturnType getStudentvideo(@RequestBody String StudentId) {
        Page<StudentVideoVO> picture = studentvideoService.getStudentPicture(new Page<>(),StudentId);
        if(picture==null){
            return CommonReturnType.create("没有找到相应照片");
        }
        return CommonReturnType.create(picture);
    }
    @PostMapping("/invigilate")
    public CommonReturnType invigilate(@RequestParam Integer testId) {
        List<Testrelstudent> testrelstudents = testrelstudentService.list(new QueryWrapper<Testrelstudent>().eq("testId", testId));
        List<StudentVideoVO> studentintest = new ArrayList<>();
        for (Testrelstudent testrelstudent : testrelstudents) {
            StudentVideoVO student = new StudentVideoVO();
            student.setStudentId(userService.getOne(new QueryWrapper<User>().eq("phone", testrelstudent.getStudentPhone())).getStudentId());
            student.setName(userService.getOne(new QueryWrapper<User>().eq("phone", testrelstudent.getStudentPhone())).getName());
            if(studentvideoService.getOne(new QueryWrapper<Studentvideo>().eq("phone", testrelstudent.getStudentPhone()).last("limit 1"))==null){
                student.setVideo(null);
            }
            else {
                student.setVideo(studentvideoService.getOne(new QueryWrapper<Studentvideo>().eq("phone", testrelstudent.getStudentPhone()).last("limit 1")).getVideo());
            }
//            student.setStatus(testrelstudentService.getOne(new QueryWrapper<Testrelstudent>().eq("testId",testId)).getStatus());
            student.setStatus(testService.getOne(new QueryWrapper<Test>().eq("testId",testId)).getTeststatus());
            studentintest.add(student);
        }
        return CommonReturnType.create(studentintest);
    }
}

