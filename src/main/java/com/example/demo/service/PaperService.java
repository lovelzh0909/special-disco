package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.PaperManage;

public interface PaperService {

    List<PaperManage> findAll();

    List<PaperManage> findById(Integer paperId);

    int add(PaperManage paperManage);
}
