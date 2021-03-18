package com.example.common.exception;
/**
 *
 *@ClassName DBException
 *@Description
 *@author wxb
 *@date 2020/4/27 13:31
 *
 **/
public class DBException extends Exception {

    private static final long serialVersionUID = 1L;
    public static String desc = "数据库发生异常,请联系管理员!";

    public DBException() {
    }

    public DBException(String var1) {
        super(var1);
    }

    public DBException(Exception var1) {
        super(var1);
    }
}
