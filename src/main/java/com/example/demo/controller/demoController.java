package com.example.demo.controller;


import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class demoController {
    @Autowired
    UserService userService;
//    @ResponseBody
//    @GetMapping("/user")
//    public User getById(@RequestParam("id") int id){
//
//        return userService.getUserByid(id);
//    }

//    @PostMapping("/select")
//    public User selectUser(@PathVariable String Id){
//        return userService.getUserByid(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public CommonReturnType delectUser(@PathVariable String Id){
//        User user = new User(Id, 0);
//        return CommonReturnType.create(user);
//    }


}
