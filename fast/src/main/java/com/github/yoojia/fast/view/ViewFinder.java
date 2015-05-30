package com.github.yoojia.fast.view;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.github.yoojia.fast.utils.Classes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * @param parentView View所在的宿主
     * @param viewId View ID
     * @param <ViewType> 自动转换类型
     * @return View
     */
    public static <ViewType> ViewType find(View parentView, int viewId){
        return find(viewId, parentView);
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
     * 查找ViewID的View，并自动类型转换
     * @param parentView View所在的宿主
     * @param viewId View ID
     * @param <ViewType> 自动转换类型
     * @return View
     */
    public static <ViewType> ViewType find(Activity parentView, int viewId){
        return find(viewId, parentView);
    }

    /**
     * 自动注入AutoView变量
     * @param host AutoView所在的宿主
     */
    public static void inject(Activity host){
        _inject(host, host);
    }

    /**
     * 自动注入Auto变量
     * @param host AutoView所在的宿主
     * @param rootView 宿主的RootView
     */
    public static void inject(Object host, View rootView){
        _inject(host, rootView);
    }

    private static void _inject(Object host, Object viewParent){
        final List<Field> fields = Classes.getAllFields(host.getClass());
        for (Field field : fields){
            AutoView config = field.getAnnotation(AutoView.class);
            if (config == null) continue;
            field.setAccessible(true);
            final int viewId = config.viewId();
            View view = null;
            if (viewParent instanceof View){
                view = ((View) viewParent).findViewById(viewId);
            }else if (viewParent instanceof Activity){
                view = ((Activity) viewParent).findViewById(viewId);
            }
            if (view == null){
                throw new IllegalArgumentException(String.format("The View(ID:%d) NOT FOUND!", viewId));
            }
            final Class<?> type = field.getType();
            try {
                field.set(host, type.cast(view));
            } catch (Exception iae) {
                Log.e("ViewFinder", String.format("> Auto inject(id:%d, view:%s) to field(:%s)",
                        config.viewId(), view, field.getName()));
                throw new IllegalStateException(iae);
            }
        }
    }



}
