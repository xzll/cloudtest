package com.example.cloudtest;

import com.example.cloudtest.vo.Command;
import com.example.cloudtest.vo.Config;
import com.example.cloudtest.vo.Const;
import com.example.cloudtest.vo.Param;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;

public class ParamTest {
    @Test
    public void test1() {
        System.out.println(Const.SEPARATOR+"1");
        Param param = new Param("key","v","b");
        System.out.println(param.toString());
    }

    @Test
    public void test2() {
//        System.out.println(command.executor);
        System.out.println(Charset.defaultCharset());
        System.out.println(System.getProperty("file.encoding"));

    }
    @Test
    public void test3() {
        System.out.println(Config.executor);

    }

}
