package com.allen.util;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.text.DecimalFormat;

/**
 * Created by Allen on 2016/12/19.
 */
public class StringUtil {
    public static boolean isEmpty(String str){
        if(null == str){
            return true;
        }else{
            str = str.trim();
            if("".equals(str) || 1 > str.length()){
                return true;
            }else{
                return false;
            }
        }
    }
    private static final String STR_FORMAT = "00000000";

    public static String haoAddOne_2(String liuShuiHao){
        Integer intHao = Integer.parseInt(liuShuiHao);
        intHao++;
        DecimalFormat df = new DecimalFormat(STR_FORMAT);
        return df.format(intHao);
    }

    public static String substringAfterLast(String str, String flag){
        int num = str.lastIndexOf(flag);
        return str.substring(num+1, str.length());
    }

    public static String getDecode(HttpServletRequest request,String name){
        String result="";
        try{
            String query=request.getQueryString();
            String[] a=query.split("\\&");
            String val="";
            for(String b:a){
                String[] c=b.split("\\=",2);
                if(c[0].equalsIgnoreCase(name)){
                    val=c[1];
                    break;
                }
            }
            result = URLDecoder.decode(val, "UTF-8");
        }catch(Throwable e){
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
    }
}
