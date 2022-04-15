package com.example.demo.controller;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Response.CommonReturnType;
import com.example.demo.entity.Studentvideo;
import com.example.demo.service.StudentvideoService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;


import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

@Slf4j
@RestController
@RequestMapping("/CompareFace")
public class CompareFaceController {
    @Autowired
    StudentvideoService studentvideoService;
    @SneakyThrows
    @RequestMapping(value = "/compareFaces", method = RequestMethod.POST)
    public CommonReturnType compareFaces(@RequestParam String phone,@RequestParam String testid) {
        List<Studentvideo> studentvideoList =studentvideoService.list(new QueryWrapper<Studentvideo>().eq("phone",phone));
        byte[] bytes1 =null;
        byte[] bytes2 =null;
//        Studentvideo studentvideo1 = new Studentvideo();
//        Studentvideo studentvideo2 =new Studentvideo();
        int i=0;
        for(Studentvideo studentvideo:studentvideoList){
            i++;
            if(i==1){
//                log.info(studentvideo.getVideo().substring(22));
             bytes1 = Base64.getDecoder().decode(studentvideo.getVideo().substring(22));
            }
            if(i==2){
                bytes2 = Base64.getDecoder().decode(studentvideo.getVideo().substring(22));
            }
        }
        if(bytes1==null||bytes2==null){
            return  CommonReturnType.create(null ,"抓拍照片不足");
        }

//        file=new File(fileName);
//        fos = new java.io.FileOutputStream(file);
//        bos = new BufferedOutputStream(fos);
//        bos.write(bytes);
//        byte [] bytes1 = DatatypeConverter.parseBase64Binary(image1);
//        log.info(String.valueOf(bytes1));
//        Encoder encoder2 = Base64.getEncoder();
//        byte[] bytes2 = encoder2.encode(image2.getBytes());
//        byte[] bytes1 = Base64Util.base64ToBytes(image1);
//        byte[] bytes2 = Base64Util.base64ToBytes(image2);

        String appId = "DbaSY33oQnAm8YqaSjM1s6qztZgix8TwLLwdG9wo93M1";
        String sdkKey = "3FhGDDXovhqfzPdGUMqY7JMyR75P36v537FGhVDJziXa";

        FaceEngine faceEngine = new FaceEngine("E:\\project\\demo\\libs\\WIN64");
        //激活引擎
        int errorCode = faceEngine.activeOnline(appId, sdkKey);
        System.out.println(errorCode);
        System.out.println(ErrorInfo.MOK.getValue());
        System.out.println(ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue());
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }


        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("获取激活文件信息失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        engineConfiguration.setDetectFaceMaxNum(10);
        engineConfiguration.setDetectFaceScaleVal(16);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        //初始化引擎
        errorCode = faceEngine.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
        //人脸检测
        ImageInfo imageInfo = ImageFactory.getRGBData(bytes1);
//
//        ;
//        ImageInfo imageInfo = getRGBData(new File("C:\\Users\\laptop ct\\Desktop\\temp\\01c41457b6f04d0000018c1b286c04.jpg@1280w_1l_2o_100sh.jpg"));
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
        System.out.println(faceInfoList);

        //特征提取
        FaceFeature faceFeature = new FaceFeature();
        errorCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

        //人脸检测2
//        ImageInfo imageInfo2 = getRGBData(new File("C:\\Users\\laptop ct\\Desktop\\temp\\01c41457b6f04d0000018c1b286c04.jpg@1280w_1l_2o_100sh.jpg"));
        ImageInfo imageInfo2 = ImageFactory.getRGBData(bytes2);
//        ImageInfo imageInfo2 = getRGBData(new File("d:\\ccc.jpg"));
        List<FaceInfo> faceInfoList2 = new ArrayList<FaceInfo>();
        errorCode = faceEngine.detectFaces(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(),imageInfo.getImageFormat(), faceInfoList2);
        System.out.println(faceInfoList);

        //特征提取2
        FaceFeature faceFeature2 = new FaceFeature();
        errorCode = faceEngine.extractFaceFeature(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(), imageInfo.getImageFormat(), faceInfoList2.get(0), faceFeature2);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

        //特征比对
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
        FaceSimilar faceSimilar = new FaceSimilar();

        errorCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);

        System.out.println("相似度：" + faceSimilar.getScore());

//        FaceFeature faceFeature = new FaceFeature();
//        FaceFeature faceFeature2 = new FaceFeature();
//        FaceFeature targetFaceFeature = new FaceFeature();
//        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
//        FaceFeature sourceFaceFeature = new FaceFeature();
//        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
//        FaceSimilar faceSimilar = new FaceSimilar();
//        Float similar = faceEngineService.compareFace(rgbData1, rgbData2);
        return CommonReturnType.create(faceSimilar.getScore());
//        return CommonReturnType.create(faceSimilar.getScore());
    }
}
