package com.github.yoojia.fast.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.yoojia.fast.R;

/**
 * iOS Table static cell view
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-13
 * @since   1.0
 */
public class TableCellStaticView extends FrameLayout {

    private final ImageView mIcon;
    private final TextView mLabel;
    private final TextView mValue;
    private final View mTopDivider;
    private final View mBottomDivider;
    private final View mDivider;
    private final View mNext;

    public TableCellStaticView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final View realView = View.inflate(context, R.layout.ios_table_cell_static, null);
        addView(realView);

        mIcon = ViewFinder.find(R.id.ios_icon, realView);
        mLabel = ViewFinder.find(R.id.ios_name, realView);
        mValue = ViewFinder.find(R.id.ios_value, realView);

        mTopDivider = realView.findViewById(R.id.ios_top_divider);
        mBottomDivider = realView.findViewById(R.id.ios_bottom_divider);
        mDivider = realView.findViewById(R.id.ios_divider);
        mNext = realView.findViewById(R.id.ios_next);

        final TypedArray myAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.iOSTableStaticCell);

        final int iconResId = myAttrs.getResourceId(R.styleable.iOSTableStaticCell_cell_icon, 0);
        if (iconResId != 0){
            mIcon.setImageResource(iconResId);
        }else{
            mIcon.setVisibility(GONE);
        }

        final int labelResId = myAttrs.getResourceId(R.styleable.iOSTableStaticCell_cell_label, 0);
        if (labelResId != 0){
            mLabel.setText(labelResId);
        }else{
            final String label = myAttrs.getString(R.styleable.iOSTableStaticCell_cell_label);
            if (!TextUtils.isEmpty(label)){
                mLabel.setText(label);
            }else{
                mLabel.setVisibility(GONE);
            }
        }

        final int valueResId = myAttrs.getResourceId(R.styleable.iOSTableStaticCell_cell_value, 0);
        if (valueResId != 0){
            mValue.setText(valueResId);
        }else{
            final String value = myAttrs.getString(R.styleable.iOSTableStaticCell_cell_value);
            if (!TextUtils.isEmpty(value)){
                mValue.setText(value);
            }else{
                mValue.setVisibility(GONE);
            }
        }

        initBounds(attrs);

        final boolean disabledNext = myAttrs.getBoolean(R.styleable.iOSTableStaticCell_cell_disabled_next, false);
        if (disabledNext){
            mNext.setVisibility(GONE);
        }
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
