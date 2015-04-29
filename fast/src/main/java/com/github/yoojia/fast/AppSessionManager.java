package com.github.yoojia.fast;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.github.yoojia.fast.utils.IntentConfigHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * App 状态管理
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-23
 * @since   1.0
 */
public class AppSessionManager {

    // 监控Activity的生命周期
    private final Application.ActivityLifecycleCallbacks mLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {

        @Override
        public void onActivityStarted(Activity activity) {}

        @Override
        public void onActivityResumed(Activity activity) {}

        @Override
        public void onActivityPaused(Activity activity) {}

        @Override
        public void onActivityStopped(Activity activity) {}

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            mActivities.add(activity);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            mActivities.remove(activity);
        }
    };

    private final List<Activity> mActivities = new ArrayList<>();

    private final Application mApp;

    public AppSessionManager(Application app) {
        mApp = app;
    }

    /**
     * 监控Activity的运行情况
     */
    public void watchActivities(){
        mApp.registerActivityLifecycleCallbacks(mLifecycleCallbacks);
    }

    /**
     * 关闭所有Activity
     */
    public void closeActivities(){
        for (Activity activity : mActivities){
            activity.finish();
        }
    }

    /**
     * 启动一个Activity
     * @param target Activity类
     */
    public void startActivity(Class<? extends Activity> target){
        startActivity(target, null);
    }

    /**
     * 启动Activity，并配置其Intent
     * @param target Activity
     * @param handler 配置处理类
     */
    public void startActivity(Class<? extends Activity> target, IntentConfigHandler handler){
        Intent intent = new Intent(mApp, target);
        if (handler != null) handler.onIntent(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mApp.startActivity(intent);
    }

}
