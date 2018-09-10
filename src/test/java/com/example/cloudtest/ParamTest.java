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

        WkHtmlToPdf.transform("E:\\pro\\wkhtmltopdf\\bin\\wkhtmltopdf.exe ","--load-error-handling ignore D:\\test.html d:\\test.pdf");
//        if(Paths.get("d").toFile().exists()){
//            System.out.println("exists");
//        }
    }
    @Test
    public void test4() {
        String message = "Counting pages (2/6)                                               \n" +
                "QFont::setPixelSize: Pixel size <= 0 (0)\n" +
                "QFont::setPixelSize: Pixel size <= 0 (0)\n" +
                "Resolving links (4/6)                                                       \n" +
                "Loading headers and footers (5/6)                                           \n" +
                "Printing pages (6/6)\n" +
                "Done                                                                      \n" +
                "Warning: Received createRequest signal on a disposed ResourceObject's NetworkAccessManager. This might be an indication of an iframe taking too long to load.\n" +
                "Exit with code 1 due to network error: ContentNotFoundError";
        System.out.println(message.contains("\nDone"));
    }

}
