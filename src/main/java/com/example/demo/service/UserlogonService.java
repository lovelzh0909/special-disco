package com.example.demo.service;


import com.example.demo.mapper.UserlogonMapper;
import com.example.demo.entity.Userlogon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserlogonService {
    @Autowired
    UserlogonMapper userlogonMapper;
    public Userlogon getUserlogonByid(int id){
        return userlogonMapper.getUserlogon(id);
    }
}
