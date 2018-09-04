package com.example.cloudtest.vo;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

public class Config {
    public static final Logger logger = LoggerFactory.getLogger(Config.class);
    public static final String executor = findExecutor();

    /**
     * 如果没有配置
     * 从当前目录和环境变量中找wkhtmltopdf的执行文件的绝对路径
     * @return wkhtmltopdf执行文件绝对路径
     */
    public static String findExecutor(){
        try {
            //找文件路径，windows和linux命令不同
            String osname = System.getProperty("os.name").toLowerCase();
            boolean isWindows = osname.contains("windows");
            String cmd = (isWindows? "where ":"which ")+Const.WKHTMLTOPDF;

            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();

            String charset = isWindows?"GBK":Charset.defaultCharset().name();
            String text = IOUtils.toString(p.getInputStream(),charset);//windows下都用gbk解码
            if (text.isEmpty()) {
                throw new RuntimeException("wkhtmltopdf command was not found in your classpath.");
            }
            logger.info("wkhtmltopdf command found in classpath: {}", text);
            return text;
        } catch (IOException e) {
            logger.error("ERROR",e);
        } catch (InterruptedException e) {
            logger.error("ERROR:",e);
        }
        return null;
    }
}
