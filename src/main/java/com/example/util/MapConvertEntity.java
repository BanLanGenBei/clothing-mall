package com.example.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

/**
 * 本类功能:
 *
 * @author chenchong
 * @date 2020/12/2 14:41
 */
@SuppressWarnings("all")
public class MapConvertEntity {
    //构造Set方法
    private static final String nameFreFix = "set";

    /**
     *
     * @param obj 实体类的对象
     * @param map map集合
     * @return
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object mapConvertEntity(Object obj, Map<String,Object> map) {
        //获取obj对象
        Class<?> clazz = obj.getClass();
        if(!map.isEmpty()){
            for (Map.Entry<String,Object> entry : map.entrySet()){
                String key = entry.getKey();
                Object value = entry.getValue();
                //查看实体类中是否有map的key这样一个属性
                Field field = getField(clazz,key);
                //如果有
                if(field != null){
                    Class<?> fieldType = field.getType();
                    //将map的值的类型全部转换为entity所需要的类型
                    value = convertValType(value,fieldType);
                    Method method = null;
                    //获取set方法名
                    String setMethodName = convertStr(key);
                    try {
                        method = clazz.getMethod(setMethodName,field.getType());
                        if(field.isAccessible()){
                            field.setAccessible(true);
                        }
                        method.invoke(obj,value);
                        field.setAccessible(false);


                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return obj;
    }

    /**
     *将String字符串的首字母大写
     */

    private static String convertStr(String str){
        String keyStr = nameFreFix.concat(str.substring(0,1).toUpperCase() + str.substring(1,str.length()));

        return keyStr;
    }
    /**
     * 获取类的属性
     */
    private static Field getField(Class<?> clazz,String fieldName){
        //判断传入的是Object类型或者是null,直接返回空
        if(clazz == null || Object.class.getName().equals(clazz.getName())){
            return null;
        }
        //获取所有的属性，包括私有的，包括继承下来的
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if(field.getName().equals(fieldName)){
                return field;
            }
        }
        Class<?> classUpper = clazz.getSuperclass();
        if(classUpper != null){
            return getField(classUpper,fieldName);
        }
        return null;

    }

    /**
     * 将object中的值按照类型转换为entity中的值
     * @param obj map集合的value值
     * @param fieldType
     * @return
     */
    private static Object convertValType(Object obj, Class<?> fieldType){
        Object retVal = null;
        if(Long.class.getName().equals(fieldType.getName()) || long.class.getName().equals(fieldType.getName())){
            retVal = Long.parseLong(obj.toString());
        }
        else if(Double.class.getName().equals(fieldType.getName()) || double.class.getName().equals(fieldType.getName())){
            retVal = Double.parseDouble(obj.toString());
        }
        else if(Integer.class.getName().equals(fieldType.getName()) || int.class.getName().equals(fieldType.getName())){
            retVal = Integer.parseInt(obj.toString());
        }
        else if (Float.class.getName().equals(fieldType.getName()) || float.class.getName().equals(fieldType.getName())){
            retVal = Float.parseFloat(obj.toString());
        }
        else if(String.class.getName().equals(fieldType.getName())){
            retVal = obj;
        }
        else if(Character.class.getName().equals(fieldType.getName()) || char.class.getName().equals(fieldType.getName())){
            retVal = obj;
        }
        else if (Date.class.getName().equals(fieldType.getName())){
            retVal = convertStrToDate(obj.toString());
        }else if(LocalDate.class.getName().equals(fieldType.getName())){
            retVal = convertStrToLocalDate(obj.toString());
        }
        return retVal;
    }

    /**
     * 将String类型转换为Date类型
     * @param str
     * @return
     */
    private static Date convertStrToDate(String str){

        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 将String转换为LocalDate
     */
    private static LocalDate convertStrToLocalDate(String str){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(str,fmt);
        return date;
    }

}
