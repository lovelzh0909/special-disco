package com.example.demo.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Notice;
import com.example.demo.entity.Test;
import com.example.demo.entity.vo.StudentTestNoticeVO;
import com.example.demo.service.NoticeService;
import com.example.demo.service.TestService;

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
 * @since 2022-03-08
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    TestService testService;

    @PostMapping("/one")
    public CommonReturnType listOne(@RequestParam String phone){
        // QueryWrapper<Notice> queryWrapper= new QueryWrapper();
        // queryWrapper.select("notice");
        List<Test> l=testService.list(new QueryWrapper<Test>().eq("phone",phone));
        if(l!=null&&l.size() != 0){
            return CommonReturnType.create(l,"success");
        }
        else{
            return CommonReturnType.create(null,"没有考试信息");
        }
    }
    @PostMapping
    public CommonReturnType notice(@RequestParam String phone) {
        //new CommonReturnType();
        QueryWrapper<Notice> queryWrapper= new QueryWrapper();
        queryWrapper.select("notice");
        List<Notice> note =noticeService.list(queryWrapper.eq("phone", phone));
        if(note!=null&&note.size() != 0){
            return CommonReturnType.create(note,"success");
        }else{
            return CommonReturnType.create(null,"暂无通知");
        }
    }
    @PostMapping("/getStudentNotice")
    public CommonReturnType getStudentNotice(@RequestParam String phone) {
        // Map<String, Object> map = new HashMap<>();
        Page<StudentTestNoticeVO> Studentnote = noticeService.getStudentNote(new Page<>(),phone);
        if(Studentnote!=null&&Studentnote.getRecords().size() != 0){
            return CommonReturnType.create(Studentnote,"success");
        }
        else{
            return CommonReturnType.create(null,"暂无通知");
        }
        // if (Studentnote.getRecords().size() == 0) {
        //     map.put("message", "暂无数据");
        // } else {
        //     map.put("message", "success");
        //     map.put("data", Studentnote);
        // }
        // return map;

    }
    /**
     * 
     * @param phone
     * @return 返回7天内test通知信息
     */
    @PostMapping("/closenotice")
    public CommonReturnType closenotice(@RequestParam String phone) {
        // Map<String, Object> map = new HashMap<>();
        //new CommonReturnType();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // String dateString = "2017-01-01 11:11:11";
        Calendar calendar = Calendar.getInstance();
        long specialDate;
        long betweenDate = 0;
        long nowDate = calendar.getTime().getTime(); //Date.getTime() 获得毫秒型日期
        QueryWrapper<Test> queryWrapper = new QueryWrapper();
        queryWrapper.select("testtime","coursename","note");
        List<Test> note = testService.list(queryWrapper.eq("phone", phone));

        for (int i = 0; i < note.size(); i++) {
            try {
                specialDate = sdf.parse(note.get(i).getTesttime()).getTime();
                betweenDate = (specialDate - nowDate) / (1000 * 60 * 60 * 24);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (betweenDate > 7 || betweenDate<0) {
                note.remove(i);
            }
            else {
                note.get(i).setNote(String.valueOf(betweenDate));
            }
        }
        // if (note.size() == 0) {
        //     map.put("message", "暂无数据");
        // } else {
        //     map.put("message", "success");
        //     map.put("data", note);
        // }
        // return map;
        if(note!=null&&note.size() != 0){
            return CommonReturnType.create(note,"success");
        }
        else{
            return CommonReturnType.create(null,"暂无通知");
        }
    }




}

