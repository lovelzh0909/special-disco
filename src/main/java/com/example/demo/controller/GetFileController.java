package com.example.demo.controller;

import com.example.demo.entity.Score;
import com.example.demo.service.PapersService;
import com.example.demo.service.ScoreService;
import jxl.read.biff.BiffException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/GetFile")
public class GetFileController {
    @Autowired
    ScoreService scoreService;
    @SneakyThrows
    @PostMapping("/excel")
    public  int savefile(@RequestParam("file") MultipartFile file){
        log.info(String.valueOf(file));
        File tfile = new File(file.getOriginalFilename());
        XSSFWorkbook workbook=new XSSFWorkbook(FileUtils.openInputStream(tfile));
//        Workbook workbook=Workbook.getWorkbook(tfile);
        //两种方式读取工作表
        //  HSSFSheet sheet=workbook.getSheet("Sheet0");
        XSSFSheet sheet=workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum=sheet.getLastRowNum();
        for (int i=0;i<=lastRowNum;i++){
            XSSFRow row=sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum=row.getLastCellNum();
            for (int j=0;j<lastCellNum;j++){
                XSSFCell cell=row.getCell(j);
                String value=cell.getStringCellValue();
                System.out.print(value+" ");
            }
            System.out.println();}
        return 1;
    }
//    public static File MultipartFileToFile(MultipartFile multipartFile) {
//
//        File file = null;
//        //判断是否为null
//        if (multipartFile.equals("") || multipartFile.getSize() <= 0) {
//            return file;
//        }
//        //MultipartFile转换为File
//        InputStream ins = null;
//        OutputStream os = null;
//        try {
//            ins = multipartFile.getInputStream();
//            file = new File(multipartFile.getOriginalFilename());
//            os = new FileOutputStream(file);
//            int bytesRead = 0;
//            byte[] buffer = new byte[8192];
//            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if(os != null){
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(ins != null){
//                try {
//                    ins.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return file;
//    }



}
