package com.example.cloudtest.utils.wkhtmltopdf;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.*;

public class WkHtmlToPdf {
    public static final Logger logger = LoggerFactory.getLogger(WkHtmlToPdf.class);


    public static boolean transform(String executor, String cmd) {

        return execute(executor + "--load-error-handling ignore " + cmd);
    }

    public static boolean transform(String cmd) {

        return execute(WkHtmltopdfConfig.executor + "--load-error-handling ignore " + cmd);
    }

    private static boolean execute(String cmd) {
        try {
            logger.info("执行命令: {}", cmd);

            Process process = Runtime.getRuntime().exec(cmd);
            String message = IOUtils.toString(process.getErrorStream(), System.getProperty("sun.jnu.encoding"));//需要从缓冲区中读出数据否则满了会堵塞，不应该放在waitfor后面
            if(!process.waitFor(WkHtmltopdfConfig.timeout,TimeUnit.SECONDS)){
                logger.error("超时");
                //杀死进程
                process.destroy();
                Thread.sleep(1000);
                if(process.isAlive()) {
                    process.destroyForcibly();//如果还存活强制销毁
                }
                return false;
            }


            if (message.contains("Done ")) {//有文件输出的话，信息最后都会有Done
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
