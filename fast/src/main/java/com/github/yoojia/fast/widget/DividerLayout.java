package com.github.yoojia.fast.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

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
        final TypedArray myAttrs = getContext().obtainStyledAttributes(attrs, R.styleable.iOSCell);
        final boolean isFirst = myAttrs.getBoolean(R.styleable.iOSCell_first, false);
        mTopDivider.setVisibility(isFirst ? VISIBLE : GONE);
        final boolean isLast = myAttrs.getBoolean(R.styleable.iOSCell_last, false);
        if (isLast){
            mBottomDivider.setVisibility(VISIBLE);
            mDivider.setVisibility(GONE);
        }else{
            mBottomDivider.setVisibility(GONE);
            mDivider.setVisibility(GONE);
        }
        final boolean showDivider = myAttrs.getBoolean(R.styleable.iOSCell_last, true);
        mDivider.setVisibility(showDivider ? VISIBLE : GONE);
        myAttrs.recycle();
    }
}
