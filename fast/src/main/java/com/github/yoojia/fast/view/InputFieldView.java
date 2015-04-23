package com.github.yoojia.fast.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.yoojia.fast.R;

/**
 * Input field view
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-14
 * @since   1.0
 */
public class InputFieldView extends DividerLayout{

    private final TextView mLabel;
    private final EditText mInput;
    private final ImageView mIcon;

    public InputFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.ios_input_field, this);
        mIcon = ViewFinder.find(R.id.ios_icon, this);
        mLabel = ViewFinder.find(R.id.ios_label, this);
        mInput = ViewFinder.find(R.id.ios_input, this);
        findDividers();
        mIcon.setVisibility(GONE);
        final TypedArray myAttrs = context.obtainStyledAttributes(attrs, R.styleable.iOSInputField);
        configDividers(attrs);
        // 配置 Label
        final String label = myAttrs.getString(R.styleable.iOSInputField_inputLabel);
        if (!TextUtils.isEmpty(label)){
            mLabel.setText(label);
        }else{
            mLabel.setVisibility(GONE);
        }
        // 输入框的配置
        // 1. Input type
        final int inputType = myAttrs.getInt(R.styleable.iOSInputField_android_inputType, -108);
        if(inputType != -108) mInput.setInputType(inputType);
        // 2. hint
        final String hint = myAttrs.getString(R.styleable.iOSInputField_android_hint);
        mInput.setHint(hint);
        // 3. Max length
        final int inputMax = myAttrs.getInt(R.styleable.iOSInputField_android_maxLength, -1);
        if(inputMax != -1){
            final InputFilter filter = new InputFilter.LengthFilter(inputMax);
            mInput.setFilters(new InputFilter[]{filter});
        }
        // 4. Text
        final String text = myAttrs.getString(R.styleable.iOSInputField_android_text);
        mInput.setText(text);
        // 5. editable
        final boolean enabled = myAttrs.getBoolean(R.styleable.iOSInputField_android_enabled, true);
        mInput.setEnabled(enabled);
        // 6. Gravity
        final int gravity = myAttrs.getInt(R.styleable.iOSInputField_inputGravity, 0);
        if (gravity == 0) mInput.setGravity(Gravity.LEFT);
        else if (gravity == 1) mInput.setGravity(Gravity.RIGHT);

        myAttrs.recycle();
        innerActions();
    }

    /**
     * 设置输入框文本内容
     * @param valueText 文本内容
     */
    public void setText(CharSequence valueText){
        mInput.setText(valueText);
    }

    /**
     * 设置输入框文本内容
     * @param value 文本内容
     */
    public void setText(int value){
        mInput.setText(value);
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

    @Override
    public boolean isInEditMode() {
        return true;
    }

    /**
     * 获取输入框
     * @return EditText
     */
    public EditText getEditText(){
        return mInput;
    }

    /**
     * 是否为空内容
     * @return 是否为空
     */
    public boolean isEmpty(){
        return mInput.getText().length() == 0;
    }

    /**
     * 获取输入内容
     * @return 输入内容
     */
    public String getInputValue(){
        return mInput.getText().toString();
    }

    private void innerActions(){
        mIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mInput.setText(null);
            }
        });
        // 监听输入状态
        mInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)){
                    mIcon.setVisibility(INVISIBLE);
                }else{
                    mIcon.setVisibility(VISIBLE);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        // 失去焦点后，清空图标消失
        mInput.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && !TextUtils.isEmpty(mInput.getText())){
                    mIcon.setVisibility(VISIBLE);
                }else{
                    mIcon.setVisibility(INVISIBLE);
                }
            }
        });
        //
        if (TextUtils.isEmpty(mInput.getText())){
            mIcon.setVisibility(INVISIBLE);
        }
    }

}
