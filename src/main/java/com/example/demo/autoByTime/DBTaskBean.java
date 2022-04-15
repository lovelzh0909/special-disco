package com.example.demo.autoByTime;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Notice;
import com.example.demo.entity.Test;
import com.example.demo.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Component
public class DBTaskBean {

    @Autowired
    NoticeService noticeService;
    @Scheduled(cron = "0 0 5 * * ?")
    public void task() {
        LocalDateTime specialDate;
        long betweenDate;
        LocalDateTime nowDate = LocalDateTime.now(); //Date.getTime() 获得毫秒型日期
        List<Notice> note = noticeService.list();


        for (Notice notice : note) {
            specialDate = LocalDateTime.parse(notice.getDeadLine());
            betweenDate = Duration.between(nowDate, specialDate).toDays();
            if (betweenDate < 0) {
                log.info("delete[" + notice + "]");
                noticeService.removeById(notice.getId());
            }

//
//            note.get(i).setCountdown(Math.toIntExact(betweenDate));
//            noticeService.saveOrUpdateBatch(note);
//
        }
        log.info("Thread Name : "
                + Thread.currentThread().getName() + "  i am a task : date ->  "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }
}