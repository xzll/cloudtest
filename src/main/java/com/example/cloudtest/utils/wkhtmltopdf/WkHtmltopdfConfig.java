package com.example.cloudtest.utils.wkhtmltopdf;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WkHtmltopdfConfig {
    public static final Logger logger = LoggerFactory.getLogger(WkHtmltopdfConfig.class);
    public static final String executor = findExecutor();
    public static final Long timeout = 180L;//等待时间 s
    /**
     * 从当前目录和环境变量中找wkhtmltopdf的执行文件的绝对路径
     * @return wkhtmltopdf执行文件绝对路径
     */
    public static String findExecutor(){
        try {
            //找文件路径，windows和linux命令不同
            String osname = System.getProperty("os.name").toLowerCase();
            boolean isWindows = osname.contains("windows");
            String cmd = (isWindows? "where ":"which ")+WkhtmltopdfConst.WKHTMLTOPDF;

            logger.info("执行命令: {}",cmd);

            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();

            String text = IOUtils.toString(p.getInputStream(),System.getProperty("sun.jnu.encoding"));
            if (text.isEmpty()) {
                throw new RuntimeException("在classpath中找不到wkhtmltopdf的执行文件");
            }
            logger.info("在classpath中找到wkhtmltopdf的执行文件: {}", text);
            return text;
        } catch (IOException e) {
            logger.error("ERROR",e);
        } catch (InterruptedException e) {
            logger.error("ERROR:",e);
        }
        return null;
    }
}
