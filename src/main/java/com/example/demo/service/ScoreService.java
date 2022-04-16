package com.example.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Score;

public interface ScoreService extends IService<Score> {
    int add(Score score);

    List<Score> findAll();

    IPage<Score> findById(Page page, String studentId);

    List<Score> findById(String studentId);

    List<Score> findByExamCode(Integer examCode);
}
