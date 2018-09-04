package com.example.cloudtest.tool;


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WkTest {
    public static final Logger logger = LoggerFactory.getLogger(WkTest.class);
    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("d:/test.pdf");
        if(new File("d:/test.pdf").canWrite()){
            System.out.println("can write");
        }
        BufferedWriter bw = null;
        try {
            bw = Files.newBufferedWriter(path);
//            bw.write("test");
//            bw.close();

        }catch (IOException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }


    }
}
