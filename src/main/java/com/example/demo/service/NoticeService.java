package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Notice;
import com.example.demo.entity.vo.StudentTestNoticeVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-08
 */
public interface NoticeService extends IService<Notice> {
    Page<StudentTestNoticeVO> getStudentNote(Page<StudentTestNoticeVO> page,String phone);
}

