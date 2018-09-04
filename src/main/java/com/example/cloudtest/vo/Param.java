package com.example.cloudtest.vo;


import java.util.ArrayList;
import java.util.List;

public class Param {
    private String key;
    private List<String> values = new ArrayList<String>();
    public Param(String key,String... values) {
        this.key = key;
        for (String value:values) {
            this.values.add(value);
        }
    }
    @Override
    public String toString() {
        StringBuilder param = new StringBuilder();
        param.append(key).append(Const.SEPARATOR);
        for (String value:values) {
            param.append(value).append(Const.SEPARATOR);
        }
        return param.toString();
    }

}
