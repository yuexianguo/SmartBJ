package com.example.administrator.zhihuibj.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.zhihuibj.R;

/**
 * Created by Administrator on 2017/3/17.
 */

public class TabPage extends RelativeLayout {

    public TabPage(Context context) {
      this(context,null);

    }

    public TabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
       init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_tab_page,this);
    }


}
