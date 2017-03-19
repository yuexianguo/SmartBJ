package com.example.administrator.zhihuibj.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/19.
 */

public class NewsCenterTabPage extends TabPage{

    public NewsCenterTabPage(Context context) {
        super(context);
    }

    public NewsCenterTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void onMenuSwitch(int position) {
        Object view = null;
        switch (position) {
            case 0:
                NewsPage newsPage = new NewsPage(getContext());
                view = newsPage;

                break;

            case 1:
                TextView textView = new TextView(getContext());
                textView.setText("组图");
                view=textView;
                break;
            case 2:
                TextView zhuanti = new TextView(getContext());
                zhuanti.setText("专题");
                view=zhuanti;
                break;

            case 3:
                TextView hudong = new TextView(getContext());
                hudong.setText("互动");
                view=hudong;
                break;


        }
        mTabPageContentFrame.removeAllViews();
        mTabPageContentFrame.addView((View) view);

    }

}
