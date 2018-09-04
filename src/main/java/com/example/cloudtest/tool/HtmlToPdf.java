package com.example.cloudtest.tool;

import com.example.cloudtest.vo.Config;
import com.example.cloudtest.vo.Const;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class HtmlToPdf {
    public static final Logger logger = LoggerFactory.getLogger(WkTest.class);


    public static void transform(String executor,String cmd) throws InterruptedException, IOException {

        execute(executor+cmd);
    }
    public static void transform(String cmd) throws InterruptedException, IOException {

        execute(Config.executor+cmd);
    }

    private static void execute(String cmd) throws InterruptedException, IOException {
        logger.info("command: {}",cmd);
        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();
        String message = IOUtils.toString(process.getErrorStream(),"GBK");
        if(process.exitValue()==0){
            logger.info("transform success! \n{}",message);
        } else {
            //有可能目标文件正在被使用
            logger.error("transform fail! \n{}", message);
        }
    }



    public static void main(String[] args) {
        try {

            execute("https://www.jianshu.com/p/4d65857ffe5e d:/test.pdf");
        }catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }catch (InterruptedException e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
