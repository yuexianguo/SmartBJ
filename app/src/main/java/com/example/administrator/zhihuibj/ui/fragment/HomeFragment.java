package com.example.administrator.zhihuibj.ui.fragment;

import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.administrator.zhihuibj.R;
import com.example.administrator.zhihuibj.widget.TabPage;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/17.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.tab_page_container)
    FrameLayout mTabPageContainer;
    @BindView(R.id.tab_container)
    RadioGroup mTabRadiogroup;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }
    public void init() {
    super.init();
        mTabRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TabPage tabPage = new TabPage(getContext());

                //优化，把之前的tabpage移除掉
                mTabPageContainer.removeAllViews();
                mTabPageContainer.addView(tabPage);
            }
        });
        //默认check到首页
        mTabRadiogroup.check(R.id.tab_home);
    }

}
