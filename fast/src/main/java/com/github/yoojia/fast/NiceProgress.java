package com.github.yoojia.fast;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

/**
 * a nice progress dialog
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-15
 * @since   1.0
 */
public class NiceProgress extends Dialog{

    private TextView mMessage;

    public NiceProgress(Context context) {
        super(context, R.style.LoadingDialog);
        setContentView(R.layout.ios_loading);
        setCancelable(false);
        mMessage = (TextView) findViewById(R.id.message);
    }

    public void setMessage(int msg){
        mMessage.setText(msg);
    }

    public void setMessage(CharSequence msg){
        mMessage.setText(msg);
    }

    @Override
    public void setTitle(CharSequence title) {
        setMessage(title);
    }

    @Override
    public void setTitle(int titleId) {
        setMessage(titleId);
    }
}
