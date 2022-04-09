package com.example.demo.configure;

import com.example.demo.service.QuestionService;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
public class configure {

//    @Autowired
//    QuestionService questionService;
//
//    @Bean
//    public QuestionService questionService(){
//        return  questionService;
//    }
//
    //建立一个User类型的user为ID的实例（返回的就是）
//    @Bean
//    public User user(){
//        User user = new User("hehe",12);
//        user.setAge(15);
//        return user;
//    }
}
