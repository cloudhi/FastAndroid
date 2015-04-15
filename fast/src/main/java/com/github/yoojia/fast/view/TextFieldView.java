package com.github.yoojia.fast.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.yoojia.fast.R;

/**
 * Text field view
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-15
 * @since   1.0
 */
public class TextFieldView extends FrameLayout{

    private final TextView mLabel;
    private final TextView mText;

    private final View mTopDivider;
    private final View mBottomDivider;
    private final View mDivider;

    public TextFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final View realView = View.inflate(context, R.layout.ios_text_field, null);
        addView(realView);

        mLabel = ViewFinder.find(R.id.ios_label, realView);
        mText = ViewFinder.find(R.id.ios_input, realView);

        mTopDivider = realView.findViewById(R.id.ios_top_divider);
        mBottomDivider = realView.findViewById(R.id.ios_bottom_divider);
        mDivider = realView.findViewById(R.id.ios_divider);

        final TypedArray myAttrs = context.obtainStyledAttributes(attrs, R.styleable.iOSTextField);

        initBounds(attrs);

        // 配置 Label
        final String label = myAttrs.getString(R.styleable.iOSTextField_textLabel);
        if (!TextUtils.isEmpty(label)){
            mLabel.setText(label);
        }else{
            mLabel.setVisibility(GONE);
        }

        // 4. Text
        final String text = myAttrs.getString(R.styleable.iOSTextField_android_text);
        mText.setText(text);

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
        return mText;
    }

    /**
     * 获取输入内容
     * @return 输入内容
     */
    public String getInputValue(){
        return mText.getText().toString();
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
