package com.example.cloudtest.service;

import com.example.cloudtest.common.ResponseMessage;
import com.example.cloudtest.utils.wkhtmltopdf.WkHtmlToPdf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {
    public static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${file.upload.path}")
    private String uploadPath;

    @Value("${file.pdf.path}")
    private String pdfPath;

    public ResponseMessage htmlToPdf(MultipartFile file,String pdfName) {

        if (file==null || file.isEmpty()) {
            return new ResponseMessage(0,"文件为空");
        }

        String fileName = file.getOriginalFilename();// 获取文件名
        logger.info("上传的文件名为：{}",fileName);

        String suffixName = fileName.substring(fileName.lastIndexOf("."));// 获取文件的后缀名
        if(!suffixName.equals(".html")) {
            return new ResponseMessage(0,"文件不是html");
        }

        File fileDir = new File(uploadPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String id = UUID.randomUUID().toString();
        File tempFile = new File(uploadPath,id+suffixName);

        try {
            file.transferTo(tempFile);
            logger.info("文件写入：{}",tempFile);

            fileDir = new File(pdfPath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            String pdf = pdfPath+pdfName;
            if(WkHtmlToPdf.transform(tempFile+" "+pdf)) {
                logger.info("源文件：{} 目标文件： {}",tempFile,pdf);
                tempFile.delete();
                return new ResponseMessage(1,"转换成功");
            } else {
                tempFile.delete();
                Files.deleteIfExists(Paths.get(pdf));
                return new ResponseMessage(0,"转换失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseMessage(0,"转换失败");
    }

    public void download(String filename, HttpServletResponse res) {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + filename);
        filename = pdfPath+filename;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            Files.copy(Paths.get(filename),os);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
