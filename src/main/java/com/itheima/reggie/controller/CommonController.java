package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;


/**
 * 文件上传和下载
 * the upload and download of files
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;


    /**
     * 文件上传
     * uoload files
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        //file是一个临时文件，需要转存到指定位置，本次请求完成后临时文件会被删除
        //file is a temporary file, which needs to be transferred to the specified location.
        // After the current request is completed, the temporary file will be deleted.
        log.info(file.toString());

        //原始文件名
        //original file name
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用UUID重新生成文件名，防止文件名称重复
        //Use UUID to regenerate the file name to prevent file name duplication
        String fileName = UUID.randomUUID().toString() + suffix;

        //创建一个目录对象
        //Create a directory object
        File dir = new File(basePath);
        //判断目录是否存在，不存在则创建
        //Determine whether the directory exists. If not, create it.
        if (!dir.exists()){
            dir.mkdirs();
        }

        try {
            //将临时文件转存到指定位置
            //Transfer the temporary file to the specified location
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        //输入流，读取文件内容
        //Input stream, read file content
        try {
            FileInputStream inputStream = new FileInputStream(new File(basePath + name));

            //输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            //Output stream, write the file back to the browser through the output stream,
            //and display the image in the browser
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");


            int len = 0;
            byte[] bytes = new byte[1024];
            while( (len = inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
