package com.github.yoojia.fast;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 按两次返回键退出应用
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-15
 * @since   1.0
 */
public class ExitTracker {

    private final AtomicBoolean mExitFlag = new AtomicBoolean(false);
    private final Activity mContext;
    private final int mConfirmExitMsg;
    private final Handler mResetExitFlagHandler;
    private final Runnable mResetExitFlagTask = new Runnable() {
        @Override
        public void run() {
            mExitFlag.set(false);
        }
    };

    public ExitTracker(Activity context, int confirmExitMsgResId) {
        mContext = context;
        this.mConfirmExitMsg = confirmExitMsgResId;
        mResetExitFlagHandler = new Handler(Looper.getMainLooper());
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK && !mExitFlag.get()) {
            mExitFlag.set(true);
            Toast.makeText(mContext, mConfirmExitMsg, Toast.LENGTH_SHORT).show();
            mResetExitFlagHandler.postDelayed(mResetExitFlagTask, 2500);
            return true;
        }else{
            return false;
        }
    }
}
