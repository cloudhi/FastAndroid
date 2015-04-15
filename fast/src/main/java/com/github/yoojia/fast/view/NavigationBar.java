package com.github.yoojia.fast.view;


import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.yoojia.fast.R;

import java.lang.ref.WeakReference;

/**
 * Navigation view
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-14
 * @since   1.0
 */
public class NavigationBar{

    private final ActionBar mActionBar;
    private final TextView mTitle;
    private final ImageView mLeftImageButton;
    private final ImageView mRightImageButton;
    private final Button mRightTextButton;
    private final WeakReference<FragmentActivity> mActivity;

    public NavigationBar(FragmentActivity activity){
        mActivity = new WeakReference<>(activity);
        mActionBar = activity.getActionBar();
        assert mActionBar != null;
        mActionBar.setDisplayOptions(android.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionBar.setCustomView(R.layout.ios_nav_bar);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.show();
        View contentView = mActionBar.getCustomView();
        mTitle = ViewFinder.find(android.R.id.title, contentView);
        mLeftImageButton = ViewFinder.find(R.id.nav_left_image, contentView);
        mRightImageButton = ViewFinder.find(R.id.nav_right_image, contentView);
        mRightTextButton = ViewFinder.find(R.id.nav_right_text, contentView);
        mTitle.setText(activity.getTitle());

        checkBackEnable();

        // 默认返回处理
        mLeftImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.get().finish();
            }
        });
    }

    /**
     * 设置标题
     * @param resId 标题资源ID
     */
    public void setTitle(int resId){
        mTitle.setText(resId);
        mActivity.get().setTitle(resId);
    }

    /**
     * 覆盖设置点击返回按钮的处理接口
     * @param listener 处理接口
     */
    public void overrideOnBackClickListener(View.OnClickListener listener){
        mLeftImageButton.setOnClickListener(listener);
    }

    /**
     * 设置右图片按钮处理
     * @param buttonResId 按钮图标
     * @param listener 处理接口
     */
    public void setRightImageButton(int buttonResId, View.OnClickListener listener){
        mRightTextButton.setVisibility(View.GONE);
        mRightImageButton.setVisibility(View.VISIBLE);
        mRightImageButton.setImageResource(buttonResId);
        mRightImageButton.setOnClickListener(listener);
    }

    /**
     * 屏蔽右图片按钮
     */
    public void disableRightImageButton(){
        mRightImageButton.setVisibility(View.GONE);
    }

    /**
     * 设置右文字按钮
     * @param buttonResId 文本内容
     * @param listener 监听接口
     */
    public void setRightTextButton(int buttonResId, View.OnClickListener listener){
        mRightImageButton.setVisibility(View.GONE);
        mRightTextButton.setVisibility(View.VISIBLE);
        mRightTextButton.setText(buttonResId);
        mRightTextButton.setOnClickListener(listener);
    }

    /**
     * 屏幕右文字按钮
     */
    public void disableRightTextButton(){
        mRightTextButton.setVisibility(View.GONE);
    }

    public void disableRightButton(){
        disableRightImageButton();
        disableRightTextButton();
    }

    private void checkBackEnable(){
        if (mActivity.get().isTaskRoot()){
            mLeftImageButton.setVisibility(View.GONE);
        }else{
            mLeftImageButton.setVisibility(View.VISIBLE);
        }
    }

}
