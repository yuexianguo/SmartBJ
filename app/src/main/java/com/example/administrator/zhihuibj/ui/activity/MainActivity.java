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

    private HomeFragment mHomeFragment;
    private MenuFragment mMenuFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeFragment=new HomeFragment();
        mMenuFragment = new MenuFragment();
        initLeftMenu();
        initSlidingMenu();
        initContent();
        initEvent();
    }

    private void initEvent() {
        //监听homefragment的接口回调，监听发生的tabpage页面的切换
        mHomeFragment.setOnHomeChangeListener(new HomeFragment.OnHomeChangeListener() {
            @Override
            public void OnTabSwitch(int checkId) {
                //判断是哪个页面
                if(checkId==R.id.tab_home||checkId==R.id.tab_settings){
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//不能拉出
                }else {
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                }
            }
        });

    }




    /**
     * 初始化内容区域
     */
    private void initContent() {
        setContentView(R.layout.content_frame);
        mHomeFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, mHomeFragment)
                .commit();



    }

    /**
     * 配置侧滑菜单
     */
    private void initSlidingMenu() {
        SlidingMenu sm = getSlidingMenu();
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);//侧滑菜单偏移量
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置拉出的位置
        getSlidingMenu().setMode(SlidingMenu.LEFT);//设置左右皆可拉出侧滑菜单
    }





    /**
     * 初始化左边菜单布局
     */
    private void initLeftMenu() {

        setBehindContentView(R.layout.menu_frame);//设置左边侧滑菜单的布局

        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        mMenuFragment = new MenuFragment();
        t.replace(R.id.menu_frame, mMenuFragment);
        t.commit();

    }
}
