package com.github.yoojia.fast.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.github.yoojia.fast.view.AutoView;
import com.github.yoojia.fast.view.ViewFinder;
import com.github.yoojia.fast.widget.StaticStubFieldView;

/**
 * input field fragment
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-14
 * @since   1.0
 */
public class InputFieldFragment extends Fragment{

    @AutoView(viewId = R.id.stub)
    private StaticStubFieldView mStub;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_input, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ViewFinder.inject(this, view);

        ViewStub stub = mStub.getStubView();
        stub.setLayoutResource(R.layout.stub_item);
        stub.inflate();
        stub.setVisibility(View.VISIBLE);
    }
}
