package com.github.yoojia.fast.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.yoojia.fast.R;

/**
 * Divider layout
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-15
 * @since   1.0
 */
class DividerLayout extends FrameLayout{

    private View mTopDivider;
    private View mBottomDivider;
    private View mDivider;

    public DividerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void initDividers(){
        mTopDivider = findViewById(R.id.ios_top_divider);
        mBottomDivider = findViewById(R.id.ios_bottom_divider);
        mDivider = findViewById(R.id.ios_divider);
    }

    protected void configDividers(final AttributeSet attrs){
        final TypedArray cellConfig = getContext().obtainStyledAttributes(attrs, R.styleable.iOSCell);
        final boolean isFirst = cellConfig.getBoolean(R.styleable.iOSCell_first, false);
        mTopDivider.setVisibility(isFirst ? VISIBLE : INVISIBLE);
        final boolean isLast = cellConfig.getBoolean(R.styleable.iOSCell_last, false);
        if (isLast){
            mBottomDivider.setVisibility(VISIBLE);
            mDivider.setVisibility(INVISIBLE);
        }else{
            mBottomDivider.setVisibility(INVISIBLE);
            mDivider.setVisibility(INVISIBLE);
        }
        cellConfig.recycle();
    }

    protected void configValueGravity(TextView input, AttributeSet attrs){
        final TypedArray cellConfig = getContext().obtainStyledAttributes(attrs, R.styleable.iOSCell);
        final int gravity = cellConfig.getInt(R.styleable.iOSCell_textGravity, 0);
        if (gravity == 0) input.setGravity(Gravity.LEFT);
        else if (gravity == 1) input.setGravity(Gravity.RIGHT);
        cellConfig.recycle();
    }
}
