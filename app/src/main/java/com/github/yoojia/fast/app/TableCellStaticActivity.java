package com.github.yoojia.fast.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.yoojia.fast.view.AutoView;
import com.github.yoojia.fast.view.TableCellStaticView;
import com.github.yoojia.fast.view.ViewFinder;

public class TableCellStaticActivity extends ActionBarActivity {

    @AutoView(viewId = R.id.profile)
    private TableCellStaticView mProfile;

    @AutoView(viewId = R.id.setting)
    private TableCellStaticView mSetting;

    @AutoView(viewId = R.id.about)
    private TableCellStaticView mAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_table_cell_static);
        ViewFinder.inject(this);

        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Table", ">>>> click profile");
                Toast.makeText(TableCellStaticActivity.this, "Click profile", Toast.LENGTH_SHORT).show();
            }
        });

        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Table", ">>>> click setting");
                Toast.makeText(TableCellStaticActivity.this, "Touch setting", Toast.LENGTH_SHORT).show();
            }
        });

        mAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Table", ">>>> click about");
                Toast.makeText(TableCellStaticActivity.this, "Touch about", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
