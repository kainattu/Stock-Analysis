package com.kainattu.stock.api.utils;

public class FormateUtils {

    private FormateUtils(){

    }

    public static Long convertStringToLong(String longStr){

        if(longStr==null || "-".equals(longStr))
            return -1l;

        longStr.replace(",","");

       return Long.parseLong( longStr.replace(",",""));

    }

    public static Double convertStringToDouble(String doubleStr){

        if(doubleStr==null || "-".equals(doubleStr))
            return -1d;

        return  Double.parseDouble(doubleStr);


    }


}
