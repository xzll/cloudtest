package com.example.cloudtest.vo;

public enum Const {
     SEPARATOR(" "),
     WKHTMLTOPDF("wkhtmltopdf");

     private final String value;
     Const(String value) {
         this.value = value;
     }
     @Override
     public String toString() {
         return value;
     }
}
