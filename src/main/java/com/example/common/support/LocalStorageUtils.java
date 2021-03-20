package com.example.common.support;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/3/20 20:08
 */
public class LocalStorageUtils {
    private static final String STORAGE_INFO = "STORAGE_INFO";

    private LocalStorageUtils(){

    }
    private static final ThreadLocal<ConcurrentHashMap<String, Object>> S_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 清除
     */
    public static void remove() {
        S_THREAD_LOCAL.remove();
    }

    public static StorageInfo getStorageInfo(){
        ConcurrentHashMap<String, Object> map = S_THREAD_LOCAL.get();
        if (map == null){
            ConcurrentHashMap<String, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
            objectObjectConcurrentHashMap.put(STORAGE_INFO,new StorageInfo());
            S_THREAD_LOCAL.set(objectObjectConcurrentHashMap);

        }
        return (StorageInfo)S_THREAD_LOCAL.get().get(STORAGE_INFO);
    }

    public static void setStorageInfo(StorageInfo mo){
        ConcurrentHashMap<String, Object> map = S_THREAD_LOCAL.get();
        if (map == null){
            S_THREAD_LOCAL.set(new ConcurrentHashMap<>());
        }
        S_THREAD_LOCAL.get().put(STORAGE_INFO,mo);
    }
}
