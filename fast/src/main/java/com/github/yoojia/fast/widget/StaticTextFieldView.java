package com.github.yoojia.fast.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.github.yoojia.fast.R;
import com.github.yoojia.fast.view.ViewFinder;

/**
 * 静态文本型
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-15
 * @since   1.0
 */
public class StaticTextFieldView extends DividerLayout {

    private final TextView mLabel;
    private final TextView mValue;

    public StaticTextFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.ios_text_field, this);
        mLabel = ViewFinder.find(R.id.ios_label, this);
        mValue = ViewFinder.find(R.id.ios_input, this);

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

        // 4. Text
        final String text = myAttrs.getString(R.styleable.iOSTextField_android_text);
        mValue.setText(text);

        configValueGravity(mValue, attrs);

        myAttrs.recycle();

    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    /**
     * 获取输入框
     * @return EditText
     */
    public TextView getTextView(){
        return mValue;
    }

    /**
     * 获取输入内容
     * @return 输入内容
     */
    public String getInputValue(){
        return mValue.getText().toString();
    }

    /**
     * 设置输入框文本内容
     * @param text 文本内容
     */
    public void setText(CharSequence text){
        mValue.setText(text);
    }

    /**
     * 设置输入框文本内容
     * @param text 文本内容
     */
    public void setText(int text){
        mValue.setText(text);
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
