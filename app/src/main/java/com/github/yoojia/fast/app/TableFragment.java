package com.github.yoojia.fast.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.yoojia.fast.widget.NiceToast;
import com.github.yoojia.fast.utils.UI;
import com.github.yoojia.fast.view.AutoView;
import com.github.yoojia.fast.widget.TableTextFieldView;
import com.github.yoojia.fast.view.ViewFinder;

/**
 * 表格视图
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-14
 * @since   1.0
 */
public class TableFragment extends Fragment{

    @AutoView(viewId = R.id.profile)
    private TableTextFieldView mProfile;

    @AutoView(viewId = R.id.setting)
    private TableTextFieldView mSetting;

    @AutoView(viewId = R.id.version)
    private TableTextFieldView mVersion;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_table, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ViewFinder.inject(this, view);

        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UI.to(getActivity(), AboutActivity.class);
            }
        });

        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NiceToast.make(getActivity()).show("Click Setting");
            }
        });

        mVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NiceToast.make(getActivity()).show("Click Version");
            }
        });
    }
}
