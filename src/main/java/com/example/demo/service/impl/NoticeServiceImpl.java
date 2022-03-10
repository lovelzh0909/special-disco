package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Notice;
import com.example.demo.entity.VO.StudentTestNoticeVO;
import com.example.demo.mapper.NoticeMapper;
import com.example.demo.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-08
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    public Page<StudentTestNoticeVO> getStudentNote(Page<StudentTestNoticeVO> page,String phone){
        return page.setRecords(this.baseMapper.getStudentNote(/*page*/phone));
    }
}
