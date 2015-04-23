package com.github.yoojia.fast.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;

import com.github.yoojia.fast.ExitTracker;
import com.github.yoojia.fast.FragmentTabSpec;
import com.github.yoojia.fast.view.AutoView;
import com.github.yoojia.fast.view.NavigationBar;
import com.github.yoojia.fast.view.ViewFinder;

public class MainActivity extends FragmentActivity {

    @AutoView(viewId = android.R.id.tabhost)
    FragmentTabHost mTabHost;

    private ExitTracker mExitTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ios_tab_host_layout);
        ViewFinder.inject(this);
        mExitTracker = new ExitTracker(this, R.string.msg_exit);

        NavigationBar navigationBar = new NavigationBar(this);
        navigationBar.setTitle(R.string.app_name);

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(FragmentTabSpec.create(mTabHost, "table", R.drawable.icon_coupon, R.string.test_table),
                TableFragment.class, null);
        mTabHost.addTab(FragmentTabSpec.create(mTabHost, "buttons", R.drawable.icon_statistics, R.string.test_button),
                ButtonFragment.class, null);
        mTabHost.addTab(FragmentTabSpec.create(mTabHost, "input", R.drawable.icon_record, R.string.test_input),
                InputFieldFragment.class, null);
        mTabHost.addTab(FragmentTabSpec.create(mTabHost, "profile", R.drawable.icon_profile, R.string.app_name),
                TestFragment.class, null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mExitTracker.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
