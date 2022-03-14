package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Studentvideo;
import com.example.demo.entity.Test;
import com.example.demo.entity.VO.StudentVideoVO;
import com.example.demo.service.StudentvideoService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    @PostMapping("/save")
    public CommonReturnType saveStudentvideo(@RequestBody Studentvideo studentvideo) {

        studentvideo.setTime(LocalDateTime.now());
        studentvideoService.save(studentvideo);
        return CommonReturnType.create(null);
    }
    @PostMapping("/get")
    public CommonReturnType getStudentvideo(@RequestBody String StudentId) {
        Page<StudentVideoVO> picture = studentvideoService.getStudentPicture(new Page<> (),StudentId);
        return CommonReturnType.create(picture);
    }
}

