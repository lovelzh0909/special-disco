package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Score;
import com.example.demo.entity.User;
import com.example.demo.service.PapersService;
import com.example.demo.service.ScoreService;
import com.example.demo.service.UserService;
import jxl.CellType;
import jxl.read.biff.BiffException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/GetFile")
public class GetFileController {
    @Autowired
    ScoreService scoreService;
    @Autowired
    UserService userService;
    @SneakyThrows
    @PostMapping("/excel/{phone}/{subject}/{examcode}")
    public CommonReturnType savefile(@RequestBody MultipartFile file,@PathVariable String phone,@PathVariable String subject,@PathVariable Integer examcode){
        log.info(String.valueOf(file));
//        File tfile = new File(file.getOriginalFilename());
        File tfile =MultipartFileToFile(file);
        XSSFWorkbook workbook=new XSSFWorkbook(FileUtils.openInputStream(tfile));
        XSSFSheet sheet=workbook.getSheetAt(0);
        if(sheet==null){
            return CommonReturnType.create(null,"excel为空");
        }

        //获取sheet中最后一行行号
        int lastRowNum=sheet.getLastRowNum();
        log.info(String.valueOf(lastRowNum));
        if(lastRowNum==0){
            return CommonReturnType.create(null,"excel为空");
        }
        Score score=new Score();
        score.setTeacherPhone(phone);
        score.setSubject(subject);
        score.setExamCode(examcode);
        score.setAnswerDate(String.valueOf(LocalDate.now()));
        for (int i=0;i<=lastRowNum;i++){
            XSSFRow row=sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum=row.getLastCellNum();
            log.info(String.valueOf(lastCellNum));
            for (int j=0;j<lastCellNum;j++){
                XSSFCell cell=row.getCell(j);
                cell.setCellType(1);
                log.info(String.valueOf(cell));
                String value =String.valueOf(cell);
                log.info(value+"");
                if(j==0){
                    User user =userService.getOne(new QueryWrapper<User>().eq("studentId",value));
                    if(user==null){
                        return CommonReturnType.create(null,"excel 里的学生id 不存在");
                    }
                    score.setStudentId(user.getPhone());
                }
                if(j==1)
                score.setEtScore((double)  Integer.valueOf(value) );
            }
            scoreService.save(score);
        }
        tfile.delete();
        return CommonReturnType.create(null);
    }
    public static File MultipartFileToFile(MultipartFile multipartFile) {

        File file = null;
        //判断是否为null
        if (multipartFile.equals("") || multipartFile.getSize() <= 0) {
            return file;
        }
        //MultipartFile转换为File
        InputStream ins = null;
        OutputStream os = null;
        try {
            ins = multipartFile.getInputStream();
            file = new File(multipartFile.getOriginalFilename());
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ins != null){
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }



}
