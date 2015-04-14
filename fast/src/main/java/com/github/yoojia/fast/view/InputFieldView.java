package com.github.yoojia.fast.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
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

        final int labelResId = myAttrs.getResourceId(R.styleable.iOSInputField_input_label, 0);
        if (labelResId != 0){
            mLabel.setText(labelResId);
        }else{
            final String label = myAttrs.getString(R.styleable.iOSInputField_input_label);
            if (!TextUtils.isEmpty(label)){
                mLabel.setText(label);
            }else{
                mLabel.setVisibility(GONE);
            }
        }

        final int hintResId = myAttrs.getResourceId(R.styleable.iOSInputField_input_hint, 0);
        if (labelResId != 0){
            mInput.setHint(hintResId);
        }else{
            final String hint = myAttrs.getString(R.styleable.iOSInputField_input_hint);
            if (!TextUtils.isEmpty(hint)){
                mInput.setHint(hint);
            }
        }

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
                }else{
                    mIcon.setVisibility(VISIBLE);
                }
            }
        });

    }

    /**
     * 获取输入框
     * @return EditText
     */
    public EditText getInput(){
        return mInput;
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
