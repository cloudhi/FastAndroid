package com.github.yoojia.fast.utils;

import android.app.Activity;
import android.content.Intent;

import com.github.yoojia.fast.AppSessionManager;
import com.github.yoojia.fast.R;


/**
 * 界面跳转处理类
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-14
 * @since   1.0
 */
public class UI {

    /**
     * 跳转到另一个Activity
     * @param from 从Activity
     * @param to 目标Activity
     */
    public static void to(Activity from, Class<? extends Activity> to){
        Intent i = new Intent(from, to);
        from.startActivity(i);
        startShowAnimation(from);
    }

    /**
     * 跳转到另一个Activity，并提供配置Intent的接口
     * @param from Activity
     * @param to 目标Activity
     * @param handler 通过此接口来配置Intent
     */
    public static void to(Activity from, Class<? extends Activity> to, IntentConfigHandler handler){
        Intent i = new Intent(from, to);
        handler.onIntent(i);
        from.startActivity(i);
        startShowAnimation(from);
    }

    /**
     * 在切换Activity时调用
     * @param screen Activity
     */
    public static void startShowAnimation(Activity screen){
        screen.overridePendingTransition(R.anim.screen_show_enter, R.anim.screen_show_exit);
    }

    /**
     * 在Activity的finish方法中调用，以实现Activity切换动画
     * @param screen Activity
     */
    public static void startHideAnimation(Activity screen){
        screen.overridePendingTransition(R.anim.screen_hide_enter, R.anim.screen_hide_exit);
    }
}
