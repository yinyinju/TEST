package com.example.demo.util;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public  void setApplicationContext(ApplicationContext applicationContext)throws BeansException{
        SpringContextUtil.applicationContext=applicationContext;
    }

    public static Object getBean(String name)
    {
        return applicationContext.getBean(name);
    }

    private static <T> T getBean(Class<T> clazz)
    {
        return applicationContext.getBean(clazz);
    }

    private static <T> T getBean(String name,Class<T> requiredType)
    {
        return applicationContext.getBean(name,requiredType);
    }

    private static boolean containsBean(String name)
    {
        return applicationContext.containsBean(name);
    }

    private static boolean isSingleton(String name)
    {
        return applicationContext.isSingleton(name);
    }

    private static Class<?> getType(String name)
    {
        return applicationContext.getType(name);
    }
}
