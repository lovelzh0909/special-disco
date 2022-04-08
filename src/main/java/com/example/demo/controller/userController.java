package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
public class userController {
    @Autowired
    UserService userService;
    //@ResponseBody
//    @GetMapping("/user")
//    public User getById(@RequestParam("id") int id){
//
//        return userService.getUserByid(id);
//    }
    @PostMapping("/logon")
    public CommonReturnType logon(@RequestBody User user) {
        //new CommonReturnType();

        User one = userService.getOne(
                new QueryWrapper<User>()
                        .eq("phone", user.getPhone())
                        .eq("password", user.getPassword()));
                
        if(one!=null){
            log.info("--------------------logo-------------------");
            log.info("logon["+ one +"]");
            return CommonReturnType.create(one.getRole(),"success");
        }
        return CommonReturnType.create(null,"用户名或密码错误");
        
    }

//    @RequestMapping("/register")
//    public CommonReturnType register(@RequestBody User user) {
//        userService.save(user);
//        return CommonReturnType.create(null,"success");
//    }

    @PostMapping("/register")
    public CommonReturnType register(@RequestBody User user) {
        User one = userService.getOne(new QueryWrapper<User>().eq("phone", user.getPhone()));
        if(one!=null){
            return CommonReturnType.create(null,"用户名已存在");
        }else{
            userService.save(user);
            log.info("--------------------logo-------------------");
            log.info("register["+ user +"]");
            assert false;
            return CommonReturnType.create(user.getRole(),"success");
        }
    }
    @PostMapping("/delete")
    public CommonReturnType delete(@RequestBody User user) {
        boolean b=userService.remove(new QueryWrapper<User>().eq("phone", user.getPhone()));
        if(!b)
        return CommonReturnType.create("没有该用户");
        log.info("--------------------logo-------------------");
        log.info("delete["+ user +"]");
        return CommonReturnType.create(null,"success");
    }
    @PostMapping("/information")
    public CommonReturnType information(@RequestParam String phone) {
        //new CommonReturnType();
        User one = userService.getOne(
                new QueryWrapper<User>()
                        .eq("phone", phone)
                        );
        if(one!=null){
            log.info("--------------------logo-------------------");
            log.info("send["+ one +"]");
            return CommonReturnType.create(one,"success");
        }
        else{
            return CommonReturnType.create(null,"用户名错误");
        }
    }
    @PostMapping("/update")
    public CommonReturnType update(@RequestBody User user) {
        User one = userService.getOne(
                new QueryWrapper<User>()
                        .eq("phone", user.getPhone())
        );
        if(one!=null){
            one.setPassword(user.getPassword());
            one.setName(user.getName());
            one.setRole(user.getRole());
            userService.updateById(one);
            log.info("--------------------logo-------------------");
            log.info("update["+ one +"]");
            return CommonReturnType.create(one.getRole(),"success");
        }
        else{
            return CommonReturnType.create(null,"用户名错误");
        }
    }
}
