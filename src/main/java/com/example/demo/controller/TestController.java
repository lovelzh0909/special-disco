package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Test;
import com.example.demo.entity.Testrelstudent;
import com.example.demo.service.TestService;
import com.example.demo.service.TestrelstudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    TestrelstudentService testrelstudentService;
    @PostMapping("/all")
    public CommonReturnType listAll(){
        List<Test> l =testService.list();
        if(l==null){
            return CommonReturnType.create("没有任何考试信息");
        }
        return CommonReturnType.create(l);
    }
    @PostMapping("/save")
    public CommonReturnType saveTest(@RequestBody Test t ){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        if(t.getCreatedate()==null){
            //添加时间
            // Date d= new Date();
        }
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        Boolean data = testService.save(t);
        if(data==false)
        return CommonReturnType.create(null,"添加失败");
        return CommonReturnType.create(null);
    }
    @PostMapping("/changeStatus")
    public  CommonReturnType changeStatus(@RequestBody int id){
        Test t =testService.getById(id);
        t.setPystatus("pystatus");
        t.setTeststatus(1);
        Boolean data = testService.save(t);
        if(data==false)
        return CommonReturnType.create(null,"添加失败");
        return CommonReturnType.create(null);
        // return testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
    }
    /**
     * 
     * @param test
     * @return
     */
    @PostMapping("/getStudenttest/{page}/{size}")
    public  CommonReturnType listOne(@RequestParam Integer phone,@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<Test> page2 =new Page<Test>(page,size);
        List<Testrelstudent> ts =testrelstudentService.list(new QueryWrapper<Testrelstudent>().eq("studentPhone", phone));
//        List<Test> l=new ArrayList<Test>();
        List<Integer> ll=new ArrayList<Integer>();
        for(Testrelstudent t:ts){
//            l.add(testService.getById(t.getTestId())) ;
            ll.add(t.getTestId());
        }
        if(ll.size()==0){
            return CommonReturnType.create(null,"没有该学生考试信息");
        }
        Page<Test> page3=testService.page(page2, new QueryWrapper<Test>().in("testId", ll));
        page3.setTotal(ll.size());
        // List<Test> l =testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
    //     List<Pipeline> subList = newPipelineList.subList(fromIndex, toIndex);
    // Pageable pageable = new PageRequest(page, size);
        // Page<Test> pipelinePage = new PageImpl<>(l, page2, l.size());

    // return new PageInfo<>(pipelinePage);
//        if(l.size()==0){
//            return CommonReturnType.create(null,"没有该学生考试信息");
//        }
        return CommonReturnType.create(page3);
        // return testService.list(new QueryWrapper<Test>().eq("phone", test.getPhone()));
    }
    /**
     * 
     * @param test
     * @return
     */
    @PostMapping("/getTeachertest/{page}/{size}")
    public CommonReturnType listteacherOne(@RequestBody Test test,@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        List<Test> l =testService.list(new QueryWrapper<Test>().eq("teacherphone", test.getTeacherphone()));
        Page<Test> page2 =new Page<Test>(page,size);
        Page<Test> p =testService.page(page2,new QueryWrapper<Test>().eq("teacherphone", test.getTeacherphone()));
        p.setTotal(l.size());
        if(l.size()==0){
            return CommonReturnType.create("没有该老师相关考试信息");
        }
        return CommonReturnType.create(p);
        // return testService.list(new QueryWrapper<Test>().eq("teacherphone", test.getTeacherphone()));
    }

    /**
     * 
     * @param id
     * @return
     */
    @PostMapping ("/remove")
    public CommonReturnType removeQuestion(@RequestBody int id){

        boolean data=testService.remove(new QueryWrapper<Test>()
                .eq("testId", id)
        );
        if(data==false){
            return CommonReturnType.create("没有该测试或已经被删除");
        }

        return CommonReturnType.create(null);
    }
    /**
     * 
     * @param t
     * @return
     */
    @PostMapping("/selectbyid")
    public CommonReturnType showTest(@RequestBody int t ){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        Test data = testService.getById(t);
        if(data==null)
        return CommonReturnType.create(null,"没有任何该考试信息");
        return CommonReturnType.create(null);
    }

    /**
     * 通过list id 获取相关测试信息
     * @param t
     * @return
     */
    @PostMapping("/selectbylistid")
    public CommonReturnType showTest(@RequestBody List<Integer> t ){
        // if(q.getStem()==null||q.getAnswer()==null||q.getCoursename()==null||q.getType()==null)
        // return CommonReturnType.create(null,"信息不全");
        // if(q.getId()==null){
        //     q.setId(questionService.lastQuestionId()+1);
        // }
        List<Test> data = testService.listByIds(t);
        if(data==null)
        return CommonReturnType.create(null,"没有任何该考试信息");
        return CommonReturnType.create(null);
    }


}

