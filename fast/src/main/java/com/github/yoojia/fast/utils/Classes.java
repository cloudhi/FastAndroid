package com.github.yoojia.fast.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-17
 * @since   1.0
 */
public class Classes {

    /**
     * 设置值到对象的Field中
     * @param to 目标对象
     * @param value 要设置的值
     * @param setter Setter方法
     * @param field 设置值的目标Field
     */
    public static void setValue(Object to, Object value, Method setter, Field field){
        // 优先 Setter，如果不存在，则直接设置值
        // Setter method first. If no setter method, set to field.
        if (setter != null){
            try {
                setter.invoke(to, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                field.set(to, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(Object from, Method getter, Field field){
        // 优先 Getter，如果不存在，则直接读取值
        // Getter method first. If getter method not exists, get value from field.
        if (getter != null){
            try {
                return (T) getter.invoke(from);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                return (T) field.get(from);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
