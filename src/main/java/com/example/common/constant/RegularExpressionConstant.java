package com.example.common.constant;

/**
 * @Description: 正则表达式
 * @Author: chenchong
 * @Date: 2021/2/4 11:17
 */
public class RegularExpressionConstant {
    //手机号正则表达式
    public static final String phoneRegular = "^[1][3-8]\\d{9}$|^([6|9])\\d{7}$|^[0][9]\\d{8}$|^[6]([8|6])\\d{5}$";
    //邮箱正则表达式
    public static final String emailRegular = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";

    //邮件发送者
    public static final String mailFromUser="18762084218@163.com";
    //发送的主题
    public static final String mailSubject="服装商城";
}
