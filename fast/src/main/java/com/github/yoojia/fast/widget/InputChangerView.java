package com.github.yoojia.fast.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.github.yoojia.fast.R;
import com.github.yoojia.fast.view.ViewFinder;

/**
 * Input changer
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-19
 * @since   1.0
 */
public class InputChangerView extends FrameLayout{

    private EditText mValue;
    private ImageView mIncrease;
    private ImageView mReduces;

    private OnValueChangedListener mOnValueChangedListener;

    public InputChangerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.ios_input_changer, this);
        mValue = ViewFinder.find(R.id.ios_value, this);
        mIncrease = ViewFinder.find(R.id.ios_increase, this);
        mReduces = ViewFinder.find(R.id.ios_reduces, this);

        View.OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = mValue.getText().toString();
                if (TextUtils.isEmpty(value)) return;
                final int intValue = Integer.parseInt(value);
                int id = v.getId();
                int changedValue;
                if (id == R.id.ios_increase){
                    changedValue = intValue + 1;
                    mValue.setText(Integer.toString(changedValue));
                }else if (id == R.id.ios_reduces){
                    changedValue = intValue <= 0 ? 0: intValue - 1;
                    mValue.setText(Integer.toString(changedValue));
                }
            }
        };
        mIncrease.setOnClickListener(listener);
        mReduces.setOnClickListener(listener);

        mValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mValue.setSelection(s.length());
                if (mOnValueChangedListener != null && s.length() > 0){
                    int changedValue = Integer.parseInt(s.toString());
                    mOnValueChangedListener.onValueChanged(mValue, changedValue);
                }
            }
        });
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

    public String getInputValue(){
        return mValue.getText().toString();
    }

    public void setOnValueChangedListener(OnValueChangedListener onValueChangedListener) {
        mOnValueChangedListener = onValueChangedListener;
    }

    public interface OnValueChangedListener{
        void onValueChanged(EditText input, int value);
    }
}
