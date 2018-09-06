package com.example.cloudtest.utils.wkhtmltopdf;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WkHtmlToPdf {
    public static final Logger logger = LoggerFactory.getLogger(WkHtmlToPdf.class);


    public static boolean transform(String executor, String cmd) {

        return execute(executor + cmd);
    }

    public static boolean transform(String cmd) {

        return execute(WkHtmltopdfConfig.executor + cmd);
    }

    private static boolean execute(String cmd) {
        try {
            logger.info("执行命令: {}", cmd);

            Process process = Runtime.getRuntime().exec(cmd);
//            process.waitFor(WkHtmltopdfConfig.timeout,TimeUnit.SECONDS);
            if(!process.waitFor(WkHtmltopdfConfig.timeout,TimeUnit.SECONDS)){
                logger.error("超时");
//                process.destroy();
                return false;
            }

            String message = IOUtils.toString(process.getErrorStream(), System.getProperty("sun.jnu.encoding"));

            if (process.exitValue() == 0) {
                logger.info("命令: {}\n{}\n转换成功!", cmd, message);
                return true;
            } else {
                //有可能目标文件正在被使用
                logger.error("命令: {}\n{}\n转换失败!", cmd, message);
                return false;
            }
        } catch (InterruptedException e) {
            logger.error("ERROR", e);
        } catch (IOException e) {
            logger.error("ERROR", e);
        }
        return false;
    }
}
