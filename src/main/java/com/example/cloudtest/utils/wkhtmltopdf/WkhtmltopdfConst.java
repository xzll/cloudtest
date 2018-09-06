package com.example.cloudtest.utils.wkhtmltopdf;

public enum WkhtmltopdfConst {
     SEPARATOR(" "),
     WKHTMLTOPDF("wkhtmltopdf");

     private final String value;
     WkhtmltopdfConst(String value) {
         this.value = value;
     }
     @Override
     public String toString() {
         return value;
     }
}
