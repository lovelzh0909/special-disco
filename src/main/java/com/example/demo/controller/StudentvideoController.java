package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Studentvideo;
import com.example.demo.entity.VO.StudentVideoVO;
import com.example.demo.service.StudentvideoService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.time.LocalDateTime;


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

        boolean b ;
        try{
        studentvideo.setTime(LocalDateTime.now());
        b=studentvideoService.save(studentvideo);}catch(Exception e){
            e.printStackTrace();
            return CommonReturnType.create("失败");
        }
        if(b==true){
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
    @PostMapping("/get")
    public CommonReturnType getStudentvideo(@RequestBody String StudentId) {
        Page<StudentVideoVO> picture = studentvideoService.getStudentPicture(new Page<> (),StudentId);
        if(picture==null){
            return CommonReturnType.create("没有找到相应照片");
        }
        return CommonReturnType.create(picture);
    }
}

