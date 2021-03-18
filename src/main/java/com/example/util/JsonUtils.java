package com.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/1/28 14:32
 */
public class JsonUtils {

    public static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /**
     * json 转对象
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T>  T jsonToBean(String jsonData, Class<T> beanType){
        try {
            return objectMapper.readValue(jsonData, beanType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String beanToJsonString(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
