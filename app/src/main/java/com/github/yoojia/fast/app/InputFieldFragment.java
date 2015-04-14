package com.github.yoojia.fast.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * input field fragment
 *
 * @author  yoojia.chen@gmail.com
 * @version version 2015-04-14
 * @since   1.0
 */
public class InputFieldFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input, null);
    }
}
