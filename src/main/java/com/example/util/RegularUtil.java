package com.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 *
 * @Author: chenchong
 * @Date: 2021/2/4 15:02
 */
public class RegularUtil {

    /**
     * 正则表达式验证
     * @param regular
     * @param str
     * @return
     */
    public static boolean isRegular(String regular,String str){
        Pattern compile = Pattern.compile(regular);
        Matcher matcher = compile.matcher(str);
        return matcher.matches();
    }
}
