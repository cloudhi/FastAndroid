package com.github.yoojia.fast.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
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
    private OnChangeButtonClickListener mChangeButtonClickListener;

    public InputChangerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.ios_input_changer, this);
        mValue = ViewFinder.find(R.id.ios_value, this);
        mIncrease = ViewFinder.find(R.id.ios_increase, this);
        mReduces = ViewFinder.find(R.id.ios_reduce, this);

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
                    final String s = Integer.toString(changedValue);
                    mValue.setText(s);
                    if (mChangeButtonClickListener != null) {
                        mChangeButtonClickListener.onIncrease(s);
                    }
                }else if (id == R.id.ios_reduce){
                    changedValue = intValue <= 0 ? 0: intValue - 1;
                    final String s = Integer.toString(changedValue);
                    mValue.setText(s);
                    if (mChangeButtonClickListener != null) {
                        mChangeButtonClickListener.onReduce(s);
                    }
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
                if (mOnValueChangedListener != null && s.length() > 0) {
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

    /**
     * 返回输入框的EditText对象
     * @return EditText对象
     */
    public EditText getEditText(){
        return mValue;
    }

    /**
     * 获取输入内容
     * @return 文本内容
     */
    public String getInputValue(){
        return mValue.getText().toString();
    }

    /**
     * 设置数值变化监听接口
     * @param listener 监听接口
     */
    public void setOnValueChangedListener(OnValueChangedListener listener) {
        mOnValueChangedListener = listener;
    }

    /**
     * 设置数值增减按钮点击监听接口
     * @param listener 监听接口
     */
    public void setChangeButtonClickListener(OnChangeButtonClickListener listener){
        mChangeButtonClickListener = listener;
    }

    /**
     * 在数值变化时回调
     */
    public interface OnValueChangedListener{
        /**
         * 发生数值变化
         * @param input EditText
         * @param value 变化后的数值
         */
        void onValueChanged(EditText input, int value);
    }

    /**
     * 增减按钮被点击时回调
     */
    public interface OnChangeButtonClickListener {
        /**
         * 增加按钮被点击
         * @param value 当前数值内容
         */
        void onIncrease(String value);

        /**
         * 减少按钮被点击
         * @param value 当前数值内容
         */
        void onReduce(String value);
    }
}
