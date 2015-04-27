package com.github.yoojia.fast.http;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

/**
 * 将回调结果切换到UI线程的回调处理实现类
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-27
 * @since   1.1
 */
public abstract class UIThreadCallback implements HttpCallback{

    private final static int MSG_COMPLETE = -20150427;
    private final static int MSG_RESPONSE = -20150428;
    private final static int MSG_ERROR = -20150429;
    private final static int MSG_STARTED = -20150430;

    private final Handler mInnerHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MSG_COMPLETE:
                    onUICompleted();
                    break;
                case MSG_RESPONSE:
                    onUIResponse(msg.arg1, (String) msg.obj);
                    break;
                case MSG_ERROR:
                    onUIErrors((IOException) msg.obj);
                    break;
                case MSG_STARTED:
                    onUIStarted();
                    break;
            }
            return false;
        }
    });

    @Override
    final public void onErrors(IOException ioe) {
        final Message msg = Message.obtain(mInnerHandler, MSG_ERROR, 0, 0, ioe);
        msg.sendToTarget();
    }

    @Override
    final public void onResponse(int statusCode, String content) {
        final Message msg = Message.obtain(mInnerHandler, MSG_RESPONSE, statusCode, statusCode, content);
        msg.sendToTarget();
    }

    @Override
    final public void onCompleted() {
        mInnerHandler.sendEmptyMessage(MSG_COMPLETE);
        afterCompleted();
    }

    @Override
    public void onStarted() {
        mInnerHandler.sendEmptyMessage(MSG_STARTED);
    }

    /**
     * After complete
     */
    protected void afterCompleted(){}

    /**
     * 回调到UI线程中执行异常处理
     * @param ioe IO异常
     */
    public void onUIErrors(IOException ioe){
        ioe.printStackTrace();
    }

    /**
     * 回调到UI线程中执行HTTP请求完成后操作
     */
    public abstract void onUICompleted();

    /**
     * 回调到UI线程中执行HTTP请求开始后操作
     */
    public abstract void onUIStarted();

    /**
     * 回调到UI线程中执行HTTP请求响应结果处理
     */
    public abstract void onUIResponse(int statusCode, String content);

}
