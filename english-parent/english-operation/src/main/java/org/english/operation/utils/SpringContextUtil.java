package org.english.operation.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class SpringContextUtil {

    private SpringContextUtil(){}

    private static ApplicationContext applicationContext;


    //获取上下文
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    //通过名字获取上下文中的bean
    public static <T> T getBean(String name, Class<T> clz)
            throws BeansException {
        T result = (T) applicationContext.getBean(name, clz);
        return result;
    }

    //通过类型获取上下文中的bean
    public static Object getBean(Class<?> requiredType){
        return applicationContext.getBean(requiredType);
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        if(SpringContextUtil.applicationContext == null){
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

}
