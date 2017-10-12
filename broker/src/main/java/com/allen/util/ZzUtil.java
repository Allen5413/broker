package com.allen.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Allen on 2017/8/3.
 */
public class ZzUtil {

    //金额验证
    public static boolean isMoney(String str){
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        if(match.matches()==false){
            return false;
        }else{
            return true;
        }
    }
}
