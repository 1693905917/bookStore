package com.hzp.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-01-25 18:30
 */
public class WebUtils {
    /**
     * 将Map中的值注入到对应的JavaBean属性中
     * @param map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBean(Map map, T bean){
        try {
            System.out.println("注入之前"+bean);
            BeanUtils.populate(bean,map);
            System.out.println("注入之后"+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    /**
     * 将字符串转换成为 int 类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
