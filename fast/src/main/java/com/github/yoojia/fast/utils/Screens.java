package com.github.yoojia.fast.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;

/**
 * 屏幕辅助工具类
 *
 * @author  yoojia.chen@gmail.com
 * @version 2015-04-09
 * @since   1.0
 */
public class Screens {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param context Android Context
     * @param dpValue dp数据值
     * @return px像素值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     * @param context Android Context
     * @param pxValue px像素值
     * @return dp数据值
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕尺寸
     * @param activity Activity
     * @return 尺寸
     */
    public static Point getScreenSize(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
}
