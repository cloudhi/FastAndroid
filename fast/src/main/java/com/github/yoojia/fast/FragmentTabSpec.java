package com.github.yoojia.fast;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.yoojia.fast.view.ViewFinder;

/**
 * Fragment tab spec
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-14
 * @since   1.0
 */
public class FragmentTabSpec {

    /**
     * 构造一个TabHost的条目
     * @param tabHost TabHost
     * @param key Key
     * @param iconResId 图标资源ID
     * @param labelResId 名称资源ID
     * @return TabSpec
     */
    public static TabHost.TabSpec create(FragmentTabHost tabHost, String key, int iconResId, int labelResId){
        final View view = createItem(tabHost.getContext(), iconResId, labelResId);
        return tabHost.newTabSpec(key).setIndicator(view);
    }

    public static View createItem(Context context, int iconResId, int labelResId){
        final View item = View.inflate(context, R.layout.ios_tab_widget, null);
        final ImageView icon = ViewFinder.find(R.id.icon, item);
        final TextView label = ViewFinder.find(R.id.label, item);
        icon.setImageResource(iconResId);
        label.setText(labelResId);
        return item;
    }
}
