package com.github.yoojia.fast.app;

import android.support.v4.app.FragmentActivity;

import com.github.yoojia.fast.utils.UI;

/**
 * About me activity
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-23
 * @since   1.0
 */
public class AboutActivity extends FragmentActivity{

    @Override
    public void finish() {
        super.finish();
        UI.startHideAnimation(this);
    }
}
