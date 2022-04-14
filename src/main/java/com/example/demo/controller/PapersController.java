package com.example.demo.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Papers;
import com.example.demo.entity.Question;
import com.example.demo.service.PapersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
@Slf4j
@RestController
@RequestMapping("/papers")
public class PapersController {
    @Autowired
    PapersService papersService;

    @PostMapping("/save")
    public CommonReturnType saveQuestion(@RequestBody Papers p){
        p.setCreateTime(String.valueOf(LocalDateTime.now()));
//        if(p.getPhone()==null||p.getPapercontext()==null)
//        return CommonReturnType.create(null,"信息不全");
        // if(p.getSource()==null){
        //     p.setSource(p.getPaperId()+"");
        // }
//        p.setPaperId(papersService.findOnlyQuestionId());
        if(p.getPapernum()==null){
            p.setPapernum(0);
        }
        p.setCreateTime(String.valueOf(LocalDateTime.now()));
        boolean b = papersService.save(p);
        if(!b)
        return CommonReturnType.create(null,"添加试卷失败");
        return CommonReturnType.create(p.getPaperId());
    }

    @PostMapping("/update/{paperId}")
    public CommonReturnType updatePaper(@RequestBody Papers p,@PathVariable("paperId") Integer paperId){
        log.info("更新试卷数据");
        log.info("前端发送:"+paperId+":"+p);
        p.setPaperId(paperId);
        if(p.getPapernum()==null){
            p.setPapernum(0);
        }
        log.info("后端发送: success");
        boolean b = papersService.updateById(p);
        if(!b)
        return CommonReturnType.create(null,"修改试卷失败");
        return CommonReturnType.create(null);
    }

    @PostMapping("/changenum")
    public CommonReturnType usePaper(@RequestParam Integer paperId) {
        Papers p =papersService.getById(paperId);
        if(p==null){
            return CommonReturnType.create(null,"改试卷不存在或没有题目");
        }
        p.setPapernum(p.getPapernum()+1);
        papersService.save(p);
        return CommonReturnType.create(p);
    }

    @PostMapping("/changeStatus")
    public CommonReturnType changePaperStatus(@RequestParam Integer paperId) {
        Papers p =papersService.getById(paperId);
        // String lString =p.getPapercontext();
        //获取每个题目Id放入lint
        // List<String> l =stringToList(lString);
        // List<Integer> lint;
        // for(String i:l ){
        //     lint.add(Integer.parseInt(i));
        // }
        // List<Question> picture = papersService.getpapersQuestions(paperId);
        if(p==null){
            return CommonReturnType.create(null,"改试卷不存在或没有题目");
        }
        // if(p.getPaperstatus()=="")
        // p.setPaperstatus("paperstatus");
        papersService.save(p);
        return CommonReturnType.create(p);
    }

    // @PostMapping("/getpaperbyID")
    // public CommonReturnType getStudentvideo(@RequestParam Integer paperId) {
    //     Papers p =papersService.getById(paperId);
    //     if(p==null){
    //         return CommonReturnType.create(null,"改试卷不存在或没有题目");
    //     }
    //     return CommonReturnType.create(p);
    // }

    // @PostMapping("/getPaperQuestion")
    // public CommonReturnType getpaperproblem(@RequestParam Integer paperId) {
    //     Papers p =papersService.getById(paperId);
    //     String lString =p.getPapercontext();
    //     //获取每个题目Id放入lint
    //     List<String> l =stringToList(lString);
    //     List<Integer> lint =new ArrayList<Integer>();
    //     List<Question> lQuestion =new ArrayList<Question>();
    //     for(String i:l ){
    //         lint.add(Integer.parseInt(i));
    //         lQuestion.add(papersService.getQuestions(Integer.parseInt(i)));
    //     }
    //     // List<Question> picture = papersService.getpapersQuestions(paperId);
    //     if(lint.size()==0||lQuestion.size()==0){
    //         return CommonReturnType.create(null,"改试卷不存在或没有题目");
    //     }
    //     return CommonReturnType.create(lQuestion);
    // }


    @PostMapping("/getteacherallpaper/{page}/{size}")
    public CommonReturnType getallpaper(@RequestParam String phone,@PathVariable int page,@PathVariable int size) {
        Page <Papers> p = new Page<>(page, size);
        Page<Papers> page3=papersService.page(p, new QueryWrapper<Papers>()
        .eq("createrPhone", phone));
        List<Papers> picture = papersService.list(new QueryWrapper<Papers>()
        .eq("createrPhone", phone));
        page3.setSize(picture.size());
        if(picture.size()==0){

            return CommonReturnType.create(null,"试卷不存在或没有题目");
        }
        log.info("-------------log---------------");
        log.info("send["+page3.getTotal()+"条数据]");
        return CommonReturnType.create(page3);
    }

    @PostMapping ("/remove")
    public CommonReturnType removeQuestion(@RequestParam int paperId){

        boolean data=papersService.remove(new QueryWrapper<Papers>()
                .eq("paperId", paperId)
        );
        if(!data){
            return CommonReturnType.create(null,"试卷已不存在");
        }

        return CommonReturnType.create(null);
    }


    @PostMapping ("/removemore")
    public CommonReturnType removemoreQuestion(@RequestParam List<Integer> id){
        // papersService.removeBatchByIds(list)
        boolean data=papersService.removeBatchByIds(id);
        if(!data){
            return CommonReturnType.create(null,"试卷已不存在");
        }

        return CommonReturnType.create(null);
    }



        private List<String> stringToList(String strs){
        String[] str = strs.split(",");
        return Arrays.asList(str);
   }
//复制试卷
    @PostMapping("/copy")
    public CommonReturnType copy(@RequestParam int paperId){
        Papers p =papersService.getById(paperId);
        if(p==null){
            return CommonReturnType.create(null,"改试卷不存在或没有题目");
        }
        p.setPaperId(null);
        p.setCreateTime(String.valueOf(LocalDateTime.now()));
        papersService.save(p);
        return CommonReturnType.create(p);
    }



    
//    private List<int[]> stringToIntList(String strs){
//        int str[];
//        try{
//     int str[] =Integer.parseInt(strs.split(","));}
//     // String str[] =Integer.parseInt(strs.split(","));
//     return Arrays.asList(str);
}


