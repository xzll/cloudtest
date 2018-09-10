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
            String message = IOUtils.toString(process.getErrorStream(), System.getProperty("sun.jnu.encoding"));//需要从缓冲区中读出数据否则满了会堵塞
//            process.waitFor(WkHtmltopdfConfig.timeout,TimeUnit.SECONDS);
            if(!process.waitFor(WkHtmltopdfConfig.timeout,TimeUnit.SECONDS)){
                logger.error("超时");
                //杀死进程
                process.destroy();//不管用
                Thread.sleep(1000);
                if(process.isAlive()) {
                    process.destroyForcibly();
                }
//                while(process.isAlive());
//              killWkProcess();
                return false;
            }
//            Executor executor=Executors.newSingleThreadExecutor();
//            byte[] bytes = new FutureTask<byte[]>(new Callable<byte[]>() {
//                public byte[] call() throws Exception {
//                    return IOUtils.toByteArray(process.getErrorStream());
//                }
//            }).get();


            if (message.contains("Done ")) {// \nDone 就不行
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
//        catch (ExecutionException e) {
//            logger.error("ERROR", e);
//        }
        return false;
    }
    private void killWkProcess() {
        //https://stackoverflow.com/questions/6356340/killing-a-process-using-java
    }

    private Callable<byte[]> streamToByteArrayTask(final InputStream input) {//final?
        return new Callable<byte[]>() {
            public byte[] call() throws Exception {
                return IOUtils.toByteArray(input);
            }
        };
    }
}
