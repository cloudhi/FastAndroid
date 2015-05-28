package com.github.yoojia.fast.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.TextView;

import com.github.yoojia.fast.R;
import com.github.yoojia.fast.view.ViewFinder;

/**
 * 动态填充View组件的表格单元
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-15
 * @since   1.1.7
 */
public class StaticStubFieldView extends DividerLayout {

    private final TextView mLabel;
    private final ViewStub mStub;

    public StaticStubFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.ios_static_stub, this);
        mLabel = ViewFinder.find(R.id.ios_label, this);
        mStub = ViewFinder.find(R.id.ios_stub, this);

        initDividers();

        final TypedArray myAttrs = context.obtainStyledAttributes(attrs, R.styleable.iOSTextField);
        configDividers(attrs);

        // 配置 Label
        final String label = myAttrs.getString(R.styleable.iOSTextField_textLabel);
        if (!TextUtils.isEmpty(label)){
            mLabel.setText(label);
        }else{
            mLabel.setVisibility(GONE);
        }
        myAttrs.recycle();
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    /**
     * 获取Stub
     * @return ViewStub
     */
    public ViewStub getStubView(){
        return mStub;
    }

    /**
     * 设置Label显示文本
     * @param label 显示文本
     */
    public void setLabel(CharSequence label){
        mLabel.setText(label);
    }

    /**
     * 设置Label显示文本
     * @param label 显示文本
     */
    public void setLabel(int label){
        mLabel.setText(label);
    }
}
