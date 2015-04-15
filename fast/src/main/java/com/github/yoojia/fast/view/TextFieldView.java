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
public class TextFieldView extends DividerLayout {

    private final TextView mLabel;
    private final TextView mText;

    public TextFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View.inflate(context, R.layout.ios_text_field, this);

        mLabel = ViewFinder.find(R.id.ios_label, this);
        mText = ViewFinder.find(R.id.ios_input, this);
        findDividers();

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

}
