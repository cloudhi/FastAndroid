package com.github.yoojia.fast.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.github.yoojia.fast.FragmentTabHost;
import com.github.yoojia.fast.FragmentTabSpec;
import com.github.yoojia.fast.view.AutoView;
import com.github.yoojia.fast.view.ViewFinder;

public class TabHostActivity extends ActionBarActivity {

    @AutoView(viewId = android.R.id.tabhost)
    FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ios_tab_host_layout);
        ViewFinder.inject(this);

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(FragmentTabSpec.create(mTabHost, "coupon", R.drawable.icon_coupon, R.string.app_name),
                TestFragment.class, null);
        mTabHost.addTab(FragmentTabSpec.create(mTabHost, "statistics", R.drawable.icon_statistics, R.string.app_name),
                TestFragment.class, null);
        mTabHost.addTab(FragmentTabSpec.create(mTabHost, "record", R.drawable.icon_record, R.string.app_name),
                TestFragment.class, null);
        mTabHost.addTab(FragmentTabSpec.create(mTabHost, "profile", R.drawable.icon_profile, R.string.app_name),
                TestFragment.class, null);
    }

}