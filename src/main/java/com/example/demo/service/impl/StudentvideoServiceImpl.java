package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Studentvideo;
import com.example.demo.entity.VO.StudentVideoVO;
import com.example.demo.mapper.StudentvideoMapper;
import com.example.demo.service.StudentvideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@Service
public class StudentvideoServiceImpl extends ServiceImpl<StudentvideoMapper, Studentvideo> implements StudentvideoService {
    public Page<StudentVideoVO> getStudentPicture(Page<StudentVideoVO> page, String phone){
        return page.setRecords(this.baseMapper.getStudentPicture(/*page*/phone));
    }
}
