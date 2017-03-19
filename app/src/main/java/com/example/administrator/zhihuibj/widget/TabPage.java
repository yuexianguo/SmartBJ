package com.example.administrator.zhihuibj.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zhihuibj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/17.
 */

public class TabPage extends RelativeLayout {
    private static final String TAG = "TabPage";
    @BindView(R.id.tab_page_content_frame)
    FrameLayout mTabPageContentFrame;
    private OnTabPageChangeListener mOnTabPageChangeListener;
    @BindView(R.id.menu)
    ImageView mMenu;
    @BindView(R.id.title)
    TextView mTitle;

    public TabPage(Context context) {
        this(context, null);

    }

    public TabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_tab_page, this);
        ButterKnife.bind(this, this);
    }


    @OnClick(R.id.menu)
    public void onClick() {
        Log.d(TAG, "onClick: ==被点击了");
        if (mOnTabPageChangeListener != null) {
            mOnTabPageChangeListener.onTabPageMenuClick();
        }
    }

    public void hide() {
        mMenu.setVisibility(GONE);
    }

    public void setTitle(String string) {
        mTitle.setText(string);
    }


    public interface OnTabPageChangeListener {
        void onTabPageMenuClick();
    }

    public void onMenuSwitch(int position) {
        Log.d(TAG, "onMenuSwitch: "+position);
    }


    public void setOnTabPageChangeListener(OnTabPageChangeListener listener) {
        mOnTabPageChangeListener = listener;
    }

}
