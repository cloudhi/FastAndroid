package com.github.yoojia.fast.view;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * View finder
 * @author yoojia.chen@gmail.com
 * @version 2015-04-09
 * @since   1.0
 */
public class ViewFinder {

    /**
     * 查找ViewID的View，并自动类型转换
     * @param viewId View ID
     * @param parentView View所在的宿主
     * @param <ViewType> 自动转换类型
     * @return View
     */
    public static <ViewType> ViewType find(int viewId, View parentView){
        return (ViewType) parentView.findViewById(viewId);
    }

    /**
     * 查找ViewID的View，并自动类型转换
     * @param viewId View ID
     * @param parentView View所在的宿主
     * @param <ViewType> 自动转换类型
     * @return View
     */
    public static <ViewType> ViewType find(int viewId, Activity parentView){
        return (ViewType) parentView.findViewById(viewId);
    }

    /**
     * 自动注入AutoView变量
     * @param host AutoView所在的宿主
     */
    public static void inject(Activity host){
        final Field[] fields = host.getClass().getDeclaredFields();
        for (Field f : fields){
            AutoView config = f.getAnnotation(AutoView.class);
            if (config == null) continue;
            f.setAccessible(true);
            final int viewId = config.viewId();
            final View view = host.findViewById(viewId);
            if (view == null){
                throw new IllegalArgumentException(String.format("View(ID:%d) not found!", viewId));
            }
            final Class<?> type = f.getType();
            try {
                f.set(host, type.cast(view));
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            }catch (ClassCastException e1){
                throw new IllegalStateException("Does your view id correct ?");
            }
        }
    }

}
