package cn.ech0.examinationsystem.controller.common;



import cn.ech0.examinationsystem.response.BaseResponse;
import cn.ech0.examinationsystem.util.QiniuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Author : Ech0
 *
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/19/2018 03:07 PM
 */

@RestController
@RequestMapping("/image")
@Slf4j
@CrossOrigin(origins = "*")
public class ImageController {

    @Autowired
    private QiniuUtil qiniuUtil;

    @PostMapping("/upload")
    public BaseResponse uploadImg(@RequestParam("image-file")MultipartFile multipartFile){




        String path = "";
        try (FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream()) {

            path = qiniuUtil.uploadImg(fileInputStream, UUID.randomUUID().toString());

        } catch (IOException e) {
            // upload failed
            log.error("upload img failed !  {}" , e);
        }

        if (StringUtils.isEmpty(path)) {
            return BaseResponse.ERROR;
        } else {
            return new BaseResponse(true, path);
        }

    }
}
