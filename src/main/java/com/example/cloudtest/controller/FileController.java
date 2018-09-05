package com.example.cloudtest.controller;

import com.example.cloudtest.common.ResponseMessage;
import com.example.cloudtest.config.FileConfig;
import com.example.cloudtest.service.FileService;
import com.example.cloudtest.utils.wkhtmltopdf.WkHtmlToPdf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class FileController {
    public static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/htmltopdf")
    @ResponseBody
    public ResponseMessage upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) {

        String pdfName = UUID.randomUUID().toString()+".pdf";
        ResponseMessage  responseMessage = fileService.htmlToPdf(file,pdfName);
        if(responseMessage.getStatus()==1){
            try {
                response.sendRedirect("/download/"+pdfName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseMessage;

    }

    @RequestMapping(value = "/download/{filename}")
    public void download(@PathVariable("filename") String filename, HttpServletResponse res) {
        fileService.download(filename,res);
    }
}
