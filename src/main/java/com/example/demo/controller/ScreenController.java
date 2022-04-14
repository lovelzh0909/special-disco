package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import sun.misc.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
@Slf4j
@RestController
@RequestMapping("/Screen")
public class ScreenController {

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam String phone, HttpServletRequest request) {
        //String path = request.getSession().getServletContext().getRealPath("/");
        String path = "D:\\"+phone+"\\";
        String fileName = file.getOriginalFilename();
        String suffix = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + suffix;
        File targetFile = new File(path, newFileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }

//    //读取文件夹内所有文件内容
//    @GetMapping("/read")
//    @ResponseBody
//    public String read() {
//        String path = "log/";
//        File file = new File(path);
//        StringBuilder sb = new StringBuilder();
//        if (file.exists()) {
//            File[] files = file.listFiles();
//            for (File f : files) {
//                try {
//                    InputStream inputStream = new ClassPathResource("log/"+ f.getName()).getInputStream();
//                    byte[] bytes = IOUtils.readFully(inputStream, -1, true);
//                    String str = new String(bytes, StandardCharsets.UTF_8);
//                    sb.append(str);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return sb.toString();
//    }

}
