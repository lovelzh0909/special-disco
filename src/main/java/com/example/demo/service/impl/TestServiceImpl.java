package com.example.demo.service.impl;

import com.example.demo.entity.Test;
import com.example.demo.mapper.TestMapper;
import com.example.demo.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-03
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}
