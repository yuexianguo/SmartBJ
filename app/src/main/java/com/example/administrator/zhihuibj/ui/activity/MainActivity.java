package com.example.administrator.zhihuibj.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.example.administrator.zhihuibj.R;
import com.example.administrator.zhihuibj.ui.fragment.HomeFragment;
import com.example.administrator.zhihuibj.ui.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by Administrator on 2017/3/17.
 */

public class MainActivity extends SlidingFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initLeftMenu();
        initSlidingMenu();
        initContent();
    }
    /**
     * 初始化内容区域
     */
    private void initContent() {
        setContentView(R.layout.content_frame);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, homeFragment)
                .commit();



    }

    /**
     * 配置侧滑菜单
     */
    private void initSlidingMenu() {
        SlidingMenu sm = getSlidingMenu();
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);//侧滑菜单偏移量
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        getSlidingMenu().setMode(SlidingMenu.LEFT);//设置左右皆可拉出侧滑菜单
    }





    /**
     * 初始化左边菜单布局
     */
    private void initLeftMenu() {

        setBehindContentView(R.layout.menu_frame);//设置左边侧滑菜单的布局

        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        MenuFragment menufragment = new MenuFragment();
        t.replace(R.id.menu_frame, menufragment);
        t.commit();

    }
}
