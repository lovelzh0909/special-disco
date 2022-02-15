package com.example.demo.dao;

import com.example.demo.entity.Userlogon;


public interface userLogonDao {
    // 查询ID、密码、登录状态匹配；修改密码：退出登录
    Userlogon selectById(String Id);
}
