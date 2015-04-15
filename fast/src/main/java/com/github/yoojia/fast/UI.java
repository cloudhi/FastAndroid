package com.github.yoojia.fast;

import android.app.Activity;
import android.content.Intent;


/**
 * 界面跳转处理类
 *
 * @author  陈永佳 (chenyongjia@parkingwang.com)
 * @version version 2015-04-14
 * @since   1.0
 */
public class UI {

    public static void to(Activity from, Class<? extends Activity> to){
        Intent i = new Intent(from, to);
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
