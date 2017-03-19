package com.example.administrator.zhihuibj.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;


/**
 * Created by Administrator on 2017/3/19.
 */

public class GovAffairsTabPage extends TabPage {
    private static final String TAG = "GovAffairsTabPage";
    public GovAffairsTabPage(Context context) {
        super(context);
    }

    public GovAffairsTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMenuSwitch(int position) {
        super.onMenuSwitch(position);
        Log.d(TAG, "onMenuSwitch: ");
    }
}
