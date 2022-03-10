package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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
    @RequestMapping("/logon")
    public CommonReturnType logon(@RequestBody User user) {
        //new CommonReturnType();
        User one = userService.getOne(
                new QueryWrapper<User>()
                        .eq("phone", user.getPhone())
                        .eq("password", user.getPassword()));
        if(one!=null){
            return CommonReturnType.create(one.getRole(),"success");
        }else{
            return CommonReturnType.create(null,"用户名或密码错误");
        }
    }

//    @RequestMapping("/register")
//    public CommonReturnType register(@RequestBody User user) {
//        userService.save(user);
//        return CommonReturnType.create(null,"success");
//    }

    @RequestMapping("/register")
    public CommonReturnType register(@RequestBody User user) {
        User one = userService.getOne(new QueryWrapper<User>().eq("phone", user.getPhone()));
        if(one!=null){
            return CommonReturnType.create(null,"用户名已存在");
        }else{
            userService.save(user);
            assert false;
            return CommonReturnType.create(user.getRole(),"success");
        }
    }
    @RequestMapping("/delete")
    public CommonReturnType delete(@RequestBody User user) {
        userService.remove(new QueryWrapper<User>().eq("phone", user.getPhone()));
        return CommonReturnType.create(null,"success");
    }
    @RequestMapping("/information")
    public CommonReturnType information(@RequestParam String phone) {
        //new CommonReturnType();
        User one = userService.getOne(
                new QueryWrapper<User>()
                        .eq("phone", phone)
                        );
        if(one!=null){
            return CommonReturnType.create(one,"success");
        }else{
            return CommonReturnType.create(null,"用户名错误");
        }
    }
}
