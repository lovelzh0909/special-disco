package com.example.demo.mapper;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Notice;
import com.example.demo.entity.vo.StudentTestNoticeVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    @Select("SELECT test.`testtime`,user.`name`,user.`studentId`,test.`coursename`,test.`invigilator`,test.`testteacher`,test.`timelast` FROM test,user WHERE test.teacherphone=user.phone and test.studentId ="+"${studentId}"+"and test. ="+"${studentId}")
    List<StudentTestNoticeVO> getStudentNote(/*Page<StudentTestNoticeVO> page*/String phone);
}
