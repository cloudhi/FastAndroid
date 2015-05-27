package com.github.yoojia.fast.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.yoojia.fast.R;
import com.github.yoojia.fast.view.ViewFinder;

/**
 * 可点击的表格型文本框
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-13
 * @since   1.0
 */
public class TableTextFieldView extends DividerLayout {

    protected final Button mActionHandler;
    protected final ImageView mIcon;
    protected final TextView mLabel;
    protected final TextView mValue;
    protected final View mNext;

    public TableTextFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.ios_table_cell_static, this);

        mIcon = ViewFinder.find(R.id.ios_icon, this);
        mLabel = ViewFinder.find(R.id.ios_name, this);
        mValue = ViewFinder.find(R.id.ios_value, this);
        mActionHandler = ViewFinder.find(R.id.ios_action_handler, this);
        initDividers();

        mNext = ViewFinder.find(R.id.ios_next, this);

        final TypedArray myAttrs = context.obtainStyledAttributes(attrs, R.styleable.iOSTableStaticCell);
        configDividers(attrs);

        // 1. Icon
        final int iconResId = myAttrs.getResourceId(R.styleable.iOSTableStaticCell_cellIcon, 0);
        if (iconResId != 0){
            mIcon.setImageResource(iconResId);
        }else{
            mIcon.setVisibility(GONE);
        }
        // 2. Label
        final String label = myAttrs.getString(R.styleable.iOSTableStaticCell_cellLabel);
        mLabel.setText(label);
        // 3. Value
        final String value = myAttrs.getString(R.styleable.iOSTableStaticCell_cellValue);
        mValue.setText(value);
        // 4. Next arrow
        final boolean disabledNext = myAttrs.getBoolean(R.styleable.iOSTableStaticCell_cellDisabledNext, false);
        if (disabledNext){
            mNext.setVisibility(GONE);
        }
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        mActionHandler.setOnClickListener(l);
    }

    @Override
    public boolean isInEditMode() {
        return true;
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

    /**
     * 获取TextView组件
     * @return TextView
     */
    public TextView getTextView(){
        return mValue;
    }

    /**
     * 获取Text内容
     * @return 文本内容
     */
    public String getText(){
        return mValue.getText().toString();
    }


}
