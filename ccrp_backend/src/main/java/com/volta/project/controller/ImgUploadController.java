package com.volta.project.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volta.project.common.BaseResponse;
import com.volta.project.common.HistogramMethod;
import com.volta.project.common.ResultUtils;
import com.volta.project.mapper.AppArtifactMapper;
import com.volta.project.model.entity.AppArtifact;
import io.swagger.annotations.Api;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static com.volta.project.common.ErrorCode.NULL_ERROR;
import static com.volta.project.common.ErrorCode.UPDATE_ERROR;

@RestController
@Api(tags = "上传图片")
@CrossOrigin
public class ImgUploadController {
    @Resource
    AppArtifactMapper mapper;
    @PostMapping(value = "file")
    public BaseResponse upload(MultipartFile file) throws IOException {
        //校验
        if(file.isEmpty()){
            return ResultUtils.error(NULL_ERROR);
        }
        //file重命名
        String originalname= file.getOriginalFilename();
        byte[] data= file.getBytes();
        String  ext="."+originalname.split("\\.")[1];
        String uuid= UUID.randomUUID().toString().replace("-","");
        String fileName=uuid+ext;
        //上传图片
        ApplicationHome applicationHome=new ApplicationHome(this.getClass());
        String pre =applicationHome.getDir().getParentFile().getParentFile().getAbsoluteFile()+
                "\\src\\main\\resources\\static\\image\\" ;
        String path=pre+fileName;
        String url = "file:///" + path;
        try {
            file.transferTo(new File(path));
            File file1 = new File(path);
            BufferedImage img = ImageIO.read(file1);
            String charaValue = HistogramMethod.getData(img);
            List<AppArtifact> artifacts=mapper.finaAll();
           // System.out.println(artifacts);
          //  QueryWrapper<AppArtifact> queryWrapper=new QueryWrapper<>();
            Map<AppArtifact, Double> artisimilarity=new HashMap<>();
          //  System.out.println(HistogramMethod.compare(charaValue,));
            for(AppArtifact artifact:artifacts){
                double similarity=HistogramMethod.compare(charaValue,artifact.getCharaValue());
                artisimilarity.put(artifact,similarity);
            }
            List<Map.Entry<AppArtifact,Double>> sortedSimilarities=new ArrayList<>(artisimilarity.entrySet());
            sortedSimilarities.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            List<AppArtifact> topAppAritifact=sortedSimilarities.stream().limit(2).map(Map.Entry::getKey).collect(Collectors.toList());

//            for (int i = 0; i < topAppAritifact.size(); i++) {
//                AppArtifact artifact = topAppAritifact.get(i);
//                Double similarity = sortedSimilarities.get(i).getValue();
//                System.out.println("第" + (i + 1) + "个最相似的AppArtifact对象：");
//                System.out.println("名称：" + artifact.getArtifactName());
//                System.out.println("描述：" + artifact.getDescription());
//                System.out.println("相似度：" + similarity);
//            }
            for(AppArtifact artifact : topAppAritifact){
                artifact.setCharaValue(null);
            }
            return ResultUtils.success(topAppAritifact);
        }catch (IOException e){
            e.printStackTrace();
        }
        return ResultUtils.error(UPDATE_ERROR);
    }
}

