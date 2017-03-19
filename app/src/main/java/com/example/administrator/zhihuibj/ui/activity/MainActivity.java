package com.example.administrator.zhihuibj.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

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
            public void onTabSwitch(int checkId) {
                //判断是哪个页面
                if(checkId==R.id.tab_home||checkId==R.id.tab_settings){
                    ////如果是切换到首页或者设置中心，则配置侧滑菜单不能拉出
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//不能拉出
                }else {
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                }
            }

            @Override
            public void onTabPageMenuClick() {
                Toast.makeText(MainActivity.this,"tabpage的事件已经从fragment传递到了mainactivity",Toast.LENGTH_SHORT).show();
                getSlidingMenu().toggle();
            }

        });
        //监听菜单按钮关闭侧滑菜单
        mMenuFragment.setOnMenuChangeListener(new MenuFragment.OnMenuChangeListener() {


            @Override
            public void onMenuItemSwitch(int position,boolean isSwitch) {

                getSlidingMenu().toggle();
                if(isSwitch){
                    mHomeFragment.onMenuSwitch(position);
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
