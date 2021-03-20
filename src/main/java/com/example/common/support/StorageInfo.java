package com.example.common.support;

import com.example.util.NationalizationUtils;
import lombok.Data;

import java.util.Locale;

/**
 * @Description:
 *
 * request 中解析出来的数据 是通过token获取的
 *
 * @Author: chenchong
 * @Date: 2021/3/20 20:08
 */
@Data
public class StorageInfo {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * token
     */
    private String token;

    /**
     * userName
     */
    private String userName;






    /**
     * 语言
     */
//    private String lang = NationalizationUtils.DEFAULT_LANG;
    /**
     * 地区
     */
//    private Locale locale = Locale.SIMPLIFIED_CHINESE;
    /**
     * 时区
     */
//    private String timeZone = NationalizationUtils.DEFAULT_TIME_ZONE;
    /**
     * ip
     */
//    private String remoteIp;





}
