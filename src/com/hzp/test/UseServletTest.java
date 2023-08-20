package com.hzp.test;

import java.lang.reflect.Method;

/**
 * @author ASUS
 * @projectName book
 * @description:
 * @date 2022-01-25 17:08
 */
public class UseServletTest {
    public void login(){
        System.out.println("这是login()方法");
    }
    public void register(){
        System.out.println("这是register()方法");
    }
    public void update(){
        System.out.println("这是update()方法");
    }

    public static void main(String[] args) {
        String action="login";
        try {
            // 获取action业务鉴别字符串 获取相应的业务方法反射对象
            Method declaredMethod = UseServletTest.class.getDeclaredMethod(action);
            System.out.println(declaredMethod);
            //调用目标业务方法
            declaredMethod.invoke(new UseServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
