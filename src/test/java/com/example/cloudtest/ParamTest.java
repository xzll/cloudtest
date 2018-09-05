package com.example.cloudtest;

import com.example.cloudtest.utils.wkhtmltopdf.WkHtmlToPdf;
import com.example.cloudtest.utils.wkhtmltopdf.WkHtmltopdfConfig;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Paths;

public class ParamTest {

    @Test
    public void test2() {
//        System.out.println(command.executor);
        System.out.println(Charset.defaultCharset());
        System.out.println(System.getProperty("file.encoding"));

    }
    @Test
    public void test3() {
//        System.out.println(WkHtmltopdfConfig.executor);
//        System.out.println(System.getProperty("sun.jnu.encoding"));
//        System.out.println(System.getProperty("file.encoding"));

        WkHtmlToPdf.transform("--load-error-handling ignore D:/aim/text.mhtml d:/test.pdf");
//        if(Paths.get("d").toFile().exists()){
//            System.out.println("exists");
//        }
    }

}
