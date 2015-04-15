package com.github.yoojia.fast.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
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
public class InputFieldView extends FrameLayout{

    private final TextView mLabel;
    private final EditText mInput;
    private final ImageView mIcon;

    private final View mTopDivider;
    private final View mBottomDivider;
    private final View mDivider;

    public InputFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final View realView = View.inflate(context, R.layout.ios_input_field, null);
        addView(realView);

        mIcon = ViewFinder.find(R.id.ios_icon, realView);
        mLabel = ViewFinder.find(R.id.ios_label, realView);
        mInput = ViewFinder.find(R.id.ios_input, realView);

        mTopDivider = realView.findViewById(R.id.ios_top_divider);
        mBottomDivider = realView.findViewById(R.id.ios_bottom_divider);
        mDivider = realView.findViewById(R.id.ios_divider);

        final TypedArray myAttrs = context.obtainStyledAttributes(attrs, R.styleable.iOSInputField);

        initBounds(attrs);

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

        myAttrs.recycle();

        if (TextUtils.isEmpty(mInput.getText())){
            mIcon.setVisibility(INVISIBLE);
        }

        mIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mInput.setText(null);
            }
        });

        // 监听输入状态
        mInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s) && mIcon.isShown()){
                    mIcon.setVisibility(INVISIBLE);
                    mIcon.setEnabled(false);
                }else{
                    mIcon.setVisibility(VISIBLE);
                    mIcon.setEnabled(true);
                }
            }
        });

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
     * 获取输入内容
     * @return 输入内容
     */
    public String getInputValue(){
        return mInput.getText().toString();
    }

    private void initBounds(final AttributeSet attrs){
        final TypedArray myAttrs = getContext().obtainStyledAttributes(attrs, R.styleable.iOSCell);
        final boolean isFirstCell = myAttrs.getBoolean(R.styleable.iOSCell_first, false);
        if (isFirstCell){
            mTopDivider.setVisibility(VISIBLE);
        }

        final boolean isLastCell = myAttrs.getBoolean(R.styleable.iOSCell_last, false);
        if (isLastCell){
            mBottomDivider.setVisibility(VISIBLE);
            mDivider.setVisibility(GONE);
        }
    }
}
