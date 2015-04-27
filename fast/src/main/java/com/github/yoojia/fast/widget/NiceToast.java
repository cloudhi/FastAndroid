package com.github.yoojia.fast.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.yoojia.fast.R;

import java.lang.ref.WeakReference;

/**
 * A nice toast
 *
 * @author  yoojia.chen@gmail.com
 * @version 2015-04-09
 * @since   1.0
 */
public class NiceToast {

    private static WeakReference<Toast> PRE_TOAST;
    private final Toast toast;
    private final TextView message;

    public NiceToast(Context context) {
        final View view = LayoutInflater.from(context).inflate(R.layout.ios_toast, null);
        toast = new Toast(context);
        message = (TextView) view.findViewById(R.id.tip);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        PRE_TOAST = new WeakReference<>(toast);
    }

    public void show(int message){
        showShort(message);
    }

    public void show(String message){
        showShot(message);
    }

    public void showShort(int message){
        cancelPre();
        this.message.setText(message);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showShot(String message){
        cancelPre();
        this.message.setText(message);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showLong(int message){
        cancelPre();
        this.message.setText(message);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static NiceToast make(Context context){
        return new NiceToast(context);
    }

    private void cancelPre(){
        if (PRE_TOAST != null){
            Toast cached = PRE_TOAST.get();
            if (cached != null && cached != this.toast) cached.cancel();
        }
    }
}