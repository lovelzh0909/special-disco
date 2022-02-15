package com.example.demo.mapper;


import com.example.demo.entity.Userlogon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserlogonMapper {
    Userlogon getUserlogon(int id);
}
