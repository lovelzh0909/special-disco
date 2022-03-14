package com.example.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.VO.StudentTestNoticeVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-03-08
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    @Select("SELECT test.`testtime`,user.`name`,user.`studentId`,test.`coursename`,test.`invigilator`,test.`testteacher`,test.`timelast` FROM test,user WHERE test.phone=user.phone and test.phone ="+"${phone}")
    List<StudentTestNoticeVO> getStudentNote(/*Page<StudentTestNoticeVO> page*/String phone);
}
