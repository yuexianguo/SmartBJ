package com.example.administrator.zhihuibj.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;


/**
 * Created by Administrator on 2017/3/19.
 */

public class SettingCenterTabPage extends TabPage {
    private static final String TAG = "SettingCenterTabPage";
    public SettingCenterTabPage(Context context) {
        super(context);
    }

    public SettingCenterTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMenuSwitch(int position) {
        super.onMenuSwitch(position);
        Log.d(TAG, "onMenuSwitch: ");
    }
}
